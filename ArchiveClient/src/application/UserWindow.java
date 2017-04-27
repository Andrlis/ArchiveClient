package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.AppLogic;

public class UserWindow {
	
	/**
	 * Method creates new NewUser window.
	 * @param logic
	 */
	public static void newUserWindow(AppLogic logic) {

		Stage stage = new Stage();
		stage.setTitle("New user");

		GridPane grid = new GridPane();

		Scene scene = new Scene(grid, 500, 250);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(false);
		

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		final Label loginLabel = new Label("Логин:");
		grid.add(loginLabel, 0, 5);

		final TextField loginField = new TextField();
		loginField.setPrefWidth(250);
		grid.add(loginField, 2, 5);

		final Label passwordLabel1 = new Label("Пароль");
		grid.add(passwordLabel1, 0, 7);

		final PasswordField passwordField1 = new PasswordField();
		grid.add(passwordField1, 2, 7);

		final Label passwordLabel2 = new Label("Повторите пароль:");
		grid.add(passwordLabel2, 0, 9);

		final PasswordField passwordField2 = new PasswordField();
		grid.add(passwordField2, 2, 9);

		Button okBtn = new Button("OK");
		okBtn.setPrefWidth(80.0);
		GridPane.setColumnSpan(okBtn, 3);
		GridPane.setHalignment(okBtn, HPos.CENTER);
		grid.add(okBtn, 0, 13);

		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (passwordField1.getText().equals(passwordField2.getText())) {
					logic.addNewUser(loginField.getText(), passwordField1.getText());
					stage.close();
				} else {
					Label warning = new Label("Пароли не совпадают!");
					warning.setStyle("-fx-text-fill: red");
					grid.add(warning, 0, 0);
				}
			}
		});

		stage.show();
	}

	/**
	 * Method creates new LogIn/LogOut window.
	 * @param logic
	 */
	public static void loginUserWindow(AppLogic logic) {

		Stage stage = new Stage();
		stage.setTitle("LogIn");

		GridPane grid = new GridPane();

		Scene scene = new Scene(grid, 400, 200);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(false);

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		final Label loginLabel = new Label("Логин:");
		grid.add(loginLabel, 0, 5);

		final TextField loginField = new TextField();
		loginField.setPrefWidth(250);
		grid.add(loginField, 2, 5);

		final Label passwLabel = new Label("Пароль");
		grid.add(passwLabel, 0, 7);

		final PasswordField passwField = new PasswordField();
		grid.add(passwField, 2, 7);

		Button okBtn = new Button("Войти");
		okBtn.setPrefWidth(80.0);
		GridPane.setColumnSpan(okBtn, 3);
		GridPane.setHalignment(okBtn, HPos.CENTER);
		grid.add(okBtn, 0, 9);

		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				logic.setUser(loginField.getText(), passwField.getText());
				if (logic.authorization == true) {
					stage.close();
				} else {
					Label warningLabel = new Label("Incorrect user data!");
					warningLabel.setStyle("-fx-text-fill: red;");
					grid.add(warningLabel, 0, 0);
				}
			}
		});

		stage.show();
	}

}
