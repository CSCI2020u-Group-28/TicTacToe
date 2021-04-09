package csci2020u.group28;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the main client ui
 * 
 * TODO:
 * implement tictactoe game
 * implement rules  (rule of game)
 * implement credit (our names/student ids)
 * implement server with thread (pain)
 */
public class client extends Application{

    Stage window;
    Scene mainMenuScene, gameScene, ruleScene, creditScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("TicTacToe Main Menu");

        Button gameButton = new Button("Play Game");
        Button ruleButton = new Button("How to Play");
        Button creditButton = new Button("Credit");
        Button exitButton = new Button("Exit");
        Button backToMenuButton = new Button("Back to Main Menu");

        gameButton.setOnAction(e -> {
            VBox tempPlacement = new VBox();
            tempPlacement.getChildren().addAll(backToMenuButton);
            gameScene = new Scene(tempPlacement, 300, 300);
            window.setTitle("TicTacToe Game");
            window.setScene(gameScene);
        });

        ruleButton.setOnAction(e -> {
            VBox tempPlacement = new VBox();
            tempPlacement.getChildren().addAll(backToMenuButton);
            ruleScene = new Scene(tempPlacement, 300, 300);
            window.setTitle("TicTacToe Rules");
            window.setScene(ruleScene);
        });

        creditButton.setOnAction(e -> {
            VBox tempPlacement = new VBox();
            tempPlacement.getChildren().addAll(backToMenuButton);
            creditScene = new Scene(tempPlacement, 300, 300);
            window.setTitle("TicTacToe Credits");
            window.setScene(creditScene);
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

        mainMenuScene = new Scene(vb, 350, 350);
        window.setScene(mainMenuScene);
        window.show();
      }

    public static void main(String[] args) {
        launch(args);
    }
}