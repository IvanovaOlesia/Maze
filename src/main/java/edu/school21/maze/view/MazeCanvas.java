package edu.school21.maze.view;

import edu.school21.maze.model.Maze;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MazeCanvas extends Canvas {
    private final int CANVAS_WIDTH = 500;
    private final int CANVAS_HEIGHT = 500;

    private int cellWidth;
    private int cellHeight;

    private Scene scene;


    public Scene getMazeScene() {
        return scene;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public MazeCanvas() {
        super(500, 500);
    }

    public void drawMaze(Maze maze) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        cellWidth = (int) Math.floor((double) CANVAS_WIDTH / maze.getNumberOfCols());
        cellHeight = (int) Math.floor((double) CANVAS_HEIGHT / maze.getNumberOfRows());

        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            for (int j = 0; j < maze.getNumberOfCols(); j++) {
                gc.setFill(Color.BLACK);
                if (maze.getVerticalWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.fillRect((j + 1) * cellWidth - 2, i * cellHeight, 2, cellHeight);
                }
                if (maze.getHorizontalWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.fillRect(j * cellWidth, (i + 1) * cellHeight - 2, cellWidth, 2);
                }
            }
        }
    }

    public void createUI(Spinner<Integer> rowsSpinner, Spinner<Integer> colsSpinner, Button generateButton, Stage stage){
        rowsSpinner.setPrefWidth(75);
        rowsSpinner.setEditable(true);
        colsSpinner.setPrefWidth(75);
        colsSpinner.setEditable(true);
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Rows:  "), 0, 0);
        gridPane.add(rowsSpinner, 1, 0);
        gridPane.add(new Label("Cols: "), 0, 1);
        gridPane.add(colsSpinner, 1, 1);
        VBox vBox = new VBox(gridPane, generateButton);
        vBox.setSpacing(20);
        HBox hBox = new HBox(this, vBox);
        hBox.setSpacing(20);
        Group root = new Group(hBox);

        scene = new Scene(root, 500 + 200, 500);
        stage.setScene(scene);
        stage.setTitle("MAZE");
        stage.show();
    }
}