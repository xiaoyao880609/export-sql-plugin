package executor;

import org.apache.commons.lang3.StringUtils;

import common.DateUtil;
import dictionary.InsertTypeEnum;
import dictionary.ServerTypeEnum;
import nut.log.teacherscorelog.TeacherScoreLogCreateHelper;
import nut.log.teacherscorelog.TeacherScoreLogInsertHelper;
import nut.log.testerrorredolog.TestErrorRedoLogCreateHelper;
import nut.log.testerrorredolog.TestErrorRedoLogInsertHelper;

public class NutLogExecutor {
    private static int insertType = InsertTypeEnum.ALL.getValue();// ALL, EXCLUDE_CURRENT_MONTH, CURRENT_MONTH
    private static int serverType = ServerTypeEnum.DEV.getValue();// DEV, REAL

	public static void main(String[] args) {
	    long startMillis = System.currentTimeMillis();
	    // teacher_score_log
	    String teacherScoreLogMinMonth = TeacherScoreLogInsertHelper.generateInsertSQL(insertType, serverType);
	    if (StringUtils.isNumeric(teacherScoreLogMinMonth)) {
	        TeacherScoreLogCreateHelper.generateCreateSQL(teacherScoreLogMinMonth);
	    }
	    // test_error_redo_log
	    String testErrorRedoLogMinMonth = TestErrorRedoLogInsertHelper.generateInsertSQL(insertType, serverType);
	    if (StringUtils.isNumeric(testErrorRedoLogMinMonth)) {
	        TestErrorRedoLogCreateHelper.generateCreateSQL(testErrorRedoLogMinMonth);
	    }
	    long endMillis = System.currentTimeMillis();
	    System.err.println("NutLogExecutor 耗时 》》 " + DateUtil.getTimeFormat(endMillis - startMillis));
	}
	
}
