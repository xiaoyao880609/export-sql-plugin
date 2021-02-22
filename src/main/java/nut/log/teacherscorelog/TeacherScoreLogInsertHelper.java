package nut.log.teacherscorelog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import common.DateUtil;
import common.JdbcUtil;
import common.SqlHelper;
import domain.JdbcConnInfo;

public class TeacherScoreLogInsertHelper {
    private static final String filePath = "E:\\sql_plugins\\log-insert.sql";
    private static final List<String> yearList = Arrays.asList("2017", "2018", "2019", "2020");
    private static Map<String, String> schoolCache = new HashMap<String, String>();
    private static Integer minMonth = null;
    private static final Integer limitSQL = 2000;

	public static String generateInsertSQL(int insertType, int serverType) {
        FileOutputStream fos = null;
        /*
         * select tsl.id,tsl.class_room_id,tsl.teacher_id,tsl.target_id,tsl.school_id,tsl.teacher_score_type,
         * tsl.score,tsl.last_score,tsl.use_device_type,tsl.create_time,tsl.create_user,
         * t.user_name,t.real_name,t.real_name as create_user_real_name from nut_cs003.lg_teacher_score_log_2020 tsl 
         * inner join nut_cs003.syb_teacher t on tsl.teacher_id = t.id 
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            fos = new FileOutputStream(filePath);
            conn = JdbcUtil.getConn4Center(serverType);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id,name from nut_center.syb_school");
            while (rs.next()) {
                schoolCache.put(rs.getString("id"), rs.getString("name"));
            }
            if (schoolCache.size() == 0) {
                return null;
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
                rs = stmt.executeQuery("select schema_name from information_schema.SCHEMATA where schema_name like 'nut%' and schema_name not in ('nut_center','nut_center_local','nut_center_local3','nut_job','nut_job_local','nut_log','nut_log_local','nut_message','nut_message_local','nut_resource','nut_resource_local','nut_spirit','nutfree','nutspiritcenter','nutnull');");
                List<String> dbList = new ArrayList<String>();
                while (rs.next()) {
                    dbList.add(rs.getString("schema_name"));
                }
                if (dbList.size() > 0) {
                    for (String dbName : dbList) {
                        for (String year : yearList) {
                            //System.out.println(dbName + ".lg_teacher_score_log_" + year + "-----already started!!!!-----");
                            DatabaseMetaData metaData = conn.getMetaData();
                            ResultSet resultSet = metaData.getTables(dbName, null, "lg_teacher_score_log_" + year, new String[]{"TABLE"});
                            int count = 0;
                            if (resultSet.next()) {
                                StringBuffer sqlSB = new StringBuffer();
                                sqlSB.append("select tsl.id,tsl.class_room_id,tsl.teacher_id,tsl.target_id,tsl.school_id,tsl.teacher_score_type,tsl.score,")
                                .append("tsl.last_score,tsl.use_device_type,tsl.create_time,tsl.create_user,t.user_name,t.real_name,t.real_name as create_user_real_name ")
                                .append("from ").append(dbName).append(".lg_teacher_score_log_").append(year).append(" tsl ")
                                .append("inner join ").append(dbName).append(".syb_teacher t on tsl.teacher_id = t.id ");
                                if (insertType == 0) {
                                    sqlSB.append(";");
                                } else if (insertType == 1) {
                                    sqlSB.append(" where date_format(tsl.create_time,'%Y-%m')<>date_format(now(),'%Y-%m');");
                                } else if (insertType == 2) {
                                    sqlSB.append(" where date_format(tsl.create_time,'%Y-%m')=date_format(now(),'%Y-%m');");
                                }
                                rs = stmt.executeQuery(sqlSB.toString());
                                List<Map<String,String>> pairList = new ArrayList<>();
                                while (rs.next()) {
                                    count++;
                                    pairList.add(SqlHelper.getColumnsPair(rs));
                                }
                                List<String> insertList = getInsertSQL(pairList, year);
                                if (insertList != null && insertList.size() > 0) {
                                    for (String insert : insertList) {
                                        fos.write(insert.getBytes());
                                    }
                                }
                            }
                            totalCount += count;
                            if (count > 0) System.out.println(dbName + ".lg_teacher_score_log_" + year + "【" + count + "】-----already done!!!!-----");
                        }
                    }
                }
            }
            System.err.println("--------------TeacherScoreLogInsertHelper.generateInsertSQL【" + totalCount + "】 already done!---------------");
            //schoolCache.forEach((k, v) -> System.err.println("schoolId: " + k + " dbName:" + v));
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
            schoolCache.clear();
            schoolCache = null;
        }
        return minMonth != null ? minMonth.toString() : null;
	}
	
	private static List<String> getInsertSQL(List<Map<String,String>> pairList, String academicYear) {
		try {
		    if (pairList.size() == 0) return null;
            Map<String, StringBuilder> insertMap = new HashMap<String, StringBuilder>();
            for (int j=0;j<pairList.size();j++) {
                Map<String, String> pair = pairList.get(j);
                String schoolName = StringUtils.isNotBlank(schoolCache.get(pair.get("school_id").substring(1, pair.get("school_id").length()-1))) ? schoolCache.get(pair.get("school_id").substring(1, pair.get("school_id").length()-1)) : "";
                String monthYear = DateUtil.yearMonthFormatDate(DateUtil.parseDate(pair.get("create_time").substring(1, pair.get("create_time").length() - 1), DateUtil.default_pattern));
                if (minMonth == null || minMonth > Integer.parseInt(monthYear)) {
                    minMonth = Integer.parseInt(monthYear);
                }
                String tableName = StringUtils.join("lg_teacher_score_log_result_", monthYear);
                String insertKey = StringUtils.join(tableName, Integer.valueOf(j / limitSQL).toString());
                if (insertMap.get(insertKey) == null) {
                    insertMap.put(insertKey, new StringBuilder());
                    StringBuilder columnSB = new StringBuilder();
                    StringBuilder valueSB = new StringBuilder();
                    for(Map.Entry<String,String> entry : pair.entrySet()){
                        columnSB.append(entry.getKey()).append(",");
                        valueSB.append(entry.getValue()).append(",");
                    }
                    columnSB.append("academic_year").append(",").append("school_name");
                    valueSB.append(academicYear).append(",").append("'").append(schoolName).append("'");
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
                    valueSB.append(academicYear).append(",").append("'").append(schoolName).append("'");
                    insertMap.get(insertKey)
                        .append(",\r\n(")
                        .append(valueSB.toString())
                        .append(")");
                }
            }
            return insertMap.values().stream().map(i -> i.append(";\r\n").toString()).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
}
