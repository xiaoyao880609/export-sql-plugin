package nut.log.testerrorredolog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import common.DateUtil;
import common.JdbcUtil;
import common.SqlHelper;
import domain.JdbcConnInfo;

public class TestErrorRedoLogInsertHelper {
    private static final String filePath = "E:\\sql_plugins\\log-insert.sql";
    private static Integer minMonth = null;
    private static final Integer limitSQL = 2000;

	/**
	 * @param insertType - 0：全部，1：排除当前月数据，2当前月数据
	 */
	public static String generateInsertSQL(int insertType, int serverType) {
        FileOutputStream fos = null;
        /*
         * SELECT id,type,top_topic_code,topic_code,user_id,klass_id,exam_id,exam_name,exam_type,from_type,master_level,grade_code,
         * specialty_code,academic_year,question_count,right_count,score,auto_submit,start_time,end_time,student_score,create_time,school_id 
         * FROM nut_cs003.wh_test_error_redo_log;
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            fos = new FileOutputStream(filePath, true);
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
                        //System.out.println(dbName + ".wh_test_error_redo_log-----already started!!!!-----");
                        DatabaseMetaData metaData = conn.getMetaData();
                        ResultSet resultSet = metaData.getTables(dbName, null, "wh_test_error_redo_log", new String[]{"TABLE"});
                        int count = 0;
                        if (resultSet.next()) {
                            StringBuffer sqlSB = new StringBuffer();
                            sqlSB.append("SELECT id,type,top_topic_code,topic_code,user_id,klass_id,exam_id,exam_name,exam_type,from_type,master_level,grade_code,")
                            .append("specialty_code,academic_year,question_count,right_count,score,auto_submit,start_time,end_time,student_score,create_time,school_id ")
                            .append("from ").append(dbName).append(".wh_test_error_redo_log");
                            if (insertType == 0) {
                                sqlSB.append(";");
                            } else if (insertType == 1) {
                                sqlSB.append(" where date_format(create_time,'%Y-%m')<>date_format(now(),'%Y-%m');");
                            } else if (insertType == 2) {
                                sqlSB.append(" where date_format(create_time,'%Y-%m')=date_format(now(),'%Y-%m');");
                            }
                            rs = stmt.executeQuery(sqlSB.toString());
                            List<Map<String,String>> pairList = new ArrayList<>();
                            while (rs.next()) {
                                count++;
                                pairList.add(SqlHelper.getColumnsPair(rs));
                            }
                            List<String> insertList = getInsertSQL(pairList);
                            if (insertList != null && insertList.size() > 0) {
                                for (String insert : insertList) {
                                    fos.write(insert.getBytes());
                                }
                            }
                        }
                        totalCount += count;
                        if (count > 0) System.out.println(dbName + ".wh_test_error_redo_log【" + count + "】-----already done!!!!-----");
                    }
                }
            }
            System.err.println("--------------TestErrorRedoLogInsertHelper.generateInsertSQL【" + totalCount + "】 already done!---------------");
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
        }
        return minMonth != null ? minMonth.toString() : null;
	}
	
	private static List<String> getInsertSQL(List<Map<String,String>> pairList) {
		try {
		    if (pairList.size() == 0) return null;
            Map<String, StringBuilder> insertMap = new HashMap<String, StringBuilder>();
            for (int j=0;j<pairList.size();j++) {
                Map<String, String> pair = pairList.get(j);
                String monthYear = DateUtil.yearMonthFormatDate(DateUtil.parseDate(pair.get("create_time").substring(1, pair.get("create_time").length() - 1), DateUtil.default_pattern));
                if (minMonth == null || minMonth > Integer.parseInt(monthYear)) {
                    minMonth = Integer.parseInt(monthYear);
                }
                String tableName = StringUtils.join("lg_test_error_redo_log_result_", monthYear);
                String insertKey = StringUtils.join(tableName, Integer.valueOf(j / limitSQL).toString());
                if (insertMap.get(insertKey) == null) {
                    insertMap.put(insertKey, new StringBuilder());
                    StringBuilder columnSB = new StringBuilder();
                    StringBuilder valueSB = new StringBuilder();
                    for(Map.Entry<String,String> entry : pair.entrySet()){
                        columnSB.append(entry.getKey()).append(",");
                        valueSB.append(entry.getValue()).append(",");
                    }
                    columnSB.deleteCharAt(columnSB.length() - 1);
                    valueSB.deleteCharAt(valueSB.length() - 1);
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
                    valueSB.deleteCharAt(valueSB.length() - 1);
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
