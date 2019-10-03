package net.imyeyu.utils.interfaces;

import java.io.File;
import java.util.Map;

public interface Tools {
	
	/**
	 * Cur string
	 * Demo: cutString("text", 2);
	 * Return "te"
	 * 
	 * @param data text
	 * @param length cut length
	 * @return cut result
	 */
	public String cutString(String data, int length);
	
	/**
	 * Random the map
	 * 
	 * @param map map object
	 * @param limit return size limit(return all for use map.size())
	 * @return random result
	 */
	public Map<Object, Object> randomMap(Map<Object, Object> map, int limit);
	
	/**
	 * Random the file map
	 * 
	 * @param map map object
	 * @return random result
	 */
	public Map<String, File> randomFileMap(Map<String, File> map);
	
	/**
	 * Sort map by key
	 * 
	 * @param map map object
	 * @return sort result
	 */
	public Map<String, Object> sortMapByKey(Map<String, Object> map);
	
	/**
	 * Get system memory max size
	 * 
	 * @return memory max size, unit MB
	 */
	public int getSystemMemorySize();
	
	/**
	 * Find the process (Windows os)
	 * Demo: findProcess("javaw.exe", "Minecraft 1.14.2");
	 * 
	 * @param processName process name
	 * @param threadName thread name
	 * @return true is exist, else false
	 */
	public boolean findProcess(String processName, String threadName);
	
	/**
	 * Set text into system clip board
	 * Demo: setIntoClipboard("text");
	 * 
	 * @param content text
	 */
	public void setIntoClipboard(String content);
}
