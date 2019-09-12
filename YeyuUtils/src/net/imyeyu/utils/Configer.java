package net.imyeyu.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configer {
	
	private final String APP_NAME;
	
	public Configer() {
		this.APP_NAME = new Propertier().getValue("appName");
	}

	public void set(Map<String, Object> map) {
		map = YeyuUtils.tools().sortMapByKey(map);
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue() + "\r\n");
		}
		YeyuUtils.file().stringToFile(new File(APP_NAME + ".ini"), sb.toString());
	}

	public Map<String, Object> get() {
		Map<String, Object> map = new HashMap<String, Object>();
		String data = YeyuUtils.file().fileToString(new File(APP_NAME + ".ini"), "UTF-8");
		String[] configs = data.split("\r\n|[\r\n]");
		String key, value;
		for (int i = 0, l = configs.length; i < l; i++) {
			key = configs[i].substring(0, configs[i].indexOf("="));
			value = configs[i].substring(configs[i].indexOf("=") + 1, configs[i].length());
			map.put(key, value);
		}
		return map;
	}
}
