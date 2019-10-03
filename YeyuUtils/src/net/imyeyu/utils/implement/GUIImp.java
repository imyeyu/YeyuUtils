package net.imyeyu.utils.implement;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import net.imyeyu.utils.gui.Debug;
import net.imyeyu.utils.gui.Error;
import net.imyeyu.utils.gui.ExplorerFile;
import net.imyeyu.utils.gui.ExplorerFolder;
import net.imyeyu.utils.gui.Tips;
import net.imyeyu.utils.interfaces.GUI;

public class GUIImp implements GUI {

	public void tips(JLabel tips, String content, int time, int mode) {
		new Tips(tips, content, time, mode).start();
	}
	
	public void debug(String s) {
		Debug debug = new Debug();
		debug.setDebug(s);
		debug.setVisible(true);
	}
	
	public void exception(Exception e) {
		Error error = new Error();
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		error.error("Exception: \n" + sw.toString());
		error.setVisible(true);
	}

	public File getFileInExplorer(String path, String format) {
		return new ExplorerFile().open(path, format);
	}

	public File getFileInExplorer(String path, boolean parent, String format) {
		return new ExplorerFile().open(path, parent, format);
	}

	public File[] getFilesInExplorer(String path, String[] formats) {
		return new ExplorerFile().open(path, formats);
	}

	public File[] getFilesInExplorer(String path, boolean parent, String[] formats) {
		return new ExplorerFile().open(path, parent, formats);
	}

	public String getFolderInExplorer(String path) {
		return new ExplorerFolder().open(path);
	}

	public String getFolderInExplorer(String path, boolean parent) {
		return new ExplorerFolder().open(path, parent);
	}

	public String getSelectedByButtonGroup(ButtonGroup group) {
		String level = "";
		Enumeration<AbstractButton> radioBtns = group.getElements();  
		while (radioBtns.hasMoreElements()) {  
			AbstractButton btn = radioBtns.nextElement();  
		    if (btn.isSelected()) {  
		    	level = btn.getText();  
		        break;
		    }  
		}
		return level;
	}
}
