package com.zhou.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 周俊宇
 * 配置文件读取类,用于读取配置文件
 * fixme 所有输出栈可换成日志打印
 */
public class PropertiesUtils {
	/**
	 * 通过类路径读取文件
	 *
	 * @return
	 */
	public static Map<String, String> readInsideProperties(String path) {
		Properties properties = new Properties();
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		HashMap<String, String> result = new HashMap<String, String>((Map) properties);
		return result;
	}

	/**
	 * 通过流读取绝对路径的文件
	 * @param path
	 * @return
	 */
	public static Map<String, String> readOutsideProperties(String path) {
		Properties properties = new Properties();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("E:/config.properties"));
			properties.load(bufferedReader);
			HashMap<String, String> result = new HashMap<String, String>((Map) properties);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
