package common;

import java.util.Properties;

public class PropertiesUtil {
	static Properties properties = new Properties();
	public PropertiesUtil() {
	}
	public static boolean loadFile(String fileName){
		try {
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static String getPropertyValue(String key){
		return properties.getProperty(key);
	}
}
