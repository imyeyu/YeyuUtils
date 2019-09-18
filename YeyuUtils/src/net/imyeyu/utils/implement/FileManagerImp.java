package net.imyeyu.utils.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import net.imyeyu.utils.YeyuUtils;
import net.imyeyu.utils.interfaces.FileManager;

public class FileManagerImp implements FileManager {

	public String getJarAbsolutePath(Object obj) {
		String path = obj.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			YeyuUtils.gui().exception(e);
		}       
		if (path.endsWith(".jar")) {
			path = path.substring(0, path.lastIndexOf("/") + 1);
		}
		File file = new File(path);
		path = file.getAbsolutePath();
		return path;
	}

	public String[] getDirList(File dir) {
		if (dir.isDirectory()) {
			File next[] = dir.listFiles();
			String[] fileName = new String[next.length];
			for (int i = 0, l = next.length; i < l; i++) {
				fileName[i] = next[i].getName();
			}
			return fileName;
		}
		return null;
	}

	public String fileToString(File file, String charset) {
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, charset);
			BufferedReader br = new BufferedReader(isr);
			String input;
			while ((input = br.readLine()) != null) {
				sb.append(input + "\r\n");
			}
			br.close();
			isr.close();
			fis.close();
		} catch (UnsupportedEncodingException e) {
			YeyuUtils.gui().exception(e);
		} catch (IOException e) {
			YeyuUtils.gui().exception(e);
		}
		return sb.toString();
	}

	public void stringToFile(File file, String data) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(data);
			bw.flush();
			bw.close();
			osw.close();
			fos.close();
		} catch (IOException e) {
			YeyuUtils.gui().exception(e);
		} catch (Exception e) {
			YeyuUtils.gui().exception(e);
		}
	}

	public String jarFileToString(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String input;
			while ((input = br.readLine()) != null) {
				sb.append(input + "\r\n");
			}
			br.close();
			isr.close();
			is.close();
		} catch (UnsupportedEncodingException e) {
			YeyuUtils.gui().exception(e);
		} catch (IOException e) {
			YeyuUtils.gui().exception(e);
		}
		return sb.toString();
	}

	public void hidenFile(File[] files) {
		try {
			for (int i = 0, l = files.length; i < l; i++) {
				if (files[i].isFile()) {
					if (files[i].exists()) {
						Runtime.getRuntime().exec("attrib " + "\"" + files[i].getAbsolutePath() + "\""+ " +H");
					}
				} else {
					if (files[i].exists()) {
						Runtime.getRuntime().exec("attrib " + "\"" + files[i].getAbsolutePath() + "\""+ " +H");
					}
				}
			}
		} catch (IOException e) {
			YeyuUtils.gui().exception(e);
		}
	}
}
