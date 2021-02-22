package nut.log.testerrorredolog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import common.DateUtil;

public class TestErrorRedoLogCreateHelper {

	public static void generateCreateSQL(String yearMonth) {
		String filePath="E:\\sql_plugins\\log-create.sql";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath, true);
			String endMonth = DateUtil.formatDate(DateUtil.addMonth(new Date(), 3), DateUtil.DATE_FORMAT);
			StringBuffer sb = null;
			int totalCount = 0;
			while (!yearMonth.equals(endMonth)) {
			    totalCount++;
				sb = new StringBuffer();
				sb.append("CREATE TABLE IF NOT EXISTS `nut_log`.`lg_test_error_redo_log_result_").append(yearMonth).append("` (")
					.append("`id` char(36) NOT NULL,")
					.append("`type` tinyint(4) NOT NULL DEFAULT '1',")
					.append("`top_topic_code` char(36) NOT NULL,")
					.append("`topic_code` char(36) NOT NULL,")
					.append("`user_id` char(36) NOT NULL,")
					.append("`klass_id` char(36) NOT NULL DEFAULT '',")
					.append("`exam_id` char(36) NOT NULL,")
					.append("`exam_name` varchar(100) NOT NULL,")
					.append("`exam_type` varchar(10) NOT NULL,")
					.append("`from_type` tinyint(4) NOT NULL DEFAULT '1',")
					.append("`master_level` tinyint(4) NOT NULL DEFAULT '1',")
					.append("`grade_code` char(4) NOT NULL,")
					.append("`specialty_code` char(2) NOT NULL,")
					.append("`academic_year` int(11) NOT NULL DEFAULT '0',")
					.append("`question_count` int(11) NOT NULL DEFAULT '0',")
					.append("`right_count` double NOT NULL DEFAULT '0',")
					.append("`score` double NOT NULL DEFAULT '0',")
					.append("`auto_submit` tinyint(4) NOT NULL DEFAULT '2',")
					.append("`start_time` datetime NOT NULL,")
					.append("`end_time` datetime NOT NULL,")
					.append("`student_score` int(11) NOT NULL DEFAULT '0',")
					.append("`create_time` datetime NOT NULL,")
					.append("`school_id` varchar(36) NOT NULL,")
					.append("`user_name` varchar(255) DEFAULT NULL,")
					.append("`real_name` varchar(255) DEFAULT NULL,")
					.append("PRIMARY KEY (`id`),")
					.append("KEY `topic_code` (`topic_code`),")
					.append("KEY `user_id` (`user_id`)")
					.append(") COLLATE='utf8mb4_general_ci' ENGINE=InnoDB;\r\n");
				fos.write(sb.toString().getBytes());
				yearMonth = DateUtil.formatDate(DateUtil.addMonth(DateUtil.parseDate(yearMonth, DateUtil.DATE_FORMAT), 1), DateUtil.DATE_FORMAT);
			}
			System.err.println("--------------TestErrorRedoLogCreateHelper.generateCreateSQL【" + totalCount + "】 already done!---------------");
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
}
