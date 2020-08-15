package net.imyeyu.util;

import java.io.IOException;

import javafx.application.Application;

public class FXEvents {

	public static final void doRestart(Application application) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
					Runtime.getRuntime().exec("java -jar " + System.getProperty("java.class.path"));
				} catch (IOException e) {
					e.printStackTrace();
				}
            }    
        });
		try {
			application.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}