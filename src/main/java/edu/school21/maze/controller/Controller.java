package edu.school21.maze.controller;

import edu.school21.maze.generation.MazeGenerator;
import edu.school21.maze.model.Maze;
import edu.school21.maze.view.MazeCanvas;
import edu.school21.maze.waveAlgoritm.Point;
import edu.school21.maze.waveAlgoritm.WaveAlgorithm;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MazeCanvas mazeCanvas;
    private Scene scene;
    private WaveAlgorithm waveAlgorithm;
    private Maze maze;

    public void start(Stage stage) {
        Spinner<Integer> rowsSpinner = new Spinner<>(2, 50, 2);
        Spinner<Integer> colsSpinner = new Spinner<>(2, 50, 2);
        Button generateButton = new Button("GENERATE MAZE");
        mazeCanvas = new MazeCanvas();
        mazeCanvas.createUI(rowsSpinner, colsSpinner, generateButton, stage);
        scene = mazeCanvas.getMazeScene();
        generateButton.setOnAction(event -> generateMaze(rowsSpinner.getValue(), colsSpinner.getValue()));
        List<Point> coordinates = new ArrayList<>();
        scene.setOnMouseClicked(event -> {
            int x = (int)Math.floor(event.getSceneX()); // Получаем координату X
            int y = (int)Math.floor(event.getSceneY()); // Получаем координату Y
            coordinates.add(new Point(x, y));
            if(coordinates.size() == 2){
                generateSolution(coordinates);
                coordinates.clear();
            }
        });
    }

    private void generateSolution(List<Point> coordinates) {
        getCell(coordinates,0);
        getCell(coordinates,1);
        WaveAlgorithm waveAlgorithm = new WaveAlgorithm(maze);
        waveAlgorithm.findPath(coordinates.get(0).getX(), coordinates.get(0).getY(), coordinates.get(1).getX(), coordinates.get(1).getY());
    }

    private void getCell(List<Point> coordinates, int index) {
        int startCellX = coordinates.get(index).getX() / mazeCanvas.getCellHeight();
        int startCellY  = coordinates.get(index).getY() / mazeCanvas.getCellWidth();
        coordinates.get(index).setX(startCellX);
        coordinates.get(index).setY(startCellY);
    }

    private void generateMaze(Integer rows, Integer cols) {
        maze = new Maze(rows, cols);
        MazeGenerator mazeGenerator = new MazeGenerator(maze);
        mazeGenerator.mazeGeneration();
        mazeCanvas.drawMaze(maze);
    }
}
