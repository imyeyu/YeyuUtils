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
	 * 
	 * @param map files list
	 */
	public CheckFiles(Map<String, String> map) {
		this.map = map;
	}
	
	public void run() {
		try {
			File file;
			for (Map.Entry<String, String> item : map.entrySet()) {
				file = new File(item.getKey());
				if (item.getValue().toString().equals("-1")) {
					file.mkdirs();
				} else {
					if (!file.exists()) {
						YeyuUtils.file().stringToFile(file, item.getValue());
					}
				}
			}
		} catch (Exception e) {
			YeyuUtils.gui().exception(e);
		}
	}
}
