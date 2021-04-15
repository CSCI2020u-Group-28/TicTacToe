package csci2020u.group28;

import java.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the main client ui
 * 
 */
public class TicTacToeClient extends Application {

  Stage window;
  Scene mainMenuScene, gameScene, ruleScene, creditScene;
  Button backToMenuButton;
  static Button resetGameButton;
  TextArea textArea;

  /*
   * Sets up various buttons and the scene
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    window = primaryStage;
    window.setTitle("TicTacToe Main Menu");
    Button gameButton = new Button("Play Game");
    gameButton.setPrefWidth(200);
    Button ruleButton = new Button("How to Play");
    ruleButton.setPrefWidth(200);
    Button creditButton = new Button("Credit");
    creditButton.setPrefWidth(200);
    Button exitButton = new Button("Exit");
    exitButton.setPrefWidth(200);
    backToMenuButton = new Button("Back to Main Menu");
    backToMenuButton.setPrefWidth(200);

    gameButton.setOnAction(e -> {
      openGameScene();
    });

    ruleButton.setOnAction(e -> {
      openRuleScene();
    });

    creditButton.setOnAction(e -> {
      openCreditScene();
    });

    exitButton.setOnAction(e -> {
      System.exit(1);
    });

    backToMenuButton.setOnAction(e -> {
      window.setScene(mainMenuScene);
    });

    VBox vb = new VBox();
    vb.getChildren().addAll(gameButton, ruleButton, creditButton, exitButton);
    vb.setAlignment(Pos.CENTER);

    mainMenuScene = new Scene(vb, 600, 600);
    window.setScene(mainMenuScene);
    window.show();
  }

  /**
   * This function opens the tictactoe game scene by calling createGameBoard() to
   * create the board and display the board on the screen.
   */
  public void openGameScene() {
    VBox vb = new VBox();
    vb.getChildren().addAll(backToMenuButton, TicTacToeBoard.createGameBoard());
    gameScene = new Scene(vb, 600, 600);
    window.setTitle("TicTacToe Game");
    window.setScene(gameScene);
  }

  /**
   * This function opens the tictactoe rules scene by reading the rules.txt file
   * in the files folder and displaying the rules on the screen.
   */
  public void openRuleScene() {
    VBox vb = new VBox();
    textArea = new TextArea();
    textArea.setEditable(false);
    textArea.setPrefSize(600, 600);
    readFile("src/csci2020u/group28/files/rules.txt");
    vb.getChildren().addAll(backToMenuButton, textArea);
    ruleScene = new Scene(vb, 600, 600);
    window.setTitle("TicTacToe Rules");
    window.setScene(ruleScene);
  }

  /**
   * This function opens the tictactoe credit scene by reading the credit.txt file
   * in the files folder and displaying the credit on the screen.
   */
  public void openCreditScene() {
    VBox vb = new VBox();
    textArea = new TextArea();
    textArea.setEditable(false);
    textArea.setPrefSize(600, 600);
    readFile("src/csci2020u/group28/files/credit.txt");
    vb.getChildren().addAll(backToMenuButton, textArea);
    creditScene = new Scene(vb, 600, 600);
    window.setTitle("TicTacToe Credit");
    window.setScene(creditScene);
  }

  /**
   * This function will use isComplete() and checkState() function from
   * TicTacToeBoard class to check the tiles of the board to see the condition
   * of the game is met and will output the condition into a new stage.
   * 
   * @param condition the condition of the game (ie. win, lose, tie)
   */
  public static void postGameStage(String condition) {
    Stage postStage = new Stage();
    postStage.setTitle(condition);
    StackPane postWindow = new StackPane();

    resetGameButton = new Button("RESET GAME");
    resetGameButton.setOnAction(e -> {
      TicTacToeBoard.resetBoard();
      postStage.close();
    });

    postWindow.getChildren().add(resetGameButton);
    Scene victoryScene = new Scene(postWindow, 250, 100);
    postStage.setScene(victoryScene);
    postStage.show();
  }

  /**
   * This function will read the inputted file directory and output the content of
   * the file into a textArea to display the content on the screen.
   * 
   * @param file a file directory as String (ie. "src\csci2020u\group28\*.txt")
   */
  public void readFile(String file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
      String line;
      while ((line = reader.readLine()) != null) {
        textArea.appendText(line + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
