package executor;

import org.apache.commons.lang3.StringUtils;

import common.DateUtil;
import dictionary.InsertTypeEnum;
import dictionary.ServerTypeEnum;
import nut.statistic.StatisticInsertHelper;
import nut.statistic.StudentDayRecordCreateHelper;

public class NutStatisticExecutor {
    private static int insertType = InsertTypeEnum.ALL.getValue();// ALL, EXCLUDE_CURRENT_MONTH, CURRENT_MONTH
    private static int serverType = ServerTypeEnum.DEV.getValue();// DEV, REAL

	public static void main(String[] args) {
	    long startMillis = System.currentTimeMillis();
	    String minMonth = StatisticInsertHelper.generateInsertSQL(insertType, serverType);
	    if (StringUtils.isNumeric(minMonth)) {
	        StudentDayRecordCreateHelper.generateCreateSQL(minMonth);
	    }
	    long endMillis = System.currentTimeMillis();
        System.err.println("NutStatisticExecutor 耗时 >>>>> " + DateUtil.getTimeFormat(endMillis - startMillis));
	}
}
