package csci2020u.group28;

import java.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the main client ui
 * 
 * TODO:
 * implement tictactoe game (BOARD DONE / GAME NOT FINISH YET)
 * implement server with thread (pain)
 * (IM THINKING OF IMPLEMENTING ANOTHER PLAYER SOCKET CAUSE MAKING A AI SUCKS)
 */

public class client extends Application{

    Stage window;
    Scene mainMenuScene, gameScene, ruleScene, creditScene;
    Button backToMenuButton;
    TextArea textArea;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("TicTacToe Main Menu");

        Button gameButton = new Button("Play Game");
        Button ruleButton = new Button("How to Play");
        Button creditButton = new Button("Credit");
        Button exitButton = new Button("Exit");
        backToMenuButton = new Button("Back to Main Menu");

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
       * This function opens the tictactoe game scene by calling createGameBoard()
       * to create the board and display the board on the screen.
       */
      public void openGameScene() {
        VBox vb = new VBox();
        vb.getChildren().addAll(backToMenuButton, createGameBoard());
        gameScene = new Scene(vb, 600, 600);
        window.setTitle("TicTacToe Game");
        window.setScene(gameScene);
      }

      /**
       * This function opens the tictactoe rules scene by reading the rules.txt 
       * file in the files folder and displaying the rules on the screen.
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
       * This function opens the tictactoe credit scene by reading the credit.txt 
       * file in the files folder and displaying the credit on the screen.
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
       * This create the layouy of the tictactoe board
       * 
       * @return gameBoard - the tictactoe board layout.
       */
      public Parent createGameBoard() {
        Pane gameBoard = new Pane();
        gameBoard.setPrefSize(600, 600);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Board board = new Board();
                board.setTranslateX(j * 200);
                board.setTranslateY(i * 200);

                gameBoard.getChildren().add(board);
            }
        }

        return gameBoard;
      }

      /**
       * This function will read the inputted file directory and output the content 
       * of the file into a textArea to display the content on the screen.
       * @param file
       */
      public void readFile(String file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
            String line;
            while((line = reader.readLine()) != null) {
                textArea.appendText(line + "\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
      }
    public static void main(String[] args) {
        launch(args);
    }
}