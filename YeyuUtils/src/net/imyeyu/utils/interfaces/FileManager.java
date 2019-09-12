package net.imyeyu.utils.interfaces;

import java.io.File;

/**
 * File manager interfaces
 * 
 * @author Yeyu
 *
 */
public interface FileManager {
	
	/**
	 * Get this application running absolute path
	 * Param must use "this" keyword
	 * Demo: getJarAbsolutePath(this);
	 * 
	 * @param obj "this" keyword
	 * @return absolute path
	 */
	public String getJarAbsolutePath(Object obj);

	/**
	 * Get file list in dir
	 * Demo: getDirList(new File("C://"));
	 * 
	 * @param dir file object
	 * @return file list array
	 */
	public String[] getDirList(File dir);

	/**
	 * Read disk file to string
	 * Demo: fileToString(new File("test.txt"));
	 * 
	 * @param file file object
	 * @param charset file charset
	 * @return string when read finish
	 */
	public String fileToString(File file, String charset);

	/**
	 * Write string to disk file
	 * Demo: stringToFile(new File("test.txt"), "test data");
	 * 
	 * @param file file object
	 * @param data string data
	 */
	public void stringToFile(File file, String data);

	/**
	 * Read file from jar application
	 * Demo: jarFileToString("net/imyeyu/test");
	 * 
	 * @param path file path in jar application
	 * @return read result string
	 */
	public String jarFileToString(String path);
	
	/**
	 * Hiden files (Windows os)
	 * Demo: hidenFile(files);
	 * 
	 * @param files file array object
	 */
	public void hidenFile(File[] files);
}
