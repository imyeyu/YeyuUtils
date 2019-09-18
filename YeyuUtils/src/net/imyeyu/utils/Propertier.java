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
		String[] a = s.split("\r\n|[\r\n]");
		String k, v;
		for (int i = 0, l = a.length; i < l; i++) {
			k = a[i].substring(0, a[i].indexOf("="));
			v = a[i].substring(a[i].indexOf("=") + 1, a[i].length());
			map.put(k, v);
		}
		return map;
	}
}
