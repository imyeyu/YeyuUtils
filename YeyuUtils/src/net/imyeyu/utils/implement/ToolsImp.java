package net.imyeyu.utils.implement;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.sun.management.OperatingSystemMXBean;

import net.imyeyu.utils.YeyuUtils;
import net.imyeyu.utils.interfaces.Tools;

public class ToolsImp implements Tools {

	public String cutString(String data, int length) {
		if (data.length() < length / 2) {
			return data;
		}
		int count = 0;
		StringBuffer sb = new StringBuffer();
		String[] array = data.split("");
		for (int i = 0; i < array.length; i++) {
			count += array[i].getBytes().length > 1 ? 2 : 1;
			sb.append(array[i]);
			if (count >= length) {
				break;
			}
		}
		return sb.toString();
	}

	public Map<Object, Object> randomMap(Map<Object, Object> map, int limit){
		Map<Object, Object> resultTemporary = new LinkedHashMap<Object, Object>();
		List<Map.Entry<Object, Object>> list = new ArrayList<Map.Entry<Object, Object>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Object, Object>>(){
			public int compare(Entry<Object, Object> lhs, Entry<Object, Object> rhs) {
				int randomOne = (int) (Math.random() * 10);
				int randomTwo = (int) (Math.random() * 10);
				return randomOne - randomTwo;
			}
		});
		for (int i = 0, l = list.size(); i < l; i++){
			Map.Entry<Object, Object> mapEntry = list.get(i);
			if (resultTemporary.size() < limit) {
				resultTemporary.put(mapEntry.getKey(), mapEntry.getValue());
			}
		}
		return resultTemporary;
	}

	public Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		class MapKeyComparator implements Comparator<String> {
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

	public int getSystemMemorySize() {
        OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long size = osmb.getTotalPhysicalMemorySize();
        size = size / 1024 / 1024;
        return (int) size;
	}

	public boolean findProcess(String processName, String threadName) {
		BufferedReader bufferedReader = null;
		try {
			Process proc = Runtime.getRuntime().exec("tasklist -v -fi " + '"' + "imagename eq " + processName + '"');
			bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith(processName) && line.indexOf(threadName) != -1) {
					return true;
				}
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		} catch (Exception e) {
			YeyuUtils.gui().exception(e);
		}
		return false;
	}

	public void setIntoClipboard(String content) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(content), null);
	}
}
