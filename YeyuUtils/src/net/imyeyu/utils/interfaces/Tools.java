package net.imyeyu.utils.interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Tools {
	
	/**
	 * Cur string
	 * Demo: cutString("text", 2);
	 * Return "te"
	 * 
	 * @param data text
	 * @param length cut length
	 * @param dot string last has "..." for set true, else or noting
	 * @return cut result
	 */
	public String cutString(String data, int length, boolean dot);
	
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
	 * Sort map by string key
	 * 
	 * @param map map object
	 * @return sort result
	 */
	public Map<String, Object> sortMapByStringKey(Map<String, Object> map);
	
	/**
	 * Sort map by long key
	 * 
	 * @param map map object
	 * @return sort result
	 */
	public Map<Long, String> sortMapByLongKey(Map<Long, String> map);
	
	/**
	 * Remove an item by key in file map
	 * 
	 * @param map remove map
	 * @param list remove key list
	 * @return remove result map
	 */
	public Map<String, File> removeFileMapByKey(Map<String, File> map, List<String> list);
	
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
