package application;

import data_classes.Request;
import data_classes.UserCard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import logic.AppLogic;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	
	private static AppLogic logic;
	
	private static TextField nameField;
	private static TextField midNameField;
	private static TextField lastNameField;
	private static TextField mailField;
	private static TextField phoneField;
	private static TextField jobField;
	private static TextField descField;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Archive");
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);

			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root, 455, 470);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			/*
			 * Menu interface.
			 */
			MenuBar menuBar = new MenuBar();
			menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

			Menu menuHelp = new Menu("Помощь");
			Menu menuUser = new Menu("Пользователь");
			MenuItem loginUser = new MenuItem("Войти");
			MenuItem newUser = new MenuItem("Новый пользователь");
			menuUser.getItems().addAll(loginUser, newUser);

			menuBar.getMenus().addAll(menuUser, menuHelp);

			/*
			 * Top buttons.
			 */
			HBox menuBtnBox = new HBox();
			menuBtnBox.setPadding(new Insets(5, 5, 5, 5));

			Button addBtn = new Button();
			addBtn.setGraphic(new ImageView("file:/../plus.png"));
			HBox.setMargin(addBtn, new Insets(5, 5, 5, 5));
			Button searchBtn = new Button();
			searchBtn.setGraphic(new ImageView("file:/../search.png"));
			HBox.setMargin(searchBtn, new Insets(5, 5, 5, 5));
			Button deleteBtn = new Button();
			deleteBtn.setGraphic(new ImageView("file:/../trash.png"));
			HBox.setMargin(deleteBtn, new Insets(5, 5, 5, 5));
			menuBtnBox.getChildren().addAll(addBtn, searchBtn, deleteBtn);

			/*
			 * User card information pane.
			 */
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(50);
			grid.setVgap(10);
			grid.setPadding(new Insets(10, 20, 20, 20));
			grid.add(new Label("Name:"), 0, 0);
			grid.add(new Label("Middle name:"), 0, 1);
			grid.add(new Label("Last name:"), 0, 2);
			grid.add(new Label("E-mail:"), 0, 3);
			grid.add(new Label("Phone:"), 0, 4);
			grid.add(new Label("Job:"), 0, 5);
			grid.add(new Label("Description:"), 0, 6);

			nameField = new TextField();
			nameField.setEditable(false);
			nameField.setPrefWidth(300);
			grid.add(nameField, 1, 0);
			midNameField = new TextField();
			midNameField.setEditable(false);
			grid.add(midNameField, 1, 1);
			lastNameField = new TextField();
			lastNameField.setEditable(false);
			grid.add(lastNameField, 1, 2);
			mailField = new TextField();
			mailField.setEditable(false);
			grid.add(mailField, 1, 3);
			phoneField = new TextField();
			phoneField.setEditable(false);
			grid.add(phoneField, 1, 4);
			jobField = new TextField();
			jobField.setEditable(false);
			grid.add(jobField, 1, 5);
			descField = new TextField();
			descField.setEditable(false);
			descField.setPrefHeight(100);
			grid.add(descField, 1, 6);
			setCard(logic);

			/*
			 * Bottom buttons.
			 */
			HBox downBtnBox = new HBox();
			downBtnBox.setPadding(new Insets(10, 10, 10, 10));

			Button nextCard = new Button();
			nextCard.setGraphic(new ImageView("file:/../next.png"));
			HBox.setMargin(nextCard, new Insets(0, 0, 0, 5));
			Button prevCard = new Button();
			prevCard.setGraphic(new ImageView("file:/../prev.png"));

			downBtnBox.getChildren().addAll(prevCard, nextCard);
			/*
			 * Window pane.
			 */
			VBox windowBox = new VBox();
			windowBox.getChildren().addAll(menuBar, menuBtnBox, new Separator(), grid, new Separator(), downBtnBox);
			root.getChildren().add(windowBox);

			/*
			 * logIn/logOut menu action
			 */
			loginUser.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					if (!logic.authorization) {
						UserWindow.loginUserWindow(logic);
						loginUser.setText("Выйти");
					} else {
						logic.exitUser();
						loginUser.setText("Войти");
					}
				}
			});

			/*
			 * Add card action.
			 */
			addBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					ExternalWindow.addWindow();
				}
			});
			
			/*
			 * Next card action.
			 */
			nextCard.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					logic.currentCard++;
					setCard(logic);
				}
			});
			
			/*
			 * Previous card action.
			 */
			prevCard.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					logic.currentCard--;
					setCard(logic);
				}
			});
			
			/*
			 * Search card action.
			 */
			searchBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					ExternalWindow.searchWindow();
				}
			});

			/*
			 * "Add new user" menu button action
			 */
			newUser.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					UserWindow.newUserWindow(logic);
				}
			});

			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setCard(AppLogic logic){
		if(logic.currentCard<logic.getCards().size()&&logic.currentCard>=0){
		UserCard card = logic.getCards().get(logic.currentCard);
	    
		nameField.setText(card.getUserName());
		midNameField.setText(card.getUserMiddleName());;
		lastNameField.setText(card.getUserLastName());;
	    mailField.setText(card.getUserEmail());;
		phoneField.setText(card.getUserPhone());
		jobField.setText(card.getUserJob());;
		descField.setText(card.getUserDescription());
		}
	}

	public static void main(String[] args) {
		logic = new AppLogic();
		logic.start();
		launch(args);
		// client.endWork();
	}
}


