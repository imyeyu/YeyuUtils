package net.imyeyu.util;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * 基于 JavaFX 线程的字节交流速度计算器
 * 
 * @author Yeyu
 *
 */
public class ByteSpeed extends Service<Double> {

	public static double BUFFER = 0;
	
	private double old;
	private boolean isShutdown = false;

	protected Task<Double> createTask() {
		return new Task<Double>() {
			protected Double call() throws Exception {
				while (!isShutdown) {
					updateValue(BUFFER - old);
					old = BUFFER;
					Thread.sleep(1000);
				}
				return 0d;
			}
		};
	}
	
	public void reset() {
		this.old = 0;
		ByteSpeed.BUFFER = 0;
	}
	
	public void shutdown() {
		this.isShutdown = true;
	}
}