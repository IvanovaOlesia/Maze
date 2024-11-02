package edu.school21.maze.controller;

import edu.school21.maze.generation.MazeGenerator;
import edu.school21.maze.model.Maze;
import edu.school21.maze.view.MazeCanvas;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller {
    private static final int CANVAS_WIDTH = 500; // Фиксированная ширина канваса
    private static final int CANVAS_HEIGHT = 500; // Фиксированная высота канваса

    private MazeCanvas mazeCanvas;

    public void start(Stage stage) {
        mazeCanvas = new MazeCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        TextField rowsInput = new TextField();
        rowsInput.setPromptText("Введите количество строк");

        TextField colsInput = new TextField();
        colsInput.setPromptText("Введите количество столбцов");

        Button generateButton = new Button("Сгенерировать лабиринт");

        generateButton.setOnAction(event -> generateMaze(rowsInput.getText(), colsInput.getText()));

        VBox vbox = new VBox(rowsInput, colsInput, generateButton, mazeCanvas);
        Group root = new Group(vbox);

        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + 100); // Увеличиваем высоту для ввода
        stage.setScene(scene);
        stage.setTitle("Генератор лабиринтов");
        stage.show();
    }

    private void generateMaze(String rowsText, String colsText) {
        try {
            int rows = Integer.parseInt(rowsText);
            int cols = Integer.parseInt(colsText);

            Maze maze = new Maze(rows, cols);
            MazeGenerator mazeGenerator = new MazeGenerator(maze);
            mazeGenerator.mazeGeneration();

            mazeCanvas.drawMaze(maze);
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, введите корректные числа.");
        }
    }
}
