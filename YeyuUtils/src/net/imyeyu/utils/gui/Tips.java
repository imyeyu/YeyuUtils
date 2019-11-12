package net.imyeyu.utils.gui;

import java.awt.Color;

import javax.swing.JLabel;

import net.imyeyu.utils.YeyuUtils;
import net.imyeyu.utils.interfaces.GUIX;

public class Tips extends Thread {
	
	private JLabel tips;
	private String content;
	private int time;
	private int mode;
	
	public static final int DEFAULT = 0;
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	
	
	public Tips(JLabel tips, String content, int time, int mode) {
		this.tips = tips;
		this.content = content;
		this.time = time;
		this.mode = mode;
	}
	
	public void run() {
		try {
			switch (mode) {
				case WARNING:
					tips.setForeground(GUIX.ORANGE);
					break;
				case ERROR:
					tips.setForeground(GUIX.RED);
					break;
			}
			tips.setText(content);
			sleep(time);
			tips.setText(" ");
			tips.setForeground(new Color(122, 138, 153));
		} catch (InterruptedException e) {
			YeyuUtils.gui().exception(e);
		}
	}
}
