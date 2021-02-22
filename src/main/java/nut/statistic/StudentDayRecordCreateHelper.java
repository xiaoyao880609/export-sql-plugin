package nut.statistic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import common.DateUtil;

public class StudentDayRecordCreateHelper {
    private static final List<String> codeList = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private static final String filePath = "E:\\sql_plugins\\student_day_record-create.sql";
	public static void generateCreateSQL(String yearMonth) {
	    
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			String endMonth = DateUtil.formatDate(DateUtil.addMonth(new Date(), 3), DateUtil.DATE_FORMAT);
			StringBuffer sb = null;
			int totalCount = 0;
			while (!yearMonth.equals(endMonth)) {
			    for (String code : codeList) {
			        totalCount++;
			        sb = new StringBuffer();
			        sb.append("CREATE TABLE IF NOT EXISTS `nut_log`.`st_student_day_record_")
			        .append(code)
			        .append("_")
			        .append(yearMonth).append("` (")
			        .append("`id` varchar(100) NOT NULL COMMENT '主键（student_id + day_index）',")
			        .append("`day_index` int(11) NOT NULL DEFAULT '0',")
			        .append("`login_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`down_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`weike_length` int(11) NOT NULL DEFAULT '0',")
			        .append("`weike_count` int(11) NOT NULL,")
			        .append("`test_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`discuss_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`homework_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`class_room_experience_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`classroom_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`user_task_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`ask_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`academic_year` int(11) DEFAULT NULL,")
			        .append("`school_id` char(36) NOT NULL DEFAULT '',")
			        .append("`student_id` char(36) NOT NULL,")
			        .append("`sync_flag` tinyint(4) NOT NULL DEFAULT '2',")
			        .append("`grade_code` char(4) NOT NULL,")
			        .append("`tenant_code` varchar(30) NOT NULL,")
			        .append("`modify_time` datetime NOT NULL,")
			        .append("`create_time` datetime NOT NULL,")
			        .append("`class_room_train_length` int(11) NOT NULL DEFAULT '0',")
			        .append("`class_room_video_test_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`class_room_train_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_video_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_video_length` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_video_test_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_sync_test_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_sync_test_rate` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_sync_test_question_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_sync_test_right_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`train_down_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_video_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_video_length` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_video_test_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_sync_test_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_sync_test_rate` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_sync_test_question_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_sync_test_right_count` int(11) NOT NULL DEFAULT '0',")
			        .append("`megrez_down_count` int(11) NOT NULL DEFAULT '0',")
			        .append("PRIMARY KEY (`id`),")
			        .append("KEY `idx_tenantCode` (`tenant_code`) USING BTREE")
			        .append(") COLLATE='utf8mb4_general_ci' ENGINE=InnoDB;\r\n");
			        fos.write(sb.toString().getBytes());
			    }
			    yearMonth = DateUtil.formatDate(DateUtil.addMonth(DateUtil.parseDate(yearMonth, DateUtil.DATE_FORMAT), 1), DateUtil.DATE_FORMAT);
			}
			System.err.println("--------------StudentDayRecordCreateHelper.generateCreateSQL【" + totalCount + "】 already done!---------------");
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
