package net.imyeyu.utils;

import java.io.File;
import java.util.Map;

/**
 * Create folder or file when it not exist
 * 
 * @author Yeyu
 *
 */
public class CheckFiles extends Thread {
	
	private static final String FOLDER = "-1";
	private static final String JAR_FILE = "-2";
	
	private Map<String, String> map;

	/**
	 * Init class
	 * Demo: new CheckFiles(map);
	 * .start() object to run method
	 * 
	 * Map key and value:
	 * map.put("file.exe", fileData);
	 * map.put("folder", "-1");
	 *
	 * When check file is folder, map value use "-1"
	 * When check file init data is in jar, map value use "-2",
	 * And key format: map.put("net/imyeyu/utils/res/img.png$imgs/img.png", "-2")
	 * 
	 * @param map files list
	 */
	public CheckFiles(Map<String, String> map) {
		this.map = map;
	}
	
	public void run() {
		try {
			File file;
			String[] jarToDiskKV;
			for (Map.Entry<String, String> item : map.entrySet()) {
				if (item.getValue().equals(FOLDER)) {
					file = new File(item.getKey());
					file.mkdirs();
				}
			}
			for (Map.Entry<String, String> item : map.entrySet()) {
				file = new File(item.getKey());
				switch (item.getValue()) {
					case JAR_FILE:
						jarToDiskKV = item.getKey().split("\\$");
						if (!new File(jarToDiskKV[1]).exists()) {
							YeyuUtils.file().jarFileToDisk(jarToDiskKV[0], jarToDiskKV[1]);
						}
						break;
					default:
						if (!file.exists()) {
							YeyuUtils.file().stringToFile(file, item.getValue());
						}
						break;
				}
			}
		} catch (Exception e) {
			YeyuUtils.gui().exception(e);
		}
	}
}
