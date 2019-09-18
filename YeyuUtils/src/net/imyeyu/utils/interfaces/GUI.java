package net.imyeyu.utils.interfaces;

import java.awt.Color;

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
	 * Get selected tag from button group
	 * Demo:  getSelectedByButtonGroup(group)
	 * 
	 * @param group button group
	 * @return selected tag
	 */
	public String getSelectedByButtonGroup(ButtonGroup group);
}
