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
    public static final double CANVAS_WIDTH = 500;
    public static final double CANVAS_HEIGHT = 500;
    public final double WINDOW_WIDTH = 700;
    public final double WINDOW_HEIGHT = 500;
    public final double LINE_WIDTH = 2;
    public final double BORDER_WIDTH = 4;
    private double cellWidth;
    private double cellHeight;
    private Scene scene;
    private final GraphicsContext gc;
    private Maze maze;


    public Scene getMazeScene() {
        return scene;
    }

    public double getCellWidth() {
        return cellWidth;
    }

    public double getCellHeight() {
        return cellHeight;
    }

    public MazeCanvas() {
        super(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = this.getGraphicsContext2D();
        settingBordersAndColors();

    }

    private void settingBordersAndColors() {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0 , CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(BORDER_WIDTH);
        gc.strokeRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void drawMaze(Maze maze) {
        this.maze = maze;
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        cellWidth = CANVAS_WIDTH / maze.getNumberOfCols();
        cellHeight = CANVAS_HEIGHT / maze.getNumberOfRows();
        settingBordersAndColors();
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            for (int j = 0; j < maze.getNumberOfCols(); j++) {
                gc.setFill(Color.BLACK);
                if (maze.getVerticalWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.fillRect((j + 1.00) * cellWidth - LINE_WIDTH, i * cellHeight, LINE_WIDTH, cellHeight);
                }
                if (maze.getHorizontalWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.fillRect(j * cellWidth, (i + 1.00) * cellHeight - LINE_WIDTH, cellWidth, LINE_WIDTH);
                }
            }
        }
    }

    public void drawSolution(Solution solution){
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        drawMaze(maze);
        gc.setStroke(Color.RED);
        gc.setLineWidth(LINE_WIDTH);
        for (int i = 0; i < solution.getSolutionArray().size() - 1; i++) {
            int startX = solution.getSolutionArray().get(i).getX();
            int startY = solution.getSolutionArray().get(i).getY();
            int finishX = solution.getSolutionArray().get(i + 1).getX();
            int finishY = solution.getSolutionArray().get(i + 1).getY();
            gc.strokeLine(startX, startY, finishX, finishY);
        }
    }

    public void createUI(Spinner<Integer> rowsSpinner, Spinner<Integer> colsSpinner, Button generateButton, Stage stage){
        settingSpinnerSizes(rowsSpinner,colsSpinner);
        GridPane gridPane = placingElementsInGrid(rowsSpinner,colsSpinner);
        Group root = placementOfTheCanvasWithRenderingAndControls(gridPane,generateButton);
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("MAZE");
        stage.setResizable(false);
        stage.show();
    }

    private Group placementOfTheCanvasWithRenderingAndControls(GridPane gridPane, Button generateButton ) {
        VBox vBox = new VBox(gridPane, generateButton);
        vBox.setSpacing(20);
        HBox hBox = new HBox(this, vBox);
        hBox.setSpacing(20);
        return new Group(hBox);
    }


    private void settingSpinnerSizes(Spinner<Integer> rowsSpinner, Spinner<Integer> colsSpinner) {
        rowsSpinner.setPrefWidth(75);
        rowsSpinner.setEditable(true);
        colsSpinner.setPrefWidth(75);
        colsSpinner.setEditable(true);
    }
    private GridPane placingElementsInGrid(Spinner<Integer> rowsSpinner, Spinner<Integer> colsSpinner) {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Rows:  "), 0, 0);
        gridPane.add(rowsSpinner, 1, 0);
        gridPane.add(new Label("Cols: "), 0, 1);
        gridPane.add(colsSpinner, 1, 1);
        return gridPane;
    }
}