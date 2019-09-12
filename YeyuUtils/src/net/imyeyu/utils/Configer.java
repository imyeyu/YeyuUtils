package net.imyeyu.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configer {
	
	private final String APP_NAME;
	
	public Configer() {
		this.APP_NAME = new Propertier().getValue("appName");
	}

	public void setConfig(Map<String, Object> map) {
		map = YeyuUtils.tools().sortMapByKey(map);
		YeyuUtils.file().stringToFile(new File(APP_NAME + ".ini"), configToString(map));
	}
	
	public String configToString(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		int i = 0, size = map.size();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (i < size) {
				sb.append(entry.getKey() + "=" + entry.getValue() + "\r\n");
			} else {
				sb.append(entry.getKey() + "=" + entry.getValue());
			}
		}
		return sb.toString();
	}

	public Map<String, Object> getConfig() {
		Map<String, Object> map = new HashMap<String, Object>();
		String data = YeyuUtils.file().fileToString(new File(APP_NAME + ".ini"), "UTF-8");
		String[] config = data.split("\r\n");
		String[] keyValue = null;

		for (int i = 0, l = config.length; i < l; i++) {
			keyValue = config[i].split("=");
			if (keyValue.length > 1) {
				map.put(keyValue[0], keyValue[1]);
			} else {
				map.put(keyValue[0], "");
			}
		}
		
		return map;
	}
	
	public void setDefaultConfig(Map<String, Object> map) {
		setConfig(YeyuUtils.tools().sortMapByKey(map));
	}
}
