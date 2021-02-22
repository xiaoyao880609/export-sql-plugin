package datacenter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import common.DateUtil;

public class TeacherScoreLogCreateHelper {
    private static final String filePath = "E:\\sql_plugins\\log-create.sql";
    
	public static void generateCreateSQL(String yearMonth) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			String endMonth = DateUtil.formatDate(DateUtil.addMonth(new Date(), 3), DateUtil.DATE_FORMAT);
			StringBuffer sb = null;
			int totalCount = 0;
			while (!yearMonth.equals(endMonth)) {
			    totalCount++;
				sb = new StringBuffer();
				sb.append("CREATE TABLE IF NOT EXISTS `nut_log`.`lg_teacher_score_log_result_").append(yearMonth).append("` (")
					.append("`id` char(36) NOT NULL,")
					.append("`class_room_id` char(36) NOT NULL DEFAULT '',")
					.append("`teacher_id` char(36) NOT NULL,")
					.append("`target_id` char(36) NOT NULL,")
					.append("`school_id` char(36) NOT NULL,")
					.append("`teacher_score_type` int(11) NOT NULL DEFAULT '0',")
					.append("`score` int(11) NOT NULL DEFAULT '0',")
					.append("`last_score` int(11) NOT NULL DEFAULT '0',")
					.append("`use_device_type` tinyint(4) NOT NULL DEFAULT '1',")
					.append("`create_time` datetime NOT NULL,")
					.append("`create_user` char(36) NOT NULL DEFAULT '',")
					.append("`academic_year` int(11) NOT NULL,")
					.append("`user_name` varchar(50) DEFAULT NULL,")
					.append("`real_name` varchar(50) DEFAULT NULL,")
					.append("`create_user_real_name` varchar(50) DEFAULT NULL,")
					.append("`school_name` varchar(100) NOT NULL,")
					.append("PRIMARY KEY (`id`),")
					.append("UNIQUE KEY `teacher_id_target_id_teacher_score_type` (`teacher_id`,`target_id`,`teacher_score_type`,`create_user`) USING BTREE,")
					.append("KEY `class_room_id` (`class_room_id`) USING BTREE,")
					.append("KEY `target_id` (`target_id`) USING BTREE")
					.append(") COLLATE='utf8mb4_general_ci' ENGINE=InnoDB;\r\n");
				fos.write(sb.toString().getBytes());
				yearMonth = DateUtil.formatDate(DateUtil.addMonth(DateUtil.parseDate(yearMonth, DateUtil.DATE_FORMAT), 1), DateUtil.DATE_FORMAT);
			}
			System.err.println("--------------TeacherScoreLogCreateHelper.generateCreateSQL【" + totalCount + "】 already done!---------------");
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
