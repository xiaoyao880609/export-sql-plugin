package common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SqlHelper {

    public static Map<String,String> getColumnsPair(ResultSet rs) throws SQLException {
        Map<String,String> result = new HashMap<String, String>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for(int i=1;i<=columnCount;i++){
            result.put(metaData.getColumnLabel(i), getColumnValue(metaData.getColumnLabel(i),metaData.getColumnType(i),i,rs));
        }
        return result;
    }
    
    private static String getColumnValue(String columnName,int columnType,int i,ResultSet rs) throws SQLException {
        switch(columnType){
            case Types.NUMERIC :return rs.getLong(i) + "";
            case Types.VARCHAR:return (rs.getString(i) == null) ? null : "'"+StringUtils.replaceAll(rs.getString(i), "'", "\\\\'")+"'";
            case Types.DATE:return (rs.getDate(i) == null) ? null:"'"+DateUtil.defaultFormatYMDDate(rs.getDate(i))+"'";
            case Types.TIMESTAMP:return (rs.getTimestamp(i) == null) ? null:"'"+DateUtil.defaultFormatDate(rs.getTimestamp(i))+"'";
            //case Types.TIME:return rs.getTime(i);
            case Types.BOOLEAN:return rs.getInt(i) + "";
            //case Types.ARRAY :return rs.getArray(i);
            case Types.BIGINT :return rs.getLong(i) + "";
            //case Types.BINARY:return rs.getBinaryStream(i);
            //case Types.BLOB:return rs.getBlob(i);
            case Types.CHAR:return (rs.getString(i) == null) ? null : "'"+StringUtils.replaceAll(rs.getString(i), "'", "\\\\'")+"'";
            case Types.INTEGER:return rs.getInt(i) + "";
            case Types.DOUBLE :return rs.getDouble(i) + "";
            case Types.FLOAT:return rs.getFloat(i) + "";
            case Types.BIT:return rs.getInt(i) + "";
            case Types.SMALLINT:return rs.getInt(i) + "";
            case Types.TINYINT:return rs.getInt(i) + "";
            case Types.DECIMAL:return rs.getLong(i) + "";
            default:return null;
        }
    }
    
}
