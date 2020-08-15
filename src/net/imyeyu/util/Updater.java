package net.imyeyu.util;

import java.text.DecimalFormat;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.imyeyu.util.gui.AnchorPaneX;
import net.imyeyu.util.gui.BorderX;

/**
 * 基于 JavaFX 的软件更新器
 * 
 * @author Yeyu
 *
 */
public class Updater extends Stage {
	
	private String css, title, cancelText;
	
	private Label speed;
	private Button cancel;
	private ByteSpeed byteSpeed;
	private ProgressBar pb;
	private UpdaterDownloader downloader;
	
	private void init() {
		// 进度
		pb = new ProgressBar();
		// 速度
		speed = new Label();
		speed.setFont(Font.font("Consolas"));
		speed.setTextAlignment(TextAlignment.RIGHT);
		
		AnchorPane pbBox = new AnchorPane();
		pbBox.setPadding(new Insets(12, 0, 12, 0));
		pbBox.getChildren().addAll(pb, speed);
		AnchorPane.setTopAnchor(speed, 3d);
		AnchorPaneX.def(pb);
		
		// 取消
		cancel = new Button(cancelText);
		cancel.setFocusTraversable(false);
		
		// 根容器
		BorderPane root = new BorderPane();
		root.setCenter(pbBox);
		root.setBottom(cancel);
		root.setBorder(new BorderX("#B5B5B5", BorderX.SOLID, 1).top());
		root.setPadding(new Insets(4, 12, 8, 12));
		BorderPane.setAlignment(pb, Pos.CENTER_LEFT);
		BorderPane.setAlignment(speed, Pos.CENTER_RIGHT);
		BorderPane.setAlignment(cancel, Pos.CENTER);
		
		Scene scene = new Scene(root);
		if (css != null) scene.getStylesheets().add(this.getClass().getResource(css).toExternalForm());
		setScene(scene);
		getIcons().add(new Image(YeyuUtils.RES_PATH + "update.png"));
		setTitle(title);
		setWidth(320);
		setHeight(110);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		show();
		
		// 取消
		cancel.setOnAction((event) -> cancel());
		// 关闭
		setOnCloseRequest(event -> cancel());
	}

	/**
	 * 文件更新器
	 * 
	 * @param title      窗体标题
	 * @param cancelText 取消文本
	 */
	public Updater(String title, String cancelText) {
		this.title = title;
		this.cancelText = cancelText;
		init();
	}

	/**
	 * 文件更新器
	 * 
	 * @param css        页面整体 CSS 样式
	 * @param title      窗体标题
	 * @param cancelText 取消文本
	 */
	public Updater(String css, String title, String cancelText) {
		this.css = css;
		this.title = title;
		this.cancelText = cancelText;
		init();
	}
	
	/**
	 * 设置更新器下载对象
	 * 
	 * @param url      下载地址
	 * @param path     文件储存位置
	 * @param fileName 文件名
	 */
	public void setDownloader(String url, String path, String fileName) {
		// 更新下载器
		downloader = new UpdaterDownloader(url, path, fileName);
		downloader.progressProperty().addListener((obs, o, percent) -> {
			if (percent != null) {
				// 进度
				pb.setProgress(percent.doubleValue());
				// 速度指示
				if ((percent.doubleValue() * 296 < 290 - speed.getWidth() - 2)) {
					AnchorPane.setLeftAnchor(speed, percent.doubleValue() * 296);
				} else {
					AnchorPane.setLeftAnchor(speed, null);
					AnchorPane.setRightAnchor(speed, 2d);
				}
				// 取消控制
				if (.618 < percent.doubleValue()) {
					cancel.setDisable(true);
				}
			}
		});
		downloader.exceptionProperty().addListener((obs, o, e) -> e.printStackTrace());
		// 传输速度计算
		byteSpeed = new ByteSpeed();
		byteSpeed.reset();
		DecimalFormat df = new DecimalFormat("#,###.##");
		byteSpeed.valueProperty().addListener((obs, o, by7e) -> {
			speed.setText(by7e != 0 ? YeyuUtils.tools().netSpeedFormat(by7e, df) + "/s" : "");
		});
	}
	
	/**
	 * 开始
	 * 
	 */
	public void start() {
		byteSpeed.start();
		downloader.start();
	}
	
	/**
	 * 取消
	 * 
	 */
	private void cancel() {
		if (downloader != null && downloader.isRunning()) {
			downloader.shutdown();
			byteSpeed.shutdown();
		}
		close();
	}

	/**
	 * 下载完成事件
	 * 
	 * @param event 事件对象
	 */
	public void setOnSucceeded(ChangeListener<Boolean> successListener) {
		if (downloader != null) downloader.valueProperty().addListener(successListener);
	}
	
	/**
	 * 更新异常
	 * 
	 * @return
	 */
	public ReadOnlyObjectProperty<Throwable> exceptionProperty() {
		return downloader.exceptionProperty();
	}

	public ProgressBar getPb() {
		return pb;
	}
}