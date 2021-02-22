package nut.statistic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import common.DateUtil;
import common.JdbcUtil;
import common.SqlHelper;
import dictionary.InsertTypeEnum;
import dictionary.StatisticTableEnum;
import dictionary.StudentDayTableEnum;
import domain.JdbcConnInfo;

public class StatisticInsertHelper {
    private static final String filePath = "E:\\sql_plugins\\statistic-insert.sql";
    private static Integer minMonth = null;
    private static final Integer limitSQL = 2000;

	public static String generateInsertSQL(int insertType, int serverType) {
        FileOutputStream fos = null;
        /*
         * generate StatisticTableEnum`s insert sql
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            fos = new FileOutputStream(filePath);
            List<JdbcConnInfo> connInfos = JdbcUtil.getConnInfos(serverType);
            Integer totalCount = 0;
            for (JdbcConnInfo connInfo : connInfos) {
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                conn = JdbcUtil.getConn(connInfo.getUrl(), connInfo.getUsername(), connInfo.getPassword());
                stmt = conn.createStatement();
                rs = stmt.executeQuery("select schema_name from information_schema.SCHEMATA where schema_name like 'nut%' and schema_name not in ('nut_center','nut_center_local','nut_center_local3','nut_job','nut_job_local','nut_log','nut_log_local','nut_message','nut_message_local','nut_resource','nut_resource_local','nut_spirit','nutfree','nutspiritcenter','nutnull');");
                List<String> dbList = new ArrayList<String>();
                while (rs.next()) {
                    dbList.add(rs.getString("schema_name"));
                }
                if (dbList.size() > 0) {
                    for (String dbName : dbList) {
                        DatabaseMetaData metaData = conn.getMetaData();
                        List<StatisticTableEnum> statisticTables = StatisticTableEnum.getEnumValues();
                        for (StatisticTableEnum statisticTable : statisticTables) {
                            if (insertType == InsertTypeEnum.EXCLUDE_CURRENT_MONTH.getValue() && StringUtils.isBlank(statisticTable.getFilterColumn())) continue;
                            //System.out.println(StringUtils.join(dbName, ".", statisticTable.getTable(), "-----already started!!!!-----"));
                            ResultSet resultSet = metaData.getTables(dbName, null, statisticTable.getTable(), new String[]{"TABLE"});// 判断该库中是否存在当前表
                            Integer count = 0;
                            if (resultSet.next()) {
                                StringBuilder sqlSB = new StringBuilder();
                                sqlSB.append("select ")
                                    .append(StringUtils.join(statisticTable.getColumns(), ","))
                                    .append(" from ")
                                    .append(dbName)
                                    .append(".")
                                    .append(statisticTable.getTable());
                                if (insertType == 0 || StringUtils.isBlank(statisticTable.getFilterColumn())) {// 全部
                                    sqlSB.append(";");
                                } else {
                                    sqlSB.append(" where ").append(statisticTable.getFilterColumn());
                                    if (StringUtils.equals("week_index", statisticTable.getFilterColumn())) {
                                        if (insertType == InsertTypeEnum.EXCLUDE_CURRENT_MONTH.getValue()) {// 排除当前月数据
                                            sqlSB.append(" not in ");
                                        } else if (insertType == InsertTypeEnum.CURRENT_MONTH.getValue()) {// 当前月数据
                                            sqlSB.append(" in ");
                                        }
                                        sqlSB.append("(");
                                        List<Integer> filterValues = getFilterValues(statisticTable.getFilterColumn());
                                        for (Integer filterValue : filterValues) {
                                            sqlSB.append(filterValue).append(",");
                                        }
                                        sqlSB.deleteCharAt(sqlSB.length() - 1);
                                        sqlSB.append(");");
                                    } else {
                                        if (StringUtils.equals("day_index", statisticTable.getFilterColumn())) {
                                            if (insertType == InsertTypeEnum.EXCLUDE_CURRENT_MONTH.getValue()) {// 排除当前月数据
                                                sqlSB.append(" < ");
                                            } else if (insertType == InsertTypeEnum.CURRENT_MONTH.getValue()) {// 当前月数据
                                                sqlSB.append(" > ");
                                            }
                                        } else {
                                            if (insertType == InsertTypeEnum.EXCLUDE_CURRENT_MONTH.getValue()) {// 排除当前月数据
                                                sqlSB.append(" <> ");
                                            } else if (insertType == InsertTypeEnum.CURRENT_MONTH.getValue()) {// 当前月数据
                                                sqlSB.append(" = ");
                                            }
                                        }
                                        sqlSB.append(getFilterValues(statisticTable.getFilterColumn()).get(0)).append(";");
                                    }
                                }
                                rs = stmt.executeQuery(sqlSB.toString());
                                List<Map<String,String>> pairList = new ArrayList<>();
                                while (rs.next()) {
                                    count++;
                                    pairList.add(SqlHelper.getColumnsPair(rs));
                                }
                                List<String> insertList = getInsertSQL(pairList, statisticTable, dbName);
                                if (insertList != null && insertList.size() > 0) {
                                    for (String insert : insertList) {
                                        fos.write(insert.getBytes());
                                    }
                                }
                            }
                            totalCount += count;
                            if (count > 0) System.out.println(StringUtils.join(dbName, ".", statisticTable.getTable(), "【", count.toString(), "】-----already done!!!!-----"));
                        }
                    }
                }
            }
            System.err.println(StringUtils.join("--------------StatisticInsertHelper.generateInsertSQL【", totalCount.toString(), "】-----already done!!!!-----"));
        } catch (Exception e) {
            e.printStackTrace();
            minMonth = null;
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

	private static List<Integer> getFilterValues(String filterColumn) {
	    List<Integer> filterValues = new ArrayList<Integer>();
	    Date now = new Date();
	    if (StringUtils.equals("day_index", filterColumn)) {
            filterValues.add(Integer.parseInt(DateUtil.yearMonthFormatDate(now))*100);
        } else if (StringUtils.equals("month_index", filterColumn)) {
	        filterValues.add(Integer.parseInt(DateUtil.yearMonthFormatDate(now)));
        } else if (StringUtils.equals("week_index", filterColumn)) {// 排除近4周数据
            filterValues.add(DateUtil.getYear(DateUtil.getFirstDayOfWeek(now)) * 100 + DateUtil.getWeekOfYear(now));
            filterValues.add(DateUtil.getYear(DateUtil.getFirstDayOfWeek(DateUtil.addWeek(now, -1))) * 100 + DateUtil.getWeekOfYear(DateUtil.addWeek(now, -1)));
            filterValues.add(DateUtil.getYear(DateUtil.getFirstDayOfWeek(DateUtil.addWeek(now, -2))) * 100 + DateUtil.getWeekOfYear(DateUtil.addWeek(now, -2)));
            filterValues.add(DateUtil.getYear(DateUtil.getFirstDayOfWeek(DateUtil.addWeek(now, -3))) * 100 + DateUtil.getWeekOfYear(DateUtil.addWeek(now, -3)));
        } else if (StringUtils.equals("year_index", filterColumn)) {
            filterValues.add(DateUtil.getYear(now));
        }
	    return filterValues;
	}

	/**
	 * @param pairList
	 * @param statisticTable
	 * @param dbName
	 * @return
	 */
	private static List<String> getInsertSQL(List<Map<String,String>> pairList, StatisticTableEnum statisticTable, String dbName) {
	    try {
	        if (pairList.size() == 0) return null;
	        Map<String, StringBuilder> insertMap = new HashMap<String, StringBuilder>();
	        for (int j=0;j<pairList.size();j++) {
	            Map<String, String> pair = pairList.get(j);
	            if (StatisticTableEnum.st_student_day_record.equals(statisticTable)) {
                    String monthYear = DateUtil.yearMonthFormatDate(DateUtil.parseDate(pair.get("create_time").substring(1, pair.get("create_time").length() - 1), DateUtil.default_pattern));
                    if (minMonth == null || minMonth > Integer.parseInt(monthYear)) {
                        minMonth = Integer.parseInt(monthYear);
                    }
                    String tableName = StringUtils.join(statisticTable.getTargetTable(), "_", StudentDayTableEnum.parse(dbName.split("_")[1]).getCode(), "_", monthYear);
                    String insertKey = StringUtils.join(tableName, Integer.valueOf(j / limitSQL).toString());
                    if (insertMap.get(insertKey) == null) {
                        insertMap.put(insertKey, new StringBuilder());
                        StringBuilder columnSB = new StringBuilder();
                        StringBuilder valueSB = new StringBuilder();
                        for (int k=0;k<statisticTable.getTargetColumns().size();k++) {
                            columnSB.append(statisticTable.getTargetColumns().get(k)).append(",");
                            valueSB.append(pair.get(statisticTable.getColumns().get(k))).append(",");
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
                        for (int k=0;k<statisticTable.getTargetColumns().size();k++) {
                            valueSB.append(pair.get(statisticTable.getColumns().get(k))).append(",");
                        }
                        valueSB.deleteCharAt(valueSB.length() - 1);
                        insertMap.get(insertKey)
                            .append(",\r\n(")
                            .append(valueSB.toString())
                            .append(")");
                    }
                } else {
                    String insertKey = StringUtils.join(statisticTable.getTargetTable(), Integer.valueOf(j / limitSQL).toString());
                    if (insertMap.get(insertKey) == null) {
                        insertMap.put(insertKey, new StringBuilder());
                        StringBuilder columnSB = new StringBuilder();
                        StringBuilder valueSB = new StringBuilder();
                        for (int k=0;k<statisticTable.getTargetColumns().size();k++) {
                            columnSB.append(statisticTable.getTargetColumns().get(k)).append(",");
                            valueSB.append(pair.get(statisticTable.getColumns().get(k))).append(",");
                        }
                        columnSB.deleteCharAt(columnSB.length() - 1);
                        valueSB.deleteCharAt(valueSB.length() - 1);
                        insertMap.get(insertKey)
                            .append("INSERT INTO nut_log.")
                            .append(statisticTable.getTargetTable())
                            .append(" (")
                            .append(columnSB.toString())
                            .append(") VALUES (")
                            .append(valueSB.toString())
                            .append(")");
                    } else {
                        StringBuilder valueSB = new StringBuilder();
                        for (int i=0;i<statisticTable.getTargetColumns().size();i++) {
                            valueSB.append(pair.get(statisticTable.getColumns().get(i))).append(",");
                        }
                        valueSB.deleteCharAt(valueSB.length() - 1);
                        insertMap.get(insertKey)
                            .append(",\r\n(")
                            .append(valueSB.toString())
                            .append(")");
                    }
                }
	        }
	        return insertMap.values().stream().map(i -> i.append(";\r\n").toString()).collect(Collectors.toList());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
