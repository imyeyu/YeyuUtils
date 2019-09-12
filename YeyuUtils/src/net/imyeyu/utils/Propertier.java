package net.imyeyu.utils;

import java.util.HashMap;
import java.util.Map;

public class Propertier {
	
	public String getValue(String key) {
		String v = "Unknown";
		try {
			Map<String, String> map = getProperties();
			v = map.get(key).trim();
		} catch (Exception e) {
			return v;
		}
		return v;
	}
	
	public Map<String, String> getProperties() {
		Map<String, String> map = new HashMap<String, String>();
		String s = YeyuUtils.file().jarFileToString("YeyuUtils.properties");
		String[] a = s.split("\n");
		String[] o = null;
		for (int i = 0; i < a.length; i++) {
			o = a[i].split("=");
			map.put(o[0], o[1]);
		}
		return map;
	}
}
