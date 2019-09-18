package net.imyeyu.utils.gui;

import java.awt.Color;

import javax.swing.JLabel;

import net.imyeyu.utils.YeyuUtils;

public class Tips extends Thread {
	
	private JLabel tips;
	private String content;
	private int time;
	private int mode;
	
	public static final int DEFAULT = 0;
	public static final int ERROR = 1;
	
	public Tips(JLabel tips, String content, int time, int mode) {
		this.tips = tips;
		this.content = content;
		this.time = time;
		this.mode = mode;
	}
	
	public void run() {
		try {
			switch (mode) {
				case DEFAULT:
					tips.setText(content);
					sleep(time);
					tips.setText(" ");
					break;
				case ERROR:
					tips.setForeground(net.imyeyu.utils.interfaces.GUI.RED);
					tips.setText(content);
					sleep(time);
					tips.setText(" ");
					break;
			}
			tips.setForeground(new Color(122, 138, 153));
		} catch (InterruptedException e) {
			YeyuUtils.gui().exception(e);
		}
	}
}
