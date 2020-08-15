package net.imyeyu.util.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

public class LabelPasswordField extends HBox {
	
	private Label label;
	private PasswordField passwordField;
	private double width = 64, radius = 2;
	
	private void init() {
		BorderX border = new BorderX("#BFBFBF", BorderX.SOLID, 1);

		border.setRadius(radius, 0, 0, radius, false);
		label = new Label();
		label.setPrefHeight(27);
		label.setBorder(border.exRight());
		label.setTextFill(Paint.valueOf("#999"));
		label.setPadding(new Insets(0, 6, 0, 6));
		
		border.setRadius(0, radius, radius, 0, false);
		passwordField = new PasswordField();
		passwordField.setPrefWidth(width);
		passwordField.setBorder(border.def());
		passwordField.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFF"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		setAlignment(Pos.CENTER_LEFT);
		setBackground(new Background(new BackgroundFill(Paint.valueOf("#F4F4F4"), CornerRadii.EMPTY, Insets.EMPTY)));
		getChildren().addAll(label, passwordField);
	}
	
	public LabelPasswordField(String text) {
		init();
		label.setText(text);
	}

	public LabelPasswordField(String text, double width) {
		this.width = width;
		init();
		label.setText(text);
	}

	public LabelPasswordField(String text, double width, double height) {
		this.width = width;
		init();
		label.setText(text);
		label.setPrefHeight(height);
	}

	public LabelPasswordField(String text, double width, double height, double radius) {
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
		this.passwordField.setText(text);
	}
	
	public String getText() {
		return passwordField.getText();
	}
	
	public TextField getTextField() {
		return passwordField;
	}
}
