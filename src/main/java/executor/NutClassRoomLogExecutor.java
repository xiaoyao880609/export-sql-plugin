package executor;

import common.DateUtil;
import dictionary.InsertTypeEnum;
import dictionary.ServerTypeEnum;
import nut.classroomlog.ClassRoomLogInsertHelper;

public class NutClassRoomLogExecutor {
    private static int insertType = InsertTypeEnum.CURRENT_MONTH.getValue();// ALL, EXCLUDE_CURRENT_MONTH, CURRENT_MONTH
    private static int serverType = ServerTypeEnum.REAL.getValue();// DEV, REAL

	public static void main(String[] args) {
	    long startMillis = System.currentTimeMillis();
	    ClassRoomLogInsertHelper.generateInsertSQL(insertType, serverType);
	    long endMillis = System.currentTimeMillis();
        System.err.println("NutClassRoomLogExecutor 耗时 >>>>> " + DateUtil.getTimeFormat(endMillis - startMillis));
	}
}
