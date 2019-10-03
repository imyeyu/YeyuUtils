package net.imyeyu.utils.interfaces;

import java.awt.Color;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;

/**
 * GUI interfaces
 * 
 * @author Yeyu
 *
 */
public interface GUI {

	public final Color BLACK = new Color(0, 0, 0);
	public final Color GRAY = new Color(155, 155, 155);
	public final Color RED = new Color(255, 48, 48);
	public final Color ORANGE = new Color(255, 153, 0);
	public final Color GREEN = new Color(51, 153, 51);
	public final Color PINK = new Color(255, 122, 155);
	
	/**
	 * User JLabel tips
	 * Demo: tips(label, "error", 3000, Tips.ERROR);
	 * 
	 * @param tips jlabel object
	 * @param content tips content
	 * @param time show time
	 * @param mode show mode
	 */
	public void tips(JLabel tips, String content, int time, int mode);
	
	/**
	 * Show debug panel
	 * Demo: debug("test");
	 * 
	 * @param s debug content
	 */
	public void debug(String s);
	
	/**
	 * Show exception gui when application has a deadly error
	 * User can report exception or exit application
	 * Demo: exception(new NullPointerException());
	 * 
	 * @param e exception
	 */
	public void exception(Exception e);
	
	/**
	 * Get file in explorer
	 * Demo: getFileInExplorer("C:\\", "exe");
	 * 
	 * @param path init open position
	 * @param format select file format
	 * @return file object
	 */
	public File getFileInExplorer(String path, String format);
	
	/**
	 * Get file in explorer
	 * Demo: getFileInExplorer("C:\\", true, "exe");
	 * 
	 * @param path init open position
	 * @param parent change path to parent dir
	 * @param format select file format
	 * @return file object
	 */
	public File getFileInExplorer(String path, boolean parent, String format);
	
	/**
	 * Get files in explorer
	 * Demo: getFilesInExplorer("C:\\", formats);
	 * 
	 * @param path init open position
	 * @param format select files format
	 * @return selected files
	 */
	public File[] getFilesInExplorer(String path, String[] formats);
	
	/**
	 * Get files in explorer
	 * Demo: getFilesInExplorer("C://", true, formats);
	 * 
	 * @param path init open position
	 * @param parent change path to parent dir
	 * @param format select files format
	 * @return selected files
	 */
	public File[] getFilesInExplorer(String path, boolean parent, String[] formats);
	
	/**
	 * Select folder in explorer
	 * Demo: getFolderInExplorer();
	 * 
	 * @param path init open position
	 * @return selected folder
	 */
	public String getFolderInExplorer(String path);
	
	/**
	 * Select folder in explorer
	 * Demo: getFolderInExplorer();
	 * 
	 * @param path init open position
	 * @param parent change path to parent dir
	 * @return selected folder
	 */
	public String getFolderInExplorer(String path, boolean parent);
	
	/**
	 * Get selected tag from button group
	 * Demo:  getSelectedByButtonGroup(group)
	 * 
	 * @param group button group
	 * @return selected tag
	 */
	public String getSelectedByButtonGroup(ButtonGroup group);
}
