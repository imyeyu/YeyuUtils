package net.imyeyu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Heartbeat extends Thread {
	
	private String content, callBack = "NULL";
	private Socket socket;
	private int interval;

	public Heartbeat(Socket socket, int interval) {
		this.socket = socket;
		this.interval = interval;
	}
	
	public void say(String content) {
		this.content = content;
	}
	
	public String getCallBack() {
		return callBack;
	}
	
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String tmp;
			while (true) {
				os.write((content + "\r\n").getBytes("UTF-8"));
				os.flush();
				tmp = br.readLine();
				if (callBack != null) {
					callBack = tmp;
				}
				sleep(interval);
			}
		} catch (SocketException e) {
			// disconnect
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}