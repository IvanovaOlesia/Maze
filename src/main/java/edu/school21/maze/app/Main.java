/** MazeAndCaveSimulator project generator_device.h using code-sample file
 *
 * Copyright (C) Maze Project Developers. All Rights Reserved
 *
 * If the code of this project has helped you in any way,
 * please thank us with a cup of beer.
 *
 *
 */

package edu.school21.maze.app;

import edu.school21.maze.generation.MazeGenerator;
import edu.school21.maze.model.Maze;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    private int width = 500;
    private int height = 500;


    public static void main(String[] args) {
//        Maze maze = new Maze(4,4);
//        MazeGenerator mazeGenerator = new MazeGenerator(maze);
//        mazeGenerator.mazeGeneration();


        Application.launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Maze maze = new Maze(4,4);

        MazeGenerator mazeGenerator = new MazeGenerator(maze);
        mazeGenerator.mazeGeneration();
        System.out.println(maze.getBottomWall());
        System.out.println(maze.getRightWall());

        int cellWidth = (int) Math.floor((double) width / (maze.getNumberOfCols() + 1));
        int cellHeight = (int) Math.floor((double) height / (maze.getNumberOfRows() + 1));




        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            for (int j = 0; j < maze.getNumberOfCols(); j++) {

                if (maze.getRightWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect((j + 1) * cellWidth - 1, i * cellWidth + 1, 2, cellHeight);
                }else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect((j + 1) * cellWidth - 1, i * cellWidth + 1, 2, cellHeight);

                }
                if (maze.getBottomWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * cellWidth + 1, (i + 1) * cellWidth - 1, cellWidth, 2);
                }else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(j * cellWidth + 1, (i + 1) * cellWidth - 1, cellWidth, 2);

                }
            }
        }
        Group root = new Group(canvas);
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();

    }
}
