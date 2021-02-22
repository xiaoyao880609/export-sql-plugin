package nut.jiaoyuyun;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import common.JdbcUtil;
import common.SqlHelper;
import dictionary.LogTableSqlEnum;

public class JiaoYuYunInsertLogHelper {

	public static void generateInsertSQL() {
	    String filePath="E:\\sql_plugins\\jiaoyuyun\\nut_log_insert.sql";
        FileOutputStream fos = null;
        Connection conn = JdbcUtil.getConn4JiaoYuYun();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            fos = new FileOutputStream(filePath);
            List<LogTableSqlEnum> tableSqlEnums = LogTableSqlEnum.getEnumValues();
            for (LogTableSqlEnum tableSqlEnum : tableSqlEnums) {
                rs = stmt.executeQuery(tableSqlEnum.getSql());
                StringBuilder sb = new StringBuilder();
                boolean isFirstTable = true;
                int rowCount = 0;
                while (rs.next()) {
                    rowCount++;
                    JiaoYuYunInsertLogHelper.getInsertSQL(rs, sb, tableSqlEnum,isFirstTable);
                    isFirstTable = false;
                }
                if (sb.length() > 0) {
                    fos.write(sb.toString().getBytes());
                }
                System.out.println("[total：" + rowCount +"]create " + tableSqlEnum.getTable() + " insert sql already done!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("--------------JiaoYuYunInsertLogHelper.generateInsertSQL already done!---------------");
	}
	
	private static void getInsertSQL(ResultSet rs, StringBuilder sb, LogTableSqlEnum tableSqlEnum, boolean isFirstTable) {
		try {
			Map<String,String> pair = SqlHelper.getColumnsPair(rs);
			if (isFirstTable && rs.isFirst()) {
				sb.append("INSERT IGNORE INTO ")
					.append(tableSqlEnum.getTable())
					.append("(");
				for(Map.Entry<String,String> entry : pair.entrySet()){
					sb.append(entry.getKey()).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(") VALUES \r\n");
			}
			sb.append("(");
			for(Map.Entry<String,String> entry : pair.entrySet()){
				sb.append(entry.getValue()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
			if (rs.isLast()) {
				sb.append(";\r\n");
			} else {
				sb.append(",\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
