package net.imyeyu.util.gui;

import javafx.scene.control.Button;

public class NavButton extends Button {
	
	private String colorEnter = "#F4F4F4";
	private String colorActive = "#D7D7D7";
	private String colorDefault = "#E4E4E4";
	private static final String BASE_CSS = "; -fx-background-insets: 0, 0 0 1 0; -fx-background-radius: 0";
	
	private void init(String text, double width, double height) {
		setText(text);
		setPrefWidth(width);
		setPrefHeight(height);
		setBorder(new BorderX("#B5B5B5", BorderX.SOLID, 1).bottom());
		setStyle("-fx-background-color: " + colorDefault + BASE_CSS);
		setOnMouseEntered(event -> setStyle("-fx-background-color: " + colorEnter + BASE_CSS));
		setOnMouseExited(event -> setStyle("-fx-background-color: " + colorDefault + BASE_CSS));
		setOnMousePressed(event -> setStyle("-fx-background-color: " + colorActive + BASE_CSS));
		setOnMouseReleased(event -> setStyle("-fx-background-color: " + colorDefault + BASE_CSS));
	}
	
	public NavButton(String text) {
		init(text, 155, 36);
	}
	
	public NavButton(String text, double width, double height) {
		init(text, width, height);
	}
	
	/**
	 * 设置颜色，顺序未默认，进入，点击
	 * 
	 * @param colors 颜色
	 */
	public void setColor(String... colors) {
		colorDefault = colors[0];
		colorEnter = colors[1];
		colorActive = colors[2];
		setStyle("-fx-background-color: " + colorDefault + BASE_CSS);
	}
}