package net.imyeyu.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * 更新器下载线程
 * 
 * @author Yeyu
 *
 */
class UpdaterDownloader extends Service<Boolean> {
	
	private String url, path, fileName;
	private boolean isSucceed = false, isShutdown = false;
	
	public UpdaterDownloader(String url, String path, String fileName) {
		this.url = url;
		this.path = path;
		this.fileName = fileName;
	}
	
	protected Task<Boolean> createTask() {
		return new Task<Boolean>() {
			protected Boolean call() throws Exception {
				downloadFile(url, path, fileName);
				return isSucceed;
			}

			/**
			 * 下载文件，脱离 network 接口以便计算网速
			 * 
			 * @param url        下载地址
			 * @param path       文件存放路径
			 * @param fileName   文件名
			 * @throws Exception 异常
			 */
			private void downloadFile(String url, String path, String fileName) throws Exception {
				File dir = new File(path);
				if (!dir.exists()) dir.mkdirs();
				// HTTP 下载文件
				HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
				conn.setConnectTimeout(8000);
				conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				// 文件大小
				long fileSize = (long) conn.getContentLength();
				// 输入流
				InputStream is = conn.getInputStream();
				// 缓冲区
				byte[] buffer = new byte[1024];
				int l = 0;
				// 字节输出流
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				// 加载字节流
				while ((l = is.read(buffer)) != -1) {
					ByteSpeed.BUFFER += l;
					updateProgress(ByteSpeed.BUFFER, fileSize);
					bos.write(buffer, 0, l);
					if (isShutdown) break;
				}
				// 没有取消，输出文件
				if (!isShutdown) {
					byte[] getData = bos.toByteArray();
					File file = new File(dir + File.separator + fileName);
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(getData);
					fos.close();
					isSucceed = true;
				}
				bos.reset();
				bos.close();
				is.close();
			}
		};
	}
	
	public void shutdown() {
		this.isShutdown = true;
	}
}