package net.imyeyu.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleX {
	
	private ResourceBundle rb;

	public ResourceBundleX(String path, String lang) {
		String[] l = lang.split("_");
		Locale.setDefault(new Locale(l[0], l[1]));
		rb = ResourceBundle.getBundle(path);
	}
	
	public String def(String k) {
		return rb.getString(k);
	}
	
	public String def(String k, Object v) {
		return rb.getString(k).replaceAll("%v%", v.toString());
	}
	
	public String def(String k, Object... v) {
		String str = rb.getString(k);
		for (int i = 0; i < v.length; i++) {
			str = str.replaceAll("%v" + i + "%", v[i].toString());
		}
		return str;
	}
	
	public String l(String k) {
		return " " + rb.getString(k);
	}
	
	public String r(String k) {
		return rb.getString(k) + " ";
	}
}