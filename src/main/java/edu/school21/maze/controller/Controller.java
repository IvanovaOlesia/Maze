package edu.school21.maze.controller;

import edu.school21.maze.generation.MazeGenerator;
import edu.school21.maze.model.Maze;
import edu.school21.maze.view.MazeCanvas;
import edu.school21.maze.waveAlgoritm.WaveAlgorithm;
import javafx.scene.control.Spinner;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    private MazeCanvas mazeCanvas;

    public void start(Stage stage) {
        Spinner<Integer> rowsSpinner = new Spinner<>(2, 50, 2);
        Spinner<Integer> colsSpinner = new Spinner<>(2, 50, 2);
        Button generateButton = new Button("GENERATE MAZE");
        mazeCanvas = new MazeCanvas();
        mazeCanvas.createUI(rowsSpinner, colsSpinner, generateButton, stage);
        generateButton.setOnAction(event -> generateMaze(rowsSpinner.getValue(), colsSpinner.getValue()));
    }
    private void generateMaze(Integer rows, Integer cols) {
        Maze maze = new Maze(rows, cols);
        MazeGenerator mazeGenerator = new MazeGenerator(maze);
        mazeGenerator.mazeGeneration();
        WaveAlgorithm waveAlgorithm = new WaveAlgorithm(maze);
        mazeCanvas.drawMaze(maze);
    }
}
