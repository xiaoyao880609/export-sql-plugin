package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dictionary.ServerTypeEnum;
import domain.JdbcConnInfo;


public class JdbcUtil {

	public static List<JdbcConnInfo> getConnInfos(int serverType) {
	    PropertiesUtil.loadFile("jdbc.properties");
	    List<JdbcConnInfo> list = new ArrayList<JdbcConnInfo>();
	    if (ServerTypeEnum.DEV.getValue() == serverType) {
	        JdbcConnInfo jdbcConnInfo = new JdbcConnInfo();
	        jdbcConnInfo.setUrl(PropertiesUtil.getPropertyValue("dev.jdbc.url"));
	        jdbcConnInfo.setUsername(PropertiesUtil.getPropertyValue("dev.jdbc.username"));
	        jdbcConnInfo.setPassword(PropertiesUtil.getPropertyValue("dev.jdbc.password"));
	        list.add(jdbcConnInfo);
	    } else if (ServerTypeEnum.REAL.getValue() == serverType) {
	        JdbcConnInfo jdbcConn3401 = new JdbcConnInfo();
	        jdbcConn3401.setUrl(PropertiesUtil.getPropertyValue("ali.real.nut3401.jdbc.url"));
	        jdbcConn3401.setUsername(PropertiesUtil.getPropertyValue("ali.real.nut3401.jdbc.username"));
	        jdbcConn3401.setPassword(PropertiesUtil.getPropertyValue("ali.real.nut3401.jdbc.password"));
	        JdbcConnInfo jdbcConn3402 = new JdbcConnInfo();
	        jdbcConn3402.setUrl(PropertiesUtil.getPropertyValue("ali.real.nut3402.jdbc.url"));
	        jdbcConn3402.setUsername(PropertiesUtil.getPropertyValue("ali.real.nut3402.jdbc.username"));
	        jdbcConn3402.setPassword(PropertiesUtil.getPropertyValue("ali.real.nut3402.jdbc.password"));
	        JdbcConnInfo jdbcConn3403 = new JdbcConnInfo();
	        jdbcConn3403.setUrl(PropertiesUtil.getPropertyValue("ali.real.nut3403.jdbc.url"));
	        jdbcConn3403.setUsername(PropertiesUtil.getPropertyValue("ali.real.nut3403.jdbc.username"));
	        jdbcConn3403.setPassword(PropertiesUtil.getPropertyValue("ali.real.nut3403.jdbc.password"));
	        list.add(jdbcConn3401);
	        list.add(jdbcConn3402);
	        list.add(jdbcConn3403);
        }
	    return list;
	}

	public static Connection getConn(String vUrl, String vUsername, String vPassword){
        PropertiesUtil.loadFile("jdbc.properties");
        String driver = PropertiesUtil.getPropertyValue("jdbc.driver");
        String url = StringUtils.join(vUrl, "?characterEncoding=utf-8");
        String username = vUsername;
        String password = vPassword;
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	public static Connection getConn4Center(int serverType){
	    PropertiesUtil.loadFile("jdbc.properties");
        String driver = PropertiesUtil.getPropertyValue("jdbc.driver");
        String url = null;
        String username = null;
        String password = null;
	    if (ServerTypeEnum.DEV.getValue() == serverType) {
	        url = PropertiesUtil.getPropertyValue("dev.jdbc.url");
	        username = PropertiesUtil.getPropertyValue("dev.jdbc.username");
	        password = PropertiesUtil.getPropertyValue("dev.jdbc.password");
	    } else if (ServerTypeEnum.REAL.getValue() == serverType) {
	        url = PropertiesUtil.getPropertyValue("ali.real.center.jdbc.url");
            username = PropertiesUtil.getPropertyValue("ali.real.center.jdbc.username");
            password = PropertiesUtil.getPropertyValue("ali.real.center.jdbc.password");
	    }
	    if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
	        try {
	            Class.forName(driver);
	            return DriverManager.getConnection(StringUtils.join(url, "?characterEncoding=utf-8"),username,password);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return null;
	}
	
	public static Connection getConn4JiaoYuYun(){
	    Connection conn = null;
		PropertiesUtil.loadFile("jdbc.properties");
		String driver = PropertiesUtil.getPropertyValue("jdbc.driver");
		String url = PropertiesUtil.getPropertyValue("jiaoyu.real.jdbc.url");
		String username = PropertiesUtil.getPropertyValue("jiaoyu.real.jdbc.username");
		String password = PropertiesUtil.getPropertyValue("jiaoyu.real.jdbc.password");
		try {
			Class.forName(driver);
			DriverManager.getConnection(url,username,password);
			conn =  DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
