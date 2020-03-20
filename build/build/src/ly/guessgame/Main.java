package ly.guessgame;

import ly.guessgame.model.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane root;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GameMechanism PlayGame = new GameMechanism();
			PlayGame.GeneratedNumbers();
			this.primaryStage = primaryStage;
			root = FXMLLoader.load(getClass().getResource("view/TheLayout.fxml"));
			Scene scene = new Scene(root);
			this.primaryStage.setScene(scene);
			this.primaryStage.getIcons().add(new Image("file:resources/images/brain.png"));
			this.primaryStage.setTitle("Guess Game V2.1");
			this.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
