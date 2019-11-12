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
		StringBuffer sb = new StringBuffer();
		String d = YeyuUtils.file().fileToString(new File(APP_NAME + ".ini"), "UTF-8");
		String[] c = d.split("\r\n|[\r\n]");
		String k;
		for (int i = 0, l = c.length; i < l; i++) {
			if (c[i].equals("")) {
				sb.append("\r\n");
				continue;
			}
			if (!c[i].startsWith("#")) {
				k = c[i].substring(0, c[i].indexOf("="));
				if (!map.get(k).toString().equals("")) {
					sb.append(k + "=" + map.get(k) + "\r\n");
				}
			} else {
				sb.append(c[i] + "\r\n");
			}
		}
		YeyuUtils.file().stringToFile(new File(APP_NAME + ".ini"), sb.toString());
	}

	public Map<String, Object> get() {
		Map<String, Object> map = new HashMap<String, Object>();
		String d = YeyuUtils.file().fileToString(new File(APP_NAME + ".ini"), "UTF-8");
		String[] c = d.split("\r\n|[\r\n]");
		String k, v;
		for (int i = 0, l = c.length; i < l; i++) {
			if (c[i].length() != 0 && !c[i].startsWith("#")) {
				k = c[i].substring(0, c[i].indexOf("="));
				v = c[i].substring(c[i].indexOf("=") + 1, c[i].length());
				map.put(k, v);
			}
		}
		return map;
	}
}
