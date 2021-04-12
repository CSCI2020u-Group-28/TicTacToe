package csci2020u.group28;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class is used to create the tictactoe board and
 * process the game by the condition of winning, losing or
 * tying. *** will implement later ***
 */
public class Board extends StackPane {
    private Text text = new Text();

    Board() {
        Rectangle tile = new Rectangle(200, 200);
        tile.setFill(null);
        tile.setStroke(Color.BLACK);
        
        text.setFont(Font.font(72));

        setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                drawX();
            }
            else if(e.getButton() == MouseButton.SECONDARY) {
                drawO();
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(tile, text);
    }

    private void drawX() {
        text.setText("X");
    }

    private void drawO() {
        text.setText("O");
    }
  }