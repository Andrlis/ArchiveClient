package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExternalWindow {

	public static void addWindow() {

		Stage stage = new Stage();
		stage.setTitle("Add card");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(15);
		grid.setVgap(15);
		grid.setPadding(new Insets(10, 20, 20, 20));

		final Label nameLabel = new Label("Name:");
		grid.add(nameLabel, 0, 0);
		final TextField nameField = new TextField();
		nameField.setPrefWidth(280);
		grid.add(nameField, 1, 0);

		final Label mNameLabel = new Label("Middle name:");
		grid.add(mNameLabel, 0, 1);
		final TextField mNameField = new TextField();
		grid.add(mNameField, 1, 1);

		final Label lNameLabel = new Label("Last Name:");
		grid.add(lNameLabel, 0, 2);
		final TextField lNameField = new TextField();
		grid.add(lNameField, 1, 2);

		final Label mailLabel = new Label("E-mail:");
		grid.add(mailLabel, 0, 3);
		final TextField mailField = new TextField();
		grid.add(mailField, 1, 3);

		final Label phoneLabel = new Label("Phone:");
		grid.add(phoneLabel, 0, 4);
		final TextField phoneField = new TextField();
		grid.add(phoneField, 1, 4);

		final Label jobLabel = new Label("Job:");
		grid.add(jobLabel, 0, 5);
		final TextField jobField = new TextField();
		grid.add(jobField, 1, 5);

		final Label descLabel = new Label("Description:");
		grid.add(descLabel, 0, 6);
		final TextField descField = new TextField();
		descField.setPrefHeight(80);
		grid.add(descField, 1, 6);

		Button okBtn = new Button("Add");
		okBtn.setPrefWidth(50);
		GridPane.setColumnSpan(okBtn, 2);
		GridPane.setHalignment(okBtn, HPos.CENTER);
		grid.add(okBtn, 0, 7);

		Scene scene = new Scene(grid, 400, 345);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(false);
		stage.show();
	}
	
	public static void searchWindow() {

		Stage stage = new Stage();
		stage.setTitle("Search card");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(15);
		grid.setVgap(15);
		grid.setPadding(new Insets(10, 20, 10, 20));

		final Label nameLabel = new Label("Name:");
		grid.add(nameLabel, 0, 0);
		final TextField nameField = new TextField();
		nameField.setPrefWidth(200);
		grid.add(nameField, 1, 0);

		final Label mNameLabel = new Label("Middle name:");
		grid.add(mNameLabel, 0, 1);
		final TextField mNameField = new TextField();
		grid.add(mNameField, 1, 1);

		final Label lNameLabel = new Label("Last Name:");
		grid.add(lNameLabel, 0, 2);
		final TextField lNameField = new TextField();
		grid.add(lNameField, 1, 2);

		Button okBtn = new Button("Search");
		GridPane.setColumnSpan(okBtn, 2);
		GridPane.setHalignment(okBtn, HPos.CENTER);
		grid.add(okBtn, 0, 3);

		Scene scene = new Scene(grid, 300, 160);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(false);
		stage.show();
	}
}
