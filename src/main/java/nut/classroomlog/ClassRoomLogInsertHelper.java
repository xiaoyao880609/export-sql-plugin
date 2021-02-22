package nut.classroomlog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import common.DateUtil;
import common.JdbcUtil;
import common.SqlHelper;
import domain.JdbcConnInfo;

public class ClassRoomLogInsertHelper {
    private static final String filePath = "E:\\sql_plugins\\log-insert.sql";
    private static Map<String, String> schoolCodeCache = new HashMap<String, String>();
    private static Map<String, String> schoolNameCache = new HashMap<String, String>();
    private static Map<String, String> specialtyCache = new HashMap<String, String>();
    private static final Integer limitSQL = 2000;
    private static final List<Integer> yearList = Arrays.asList(2016,2017,2018);

	public static void generateInsertSQL(int insertType, int serverType) {
        FileOutputStream fos = null;
        /*
         * select cr.id,cr.lesson_id,cr.lesson_name,cr.specialty_code,cr.grade_code,cr.academic_year,cr.klass_id,k.name,cr.school_id,cr.teacher_id,t.user_name,t.real_name,cr.student_count,ks.klass_student_count,cr.device_type,cr.create_time 
         * from nut_cs003.cl_class_room cr 
         * left join nut_cs003.syb_klass k on cr.klass_id=k.id 
         * left join nut_cs003.syb_teacher t on cr.teacher_id=t.id 
         * left join (select klass_id,count(1) as klass_student_count from nut_cs003.syb_klass_student group by klass_id) ks on cr.klass_id=ks.klass_id;
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Map<Integer, List<Map<String,String>>> pairListMap = null;
        Set<String> idSet = new HashSet<>();
        try {
            fos = new FileOutputStream(filePath);
            conn = JdbcUtil.getConn4Center(serverType);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id,code,name from nut_center.syb_school");
            while (rs.next()) {
                schoolCodeCache.put(rs.getString("id"), rs.getString("code"));
                schoolNameCache.put(rs.getString("id"), rs.getString("name"));
            }
            if (schoolCodeCache.size() == 0 || schoolNameCache.size() == 0) {
                return;
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            rs = stmt.executeQuery("select code,name from nut_center.syb_specialty");
            while (rs.next()) {
                specialtyCache.put(rs.getString("code"), rs.getString("name"));
            }
            if (specialtyCache.size() == 0) {
                return;
            }
            List<JdbcConnInfo> connInfos = JdbcUtil.getConnInfos(serverType);
            int totalCount = 0;
            for (JdbcConnInfo connInfo : connInfos) {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                conn = JdbcUtil.getConn(connInfo.getUrl(), connInfo.getUsername(), connInfo.getPassword());
                stmt = conn.createStatement();
                rs = stmt.executeQuery("select schema_name from information_schema.SCHEMATA where schema_name like 'nut%' and schema_name not in ('nut_center','nut_center_local','nut_center_local3','nut_job','nut_job_local','nut_log','nut_log_local','nut_message','nut_message_local','nut_resource','nut_resource_local','nut_spirit','nutfree','nutspiritcenter','nutnull','nut_newdb');");
                List<String> dbList = new ArrayList<String>();
                while (rs.next()) {
                    dbList.add(rs.getString("schema_name"));
                }
                if (dbList.size() > 0) {
                    for (String dbName : dbList) {
                        DatabaseMetaData metaData = conn.getMetaData();
                        ResultSet resultSet = metaData.getTables(dbName, null, "cl_class_room", new String[]{"TABLE"});
                        int count = 0;
                        if (resultSet.next()) {
                            StringBuffer sqlSB = new StringBuffer();
                            sqlSB.append("select cr.id,cr.id as class_room_id,cr.lesson_id,cr.lesson_name,cr.specialty_code,cr.grade_code,cr.academic_year,cr.klass_id,k.name as klass_name,cr.school_id,cr.teacher_id,t.user_name,t.real_name,cr.student_count,ks.klass_student_count,cr.device_type,cr.create_time ")
                            .append("from ").append(dbName).append(".cl_class_room cr ")
                            .append("left join ").append(dbName).append(".syb_klass k on cr.klass_id=k.id ")
                            .append("left join ").append(dbName).append(".syb_teacher t on cr.teacher_id=t.id ")
                            .append("left join (select klass_id,count(1) as klass_student_count from ").append(dbName).append(".syb_klass_student group by klass_id) ks on cr.klass_id=ks.klass_id where cr.academic_year > 0");
                            if (insertType == 0) {
                                sqlSB.append(";");
                            } else if (insertType == 1) {
                                sqlSB.append(" and cr.create_time < '2021-01-22 00:00:00'");
                            } else if (insertType == 2) {
                                sqlSB.append(" and cr.create_time >= '2021-01-22 00:00:00'");
                            }
                            rs = stmt.executeQuery(sqlSB.toString());
                            pairListMap = new HashMap<>();
                            while (rs.next()) {
                                count++;
                                Map<String, String> pair = SqlHelper.getColumnsPair(rs);
                                Integer academicYear = Integer.valueOf(pair.get("academic_year"));
                                if (yearList.contains(academicYear) && idSet.contains(pair.get("id"))) {
                                    continue;
                                } else {
                                    idSet.add(pair.get("id"));
                                }
                                List<Map<String, String>> pairList = pairListMap.get(academicYear);
                                if (pairList == null) {
                                    pairList = new ArrayList<>();
                                    pairListMap.put(academicYear, pairList);
                                }
                                pairList.add(pair);
                            }
                            List<String> insertList = getInsertSQL(pairListMap);
                            if (insertList != null && insertList.size() > 0) {
                                for (String insert : insertList) {
                                    fos.write(insert.getBytes());
                                }
                            }
                        }
                        totalCount += count;
                        if (count > 0) System.out.println(dbName + ".cl_class_room" + "【" + count + "】-----already done!!!!-----");
                    }
                }
            }
            System.err.println("--------------ClassRoomLogInsertHelper.generateInsertSQL【" + totalCount + "】 already done!---------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            schoolCodeCache.clear();
            schoolCodeCache = null;
            schoolNameCache.clear();
            schoolNameCache = null;
            specialtyCache.clear();
            specialtyCache = null;
            pairListMap.clear();
            pairListMap = null;
            idSet.clear();
            idSet = null;
        }
	}
	
	private static List<String> getInsertSQL(Map<Integer, List<Map<String,String>>> pairListMap) {
		try {
		    if (pairListMap == null || pairListMap.size() == 0) return null;
		    Map<String, StringBuilder> insertMap = new HashMap<String, StringBuilder>();
		    for (Integer academicYear : pairListMap.keySet()) {
		        List<Map<String, String>> pairList = pairListMap.get(academicYear);
		        for (int j=0;j<pairList.size();j++) {
		            Map<String, String> pair = pairList.get(j);
		            String schoolCode = StringUtils.isNotBlank(schoolCodeCache.get(pair.get("school_id").substring(1, pair.get("school_id").length()-1))) ? schoolCodeCache.get(pair.get("school_id").substring(1, pair.get("school_id").length()-1)) : "";
		            String schoolName = StringUtils.isNotBlank(schoolNameCache.get(pair.get("school_id").substring(1, pair.get("school_id").length()-1))) ? schoolNameCache.get(pair.get("school_id").substring(1, pair.get("school_id").length()-1)) : "";
		            String specialtyName = StringUtils.isNotBlank(specialtyCache.get(pair.get("specialty_code").substring(1, pair.get("specialty_code").length()-1))) ? specialtyCache.get(pair.get("specialty_code").substring(1, pair.get("specialty_code").length()-1)) : "";
		            Date createTime = DateUtil.parseDate(pair.get("create_time").substring(1, pair.get("create_time").length() - 1), DateUtil.default_pattern);
		            pair.put("create_time", "'" + DateUtil.defaultFormatDate(new Date(createTime.getTime() - 1000)) + "'");
		            String studentCount = Optional.ofNullable(pair.get("student_count")).orElse("0");
		            String klassStudentCount = Optional.ofNullable(pair.get("klass_student_count")).orElse("0");
		            Double joinRate = Double.valueOf(klassStudentCount) > 0 ? Double.valueOf(studentCount) / Double.valueOf(klassStudentCount) * 100 : 0.0;
		            String tableName = StringUtils.join("lg_class_room_log_", academicYear.toString());
		            String insertKey = StringUtils.join(tableName, Integer.valueOf(j / limitSQL).toString());
		            if (insertMap.get(insertKey) == null) {
		                insertMap.put(insertKey, new StringBuilder());
		                StringBuilder columnSB = new StringBuilder();
		                StringBuilder valueSB = new StringBuilder();
		                for(Map.Entry<String,String> entry : pair.entrySet()){
		                    columnSB.append(entry.getKey()).append(",");
		                    valueSB.append(entry.getValue()).append(",");
		                }
		                columnSB.append("specialty_name").append(",").append("school_code").append(",").append("school_name").append(",").append("join_rate");
		                valueSB.append("'").append(specialtyName).append("','").append(schoolCode).append("','").append(schoolName).append("',").append(joinRate);
		                insertMap.get(insertKey)
		                .append("INSERT INTO nut_log.")
		                .append(tableName)
		                .append(" (")
		                .append(columnSB.toString())
		                .append(") VALUES (")
		                .append(valueSB.toString())
		                .append(")");
		            } else {
		                StringBuilder valueSB = new StringBuilder();
		                for(Map.Entry<String,String> entry : pair.entrySet()){
		                    valueSB.append(entry.getValue()).append(",");
		                }
		                valueSB.append("'").append(specialtyName).append("','").append(schoolCode).append("','").append(schoolName).append("',").append(joinRate);
		                insertMap.get(insertKey)
		                .append(",\r\n(")
		                .append(valueSB.toString())
		                .append(")");
		            }
		        }
		    }
            return insertMap.values().stream().map(i -> i.append(";\r\n").toString()).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
}
