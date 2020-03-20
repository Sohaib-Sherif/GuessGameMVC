package ly.guessgame.view;

import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.stage.Stage;
import ly.guessgame.model.GameMechanism;

public class TheLayoutController {
	
	GameMechanism PlayGame = new GameMechanism();
	private int hintCounter = 0;
	private int[] newRandom = GameMechanism.getRandomNumbers();
	
	@FXML
	private MenuItem NewGame;
	@FXML
	private MenuItem Close;
	@FXML
	private MenuItem About;
	@FXML
	private MenuItem Generatenew;
	@FXML
	private MenuBar Batata;
	@FXML
	private TextField input;
	@FXML
	private Label holderofResult;
	@FXML
	private Label labelofGuesses;
	@FXML
	private Label valueofguess;
	@FXML
	private Button button;
	@FXML
	private Button replay;
	@FXML
	private Button hint;
	@FXML
	private Label hintleft;
	
	
	/**
	 *  declaration of rootLayout methods
	 **/
	@FXML
	public void GenerateNew(ActionEvent e) {
		for(int i = 0; i <= 9; i++) {
			newRandom[i] = new Random().nextInt(10)+1;
		}
		GameMechanism.setRandomNumbers(newRandom);
		Alert New = new Alert(AlertType.INFORMATION);
		New.setTitle("Numbers generator");
		New.setHeaderText("Generate new random numbers");
		New.setContentText("New Numbers have been generated succesfully!");
		New.initOwner(Batata.getScene().getWindow());
		DialogPane dialogPane = New.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("application.css").toExternalForm());
		New.showAndWait();
	}
	
	@FXML
	public void AboutGame(ActionEvent e) {
		String one,two;
		one = "Guess game is a game made by Sohaib Sherif as training for making much more modern ";
		two = "and stylized jave GUI using JavaFX.";
		Alert AboutWindow = new Alert(AlertType.INFORMATION);
		AboutWindow.setTitle("Guess Game V 2.1");
		AboutWindow.setContentText(one + two);
		AboutWindow.setHeaderText(null);
		AboutWindow.initOwner(Batata.getScene().getWindow());
		DialogPane dialogPane = AboutWindow.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("application.css").toExternalForm());
		AboutWindow.showAndWait();
	}
	
	@FXML
	public void ExitGame(ActionEvent e) {
		Stage close;
		close = (Stage)Batata.getScene().getWindow();
		String contexttext = "Are you sure you want to exit the Guessing game?";
		ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		Alert c = new Alert(AlertType.CONFIRMATION, contexttext, yesButton , cancelButton);
		c.setTitle("Close Guessing game?");
		c.setHeaderText(null);
		DialogPane dialogPane = c.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("application.css").toExternalForm());
		c.initOwner(Batata.getScene().getWindow());
		Optional<ButtonType> result = c.showAndWait();
		if (result.get() == yesButton) {
			close.close();
		}
		else 
			c.close();
	}
	
	@FXML
	public void NewGame(ActionEvent e) {
		Alert New = new Alert(AlertType.CONFIRMATION);
		New.setTitle("New Game");
		New.setHeaderText("New game");
		New.setContentText("Are you sure you want to make a new game?");
		New.initOwner(Batata.getScene().getWindow());
		DialogPane dialogPane = New.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("application.css").toExternalForm());
		Optional<ButtonType> result = New.showAndWait();
		if (result.get()== ButtonType.OK) {
			replayHandler(e);
		}
		else {
			New.close();
		}
	}
	/**
	 * Declaration of GameScene methods
	 */
	
	@FXML
	public void replayHandler(ActionEvent e) {
		GameMechanism.setNumGuesses(0);
		GameMechanism.setCounter(0);
		PlayGame.GeneratedNumbers();
		button.setDisable(false);
		input.setDisable(false);
		input.clear();// to clear any remains of text left
		input.setPromptText("Enter a number between 1 and 10");
		replay.setDisable(true);
		labelofGuesses.setText("No guesses made so far");
		labelofGuesses.requestFocus();// just to take the focus from input.
		holderofResult.setText("");
		valueofguess.setText("");
		hint.setDisable(false);
		hintleft.setText("(3 Left)");
		hintCounter = 0;
	}
	
	@FXML
	public void hintHandler(ActionEvent e) {
		input.setText(Integer.toString(GameMechanism.getRandomNumbers()[PlayGame.getNumGuesses()]));
		switch (hintCounter) {	
		case 0 :  hintleft.setText("(2 Left)"); break;
		case 1 :  hintleft.setText("(1 left)"); break;
		case 2 :  hintleft.setText("(No hints left)"); hint.setDisable(true); break;
		}
		hintCounter++;
	}
	
	@FXML
	public void buttonHandeler(ActionEvent e) {// I need to re-factor the if statements,I can improve them.
		String TextFieldValue = input.getText();
		GameMechanism.CheckUserNumbers(TextFieldValue);
		input.setText("");
		input.requestFocus();
		if (PlayGame.getNumGuesses() == 1 && PlayGame.getNumGuesses() > 0) {
			labelofGuesses.setText(String.valueOf(PlayGame.getNumGuesses()+" Guess made"));
		}
		else if (PlayGame.getNumGuesses() > 1) {
			labelofGuesses.setText(String.valueOf(PlayGame.getNumGuesses()+" Guesses made"));
		}
		else {
			Alert wronginput = new Alert(AlertType.WARNING);
			wronginput.setTitle("Wrong input");
			wronginput.setHeaderText(null);
			wronginput.setContentText("Please insert a number");
			wronginput.initOwner(Batata.getScene().getWindow());
			wronginput.showAndWait();
		}
		if (PlayGame.getNumGuesses() == 10) {
			button.setDisable(true);
			input.setDisable(true);
			labelofGuesses.setText(PlayGame.getNumGuesses()+" Guesses made");
			input.setEffect(new Blend(BlendMode.MULTIPLY));// not so noticeable.
			input.setPromptText("Thanks For Playing!");
			replay.setDisable(false);
			if (PlayGame.getCounter() == 0) {
				holderofResult.setText("You didn't get any guess right!");
				setColor();
			}
			else if (PlayGame.getCounter() == 1) {
				holderofResult.setText("You have got only "+Integer.toString(PlayGame.getCounter())+" guess right!");
				setColor();
			}
			else if (PlayGame.getCounter() > 1 && PlayGame.getCounter() < 10) {
				holderofResult.setText("You have got "+Integer.toString(PlayGame.getCounter())+" guesses right!");
				setColor();
			}
			else {
				holderofResult.setText("I can't believe it! you done it!! 10/10 right guesses");
				setColor();
			}
		}
		try {// gave error,weird since I didn't encounter this in swing code.
			if (Integer.parseInt(TextFieldValue) >= 0 && Integer.parseInt(TextFieldValue) <= 10) {
				valueofguess.setText(Integer.toString(GameMechanism.getRandomNumbers()[PlayGame.getNumGuesses()-1]));
			}
		} catch (NumberFormatException error) {
			
		}
	}
	
	public void setColor() {
		if (PlayGame.getCounter() <= 3 && PlayGame.getCounter() > 0) {
			holderofResult.setStyle("-fx-text-fill: Orange");
		}
		else if (PlayGame.getCounter() <=6 && PlayGame.getCounter() >3) {
			holderofResult.setStyle("-fx-text-fill: Cyan");
		}
		else if (PlayGame.getCounter() <=9 && PlayGame.getCounter() >6) {
			holderofResult.setStyle("-fx-text-fill: Blue");
		}
		else if (PlayGame.getCounter() == 10) {
			holderofResult.setStyle("-fx-text-fill: Green");
		}
		else {
			holderofResult.setStyle("-fx-text-fill: Red");
		}
	}

}
