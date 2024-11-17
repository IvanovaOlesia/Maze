package edu.school21.maze.controller;

import edu.school21.maze.generation.MazeGenerator;
import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Point;
import edu.school21.maze.view.MazeCanvas;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Controller {
    private final MazeCanvas mazeCanvas;
    private Scene scene;
    private Maze maze;
    private final List<Point> coordinatesOfMouseClick;
    private final Spinner<Integer> rowsSpinner;
    private final Spinner<Integer> colsSpinner;
    private final Button generateButton;

    public Controller() {
        mazeCanvas = new MazeCanvas();
        coordinatesOfMouseClick = new ArrayList<>();
        rowsSpinner  = new Spinner<>(2, 50, 2);
        colsSpinner = new Spinner<>(2, 50, 2);
        generateButton = new Button("GENERATE MAZE");
    }

    public void startProgram(Stage stage) {
        mazeCanvas.createUI(rowsSpinner, colsSpinner, generateButton, stage);
        scene = mazeCanvas.getMazeScene();
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        rowsSpinner.getEditor().setTextFormatter(new TextFormatter<>(filter));
        colsSpinner.getEditor().setTextFormatter(new TextFormatter<>(filter));
        buttonPress();
        mouseClick();
    }
    private void buttonPress(){
        generateButton.setOnAction(event -> {
            int rows = rowsSpinner.getValue();
            int cols = colsSpinner.getValue();
                maze = new Maze(rows, cols);
                MazeGenerator.generateMaze(mazeCanvas, maze);
        });
    }
    private void mouseClick(){
        scene.setOnMouseClicked(this::handleMouseClick);
    }
    private void handleMouseClick(MouseEvent event) {
        int x = (int)event.getSceneX();
        int y = (int)event.getSceneY();
        if(MazeGenerator.checkForPermissibleRangeOfValues(x, y, maze)) {
            coordinatesOfMouseClick.add(new Point(x, y));
            if (coordinatesOfMouseClick.size() == 2) {
                MazeGenerator.generateSolution(mazeCanvas, maze,coordinatesOfMouseClick);
                coordinatesOfMouseClick.clear();
            }
        }
    }

}
