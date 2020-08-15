package net.imyeyu.util.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

/**
 * 带 Label 的输入框
 * 
 * @author 夜雨
 *
 */
public class LabelTextField extends HBox {
	
	private Label label;
	private TextField textField;
	private double width = 64, radius = 0;
	
	private void init() {
		BorderX border = new BorderX("#BFBFBF", BorderX.SOLID, 1);

		border.setRadius(radius, 0, 0, radius, false);
		label = new Label();
		label.setPrefHeight(27);
		label.setBorder(border.exRight());
		label.setTextFill(Paint.valueOf("#999"));
		label.setPadding(new Insets(0, 6, 0, 6));
		
		border.setRadius(0, radius, radius, 0, false);
		textField = new TextField();
		textField.setPrefWidth(width);
		textField.setBorder(border.def());
		textField.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFF"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		setAlignment(Pos.CENTER_LEFT);
		setBackground(new Background(new BackgroundFill(Paint.valueOf("#F4F4F4"), CornerRadii.EMPTY, Insets.EMPTY)));
		getChildren().addAll(label, textField);
	}
	
	/**
	 * 默认输入框
	 * 
	 * @param text 标签文本
	 */
	public LabelTextField(String text) {
		init();
		label.setText(text);
	}

	/**
	 * 指定文本输入框宽度
	 * 
	 * @param text  标签文本
	 * @param width 输入框宽度
	 */
	public LabelTextField(String text, double width) {
		this.width = width;
		init();
		label.setText(text);
	}

	/**
	 * 指定文本输入框尺寸
	 * 
	 * @param text   标签文本
	 * @param width  输入框宽度
	 * @param height 标签高度
	 */
	public LabelTextField(String text, double width, double height) {
		this.width = width;
		init();
		label.setText(text);
		label.setPrefHeight(height);
	}

	/**
	 * 指定组件样式
	 * 
	 * @param text   标签文本
	 * @param width  输入框宽度
	 * @param height 标签高度
	 * @param radius 组件圆角
	 */
	public LabelTextField(String text, double width, double height, double radius) {
		this.width = width;
		this.radius = radius;
		init();
		label.setText(text);
		label.setPrefHeight(height);
	}
	
	public Label getLabel() {
		return label;
	}
	
	public void setText(String text) {
		this.textField.setText(text);
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public TextField getTextField() {
		return textField;
	}
}
