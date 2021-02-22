package wechatgroup;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import common.DateUtil;
import common.JdbcUtil;

public class WechatGroupInsertHelper {

    public static void generateInsertSQL() {
		String filePath="E:\\sql_plugins\\wx_wechat_group_config-insert.sql";
		FileOutputStream fos = null;
		Connection conn = JdbcUtil.getConn4JiaoYuYun();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			fos = new FileOutputStream(filePath);
			List<Map<String, String>> schemaList = new ArrayList<Map<String, String>>();
			rs = stmt.executeQuery("select ss.id as school_id,ss.code as school_code,ss.name as school_name,so.id as organization_id,so.code as organization_code,so.name as organization_name from nut_center.syb_school ss inner join nut_center.syb_organization so on ss.organization_id=so.id where ss.organization_id='35175a58-65b6-45c6-a426-fa59a24b3573'");
			while (rs.next()) {
				Map<String, String> schema = new HashMap<String, String>();
				schema.put("school_id", rs.getString("school_id"));
				schema.put("school_code", rs.getString("school_code"));
				schema.put("school_name", rs.getString("school_name"));
				schema.put("organization_id", rs.getString("organization_id"));
				schema.put("organization_code", rs.getString("organization_code"));
				schema.put("organization_name", rs.getString("organization_name"));
				schemaList.add(schema);
			}
			if (schemaList.size() > 0) {
				System.out.println(schemaList.size());
				String insertSQL = getInsertSQL(schemaList);
				if (insertSQL != null) {
					fos.write(insertSQL.getBytes());
				}
			}
			if (rs != null) {
				rs.close();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------------done!---------------");
	}
	
	private static String getInsertSQL(List<Map<String, String>> schemaList) {
		try {
			List<Map<String, String>> gradeMapList = new ArrayList<Map<String,String>>(2);
			Map<String, String> map1 = new HashMap<String, String>(2);
			map1.put("grade_code", "0203");
			map1.put("image_path", "advertisement/2020/04/10/92c9d93346fa478c9ecebfd66634ee24.png");
			gradeMapList.add(map1);
			Map<String, String> map2 = new HashMap<String, String>(2); 
			map2.put("grade_code", "0303");
			map2.put("image_path", "advertisement/2020/04/10/6667e8eb45924fc7b8cf9787fe58df95.png");
			gradeMapList.add(map2);
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ")
				.append("nut_center.wx_wechat_group_config(id,school_id,school_code,school_name,organization_id,organization_code,organization_name,grade_code,image_path,state,create_time) values ");
			for (int j=0;j<gradeMapList.size();j++) {
				Map<String, String> map = gradeMapList.get(j);
				for(int k=0;k<schemaList.size();k++){
					Map<String, String> schema = schemaList.get(k);
					sb.append("('" +UUID.randomUUID().toString() + "'")
					.append(",'"+schema.get("school_id")+"'")
					.append(",'"+schema.get("school_code")+"'")
					.append(",'"+schema.get("school_name")+"'")
					.append(",'"+schema.get("organization_id")+"'")
					.append(",'"+schema.get("organization_code")+"'")
					.append(",'"+schema.get("organization_name")+"'")
					.append(", '"+map.get("grade_code")+"'")
					.append(", '"+map.get("image_path")+"'")
					.append(", 1")
					.append(", '"+DateUtil.defaultFormatDate(new Date()) + "')");
					if (j < gradeMapList.size() - 1 || k < schemaList.size() - 1 ) {
						sb.append(", ");
					}
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
}
