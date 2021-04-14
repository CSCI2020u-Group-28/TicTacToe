package csci2020u.group28;

import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TicTacToeBoard {
    private static boolean playable = true;
    private static boolean turnX = true;
    private static Tile[][] board = new Tile[3][3];
    private static List<TileCell> tileCell = new ArrayList<>();
    private static Pane root = new Pane();
    private static int moveCounter = 0;

    /**
     * This class manages the tictactoe tiles so the player is able
     * to make their moves by clicking on the grid of their choice.
     */
    private static class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(20));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
            
            setOnMouseClicked(e -> {
                if (!playable) {
                    return;
                }

                if (e.getButton() == MouseButton.PRIMARY) {
                    if (!turnX) {
                        return;
                    }

                    drawX();
                    moveCounter++;
                    System.out.println(moveCounter);
                    turnX = false;
                    checkState();
                    
                } else if (e.getButton() == MouseButton.SECONDARY) {
                    if (turnX) {
                        return;
                    }
                    drawO();
                    moveCounter++;
                    System.out.println(moveCounter);
                    turnX = true;
                    checkState();
                }
            });
        }

        /**
         * This function return the symbol of the board tile as a String.
         *  
         * @return the symbol of the board as a String (ie. X or O)
         */
        public String getValue() {
            return text.getText();
        }

        // This function will place the X symbol on the board
        private void drawX() {
            text.setText("X");
        }

        // This function will place the O symbol on the board
        private void drawO() {
            text.setText("O");
        }
    }

    /**
     * This class will check the condition of the board tiles to see the state
     * of the game and will update the board if the game is complete (ie. player won).
     */
    private static class TileCell {
        private Tile[] tiles;

        public TileCell(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty()) {
                return false;
            }

            return tiles[0].getValue().equals(tiles[1].getValue()) && tiles[0].getValue().equals(tiles[2].getValue());
        }
    }
    
    /**
     * This function will create the game by drawing the 3x3 board and will
     * update the board by adding the player's input into the cell to check
     * the state of the game.
     * 
     * @return the game board
     */
    public static Parent createGameBoard() {
        root.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        // check horizontal tiles
        for (int y = 0; y < 3; y++) {
            tileCell.add(new TileCell(board[0][y], board[1][y], board[2][y]));
        }

        // check vertical tiles
        for (int x = 0; x < 3; x++) {
            tileCell.add(new TileCell(board[x][0], board[x][1], board[x][2]));
        }

        // check diagonals tiles
        tileCell.add(new TileCell(board[0][0], board[1][1], board[2][2]));
        tileCell.add(new TileCell(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    /**
     * This function will check the stage of the game by using the isComplete() function
     * to declare the player as the winner or tying the game if the board is full and 
     * no one has won.
     */
    public static void checkState() {
        for (TileCell cell : tileCell) {
            if (cell.isComplete()) {
                playable = false;
                TicTacToeClient.postGameStage("YOU WON!");
                break;
            } else if(!cell.isComplete() && moveCounter == 9) {
                playable = false;
                TicTacToeClient.postGameStage("GAME TIED!");
                break;
            }
        }
    }

    /**
     * This function will reset the game board after the game by reseting the
     * cells of the tiles and reseting the player's turn.
     */
    public static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].text.setText(null);
            }
        }
        moveCounter = 0;
        playable = true;
        turnX = true;
    }
}
