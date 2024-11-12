package edu.school21.maze.view;

import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Solution;
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
    public static final int CANVAS_WIDTH = 500;
    public static final int CANVAS_HEIGHT = 500;
    private int cellWidth;
    private int cellHeight;
    private Scene scene;
    private GraphicsContext gc;
    private Maze maze;


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
        gc = this.getGraphicsContext2D();
    }

    public void drawMaze(Maze maze) {
        this.maze = maze;
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        cellWidth = CANVAS_WIDTH / maze.getNumberOfCols();
        cellHeight = CANVAS_HEIGHT / maze.getNumberOfRows();

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

    public void drawSolution(Solution solution){
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        drawMaze(maze);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        for (int i = 0; i < solution.getSolutionArray().size() - 1; i++) {
            int startX = solution.getSolutionArray().get(i).getX();
            int startY = solution.getSolutionArray().get(i).getY();
            int finishX = solution.getSolutionArray().get(i + 1).getX();
            int finishY = solution.getSolutionArray().get(i + 1).getY();
            gc.strokeLine(startX, startY, finishX, finishY);
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