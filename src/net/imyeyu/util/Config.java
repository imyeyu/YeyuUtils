package net.imyeyu.util;

import java.util.Map;

/**
 * 配置对象，以 HashMap 储存配置，使用 get 数据类型简化 Map 的数据获取方式<br />
 * 请确保配置对象数据类型，否则有转化异常直接向 JVM 抛出
 * 
 * @author Yeyu
 *
 */
public class Config {
	
	private Map<String, Object> config;
	
	public Config(Map<String, Object> config) {
		this.config = config;
	}
	
	/**
	 * 检测是否存在该配置
	 * 
	 * @param key 配置键
	 * @return boolean
	 */
	public boolean has(String key) {
		return config.get(key) != null;
	}
	
	/**
	 * 获取整型数组
	 * 
	 * @param key 配置键
	 * @return int
	 */
	public int getInt(String key) {
		return Integer.parseInt(config.get(key).toString());
	}
	
	/**
	 * 以指定进制获取整型数据
	 * 
	 * @param key   配置键
	 * @param radix 进制
	 * @return int
	 */
	public int getInt(String key, int radix) {
		return Integer.parseInt(config.get(key).toString(), radix);
	}
	
	/**
	 * 获取长整型数据
	 * 
	 * @param key 配置键
	 * @return long
	 */
	public long getLong(String key) {
		return Long.parseLong(config.get(key).toString());
	}
	
	/**
	 * 不转换直接获取对象
	 * 
	 * @param key 配置键
	 * @return Object
	 */
	public Object getObject(String key) {
		return config.get(key);
	}
	
	/**
	 * 获取字符串数据
	 * 
	 * @param key 配置键
	 * @return String
	 */
	public String getString(String key) {
		return config.get(key).toString();
	}
	
	/**
	 * 获取单精度浮点型数据
	 * 
	 * @param key 配置键
	 * @return
	 */
	public float getFloat(String key) {
		return Float.valueOf(config.get(key).toString());
	}
	
	/**
	 * 获取双精度浮点数据
	 * 
	 * @param key 配置键
	 * @return double
	 */
	public double getDouble(String key) {
		return Double.parseDouble(config.get(key).toString());
	}
	
	/**
	 * 获取布尔型数据
	 * 
	 * @param key 配置键
	 * @return boolean
	 */
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(config.get(key).toString());
	}
	
	/**
	 * 获取以 "," 为分隔符的整型数组
	 * 
	 * @param key 配置键
	 * @return int 数组
	 */
	public int[] getInts(String key) {
		String[] strings = config.get(key).toString().split(",");
		int[] ints = new int[strings.length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(strings[i]);
		}
		return ints;
	}
	
	/**
	 * 获取以指定分隔符的整型数组
	 * 
	 * @param key   配置键
	 * @param split 分隔符
	 * @return int 数组
	 */
	public int[] getInts(String key, String split) {
		String[] strings = config.get(key).toString().split(split);
		int[] ints = new int[strings.length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(strings[i]);
		}
		return ints;
	}
	
	/**
	 * 获取以 "," 为分隔符的字符串数组
	 * 
	 * @param key 配置键
	 * @return String 数组
	 */
	public String[] getStrings(String key) {
		return config.get(key).toString().split(",");
	}
	
	/**
	 * 获取以指定分隔符的字符串数组
	 * 
	 * @param key   配置键
	 * @param split 分隔符
	 * @return String 数组
	 */
	public String[] getStrings(String key, String split) {
		return config.get(key).toString().split(split);
	}
	
	/**
	 * 修改指定配置
	 * 
	 * @param key   配置键
	 * @param value 配置值
	 */
	public void put(String key, Object value) {
		this.config.put(key, value);
	}

	/**
	 * 获取配置文件 HashMap
	 * 
	 * @return
	 */
	public Map<String, Object> getConfig() {
		return config;
	}

	/**
	 * 设置配置文件 HashMap
	 * 
	 * @param config
	 */
	public void setConfig(Map<String, Object> config) {
		this.config = config;
	}
}