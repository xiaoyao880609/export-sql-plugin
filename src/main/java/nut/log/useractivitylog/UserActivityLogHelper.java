package nut.log.useractivitylog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import common.DateUtil;

/**
 * 已迁移完成
 */
public class UserActivityLogHelper { 
	private static void generateCreateSQL(String beginMonth) {
		String filePath="E:\\sql_plugins\\user_activity_log-create.sql";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			String endMonth = DateUtil.formatDate(DateUtil.addMonth(new Date(), 3), DateUtil.DATE_FORMAT);
			StringBuffer sb = null;
			while (!beginMonth.equals(endMonth)) {
				sb = new StringBuffer();
				sb.append("CREATE TABLE IF NOT EXISTS `nut_log`.`lg_user_activity_log_").append(beginMonth).append("` (")
					.append("`id` char(36) NOT NULL,")
					.append("`user_id` char(36) NOT NULL,")
					.append("`user_name` varchar(50) NOT NULL COMMENT '教师用户名',")
					.append("`real_name` varchar(50) NOT NULL COMMENT '教师姓名',")
					.append("`specialty_code` char(2) NOT NULL COMMENT '教师学科',")
					.append("`target_id` char(36) NOT NULL,")
					.append("`target_name` varchar(500) NOT NULL COMMENT '资源或者微课名称',")
					.append("`target_school_id` char(36) NOT NULL COMMENT '资源或微课学校ID',")
					.append("`target_basic_type` int(11) NOT NULL DEFAULT '0' COMMENT '参考ResourceBasicTypeEnum',")
					.append("`type` int(11) NOT NULL,")
					.append("`school_id` char(36) NOT NULL COMMENT '用户所在学校ID',")
					.append("`create_time` datetime NOT NULL,")
					.append("`target_create_user` char(36) NOT NULL COMMENT '资源上传人',")
					.append("`target_create_user_name` varchar(50) NOT NULL COMMENT '资源上传人姓名',")
					.append("`target_file_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '资源文件类型',")
					.append("PRIMARY KEY (`id`),")
					.append("KEY `user_id` (`user_id`) USING BTREE,")
					.append("KEY `target_id` (`target_id`) USING BTREE")
					.append(") COLLATE='utf8mb4_general_ci' ENGINE=InnoDB;\r\n");
				fos.write(sb.toString().getBytes());
				beginMonth = DateUtil.formatDate(DateUtil.addMonth(DateUtil.parseDate(beginMonth, DateUtil.DATE_FORMAT), 1), DateUtil.DATE_FORMAT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void generateInsertSQL(String beginMonth) {
		String filePath="E:\\sql_plugins\\user_activity_log-insert.sql";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			String endMonth = DateUtil.formatDate(DateUtil.addMonth(new Date(), 1), DateUtil.DATE_FORMAT);
			StringBuffer sb = null;
			while (!beginMonth.equals(endMonth)) {
				sb = new StringBuffer();
				sb.append("insert into `nut_log`.`lg_user_activity_log_").append(beginMonth).append("` (")
					.append("select * from `nut_log`.`lg_user_activity_log` where DATE_FORMAT(create_time, '%Y%m')='")
					.append(beginMonth)
					.append("');\r\n");
				fos.write(sb.toString().getBytes());
				beginMonth = DateUtil.formatDate(DateUtil.addMonth(DateUtil.parseDate(beginMonth, DateUtil.DATE_FORMAT), 1), DateUtil.DATE_FORMAT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		UserActivityLogHelper.generateCreateSQL("201609");
		UserActivityLogHelper.generateInsertSQL("201609");
		System.out.println("--------------done!---------------");
	}
}
