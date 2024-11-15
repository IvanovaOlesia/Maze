package edu.school21.maze.controller;

import edu.school21.maze.generation.MazeGenerator;
import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Point;
import edu.school21.maze.view.MazeCanvas;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MazeCanvas mazeCanvas;
    private Scene scene;
    private Maze maze;
    private  List<Point> coordinatesOfMouseClick;
    private Spinner<Integer> rowsSpinner;
    private Spinner<Integer> colsSpinner;
    private Button generateButton;

    public Controller() {
        mazeCanvas = new MazeCanvas();
        coordinatesOfMouseClick = new ArrayList<>();
        rowsSpinner  = new Spinner<>(2, 50, 2);
        colsSpinner = new Spinner<>(2, 50, 2);
        generateButton = new Button("GENERATE MAZE");

    }
    /**
     * The method starts the application. Places the user interface and waits for input
     */
    public void startProgram(Stage stage) {
        mazeCanvas.createUI(rowsSpinner, colsSpinner, generateButton, stage);
        scene = mazeCanvas.getMazeScene();
        buttonPress();
        mouseClick();
    }
    /**
     *  Event handler for pressing the maze generation button
     */
    private void buttonPress(){
        generateButton.setOnAction(event -> {
            maze = new Maze(rowsSpinner.getValue(),colsSpinner.getValue() );
            MazeGenerator.generateMaze(mazeCanvas, maze);
        });
    }
    /**
     *  Mouse click event handler
     */
    private void mouseClick(){
        scene.setOnMouseClicked(this::handleMouseClick);
    }
    private void handleMouseClick(MouseEvent event) {
        int x = (int)event.getSceneX();
        int y = (int)event.getSceneY();
        if(checkForPermissibleRangeOfValues(x, y)) {
            coordinatesOfMouseClick.add(new Point(x, y));
            if (coordinatesOfMouseClick.size() == 2) {
                MazeGenerator.generateSolution(mazeCanvas, maze,coordinatesOfMouseClick);
                coordinatesOfMouseClick.clear();
            }
        }
    }
    /**
     *  Mouse click event handler
     */
    private boolean checkForPermissibleRangeOfValues(int x, int y) {
        return (x < MazeCanvas.CANVAS_WIDTH) && (y < MazeCanvas.CANVAS_HEIGHT) && (maze != null);
    }
}
