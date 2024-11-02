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

import edu.school21.maze.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private int width = 500;
    private int height = 500;


    public static void main(String[] args) {
        Application.launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Controller mazeApp = new Controller();
        mazeApp.start(stage);
//        Canvas canvas = new Canvas(500, 500);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        MazeReader mazeReader = new MazeReader();
////        Maze maze = mazeReader.readFile();
//        Maze maze = new Maze(7,7);
//
//        MazeGenerator mazeGenerator = new MazeGenerator(maze);
//        mazeGenerator.mazeGeneration();
//
//
//        int cellWidth = (int) Math.floor((double) width / maze.getNumberOfCols());
//        int cellHeight = (int) Math.floor((double) height / maze.getNumberOfRows());
//
//
//
//
//        for (int i = 0; i < maze.getNumberOfRows(); i++) {
//            for (int j = 0; j < maze.getNumberOfCols(); j++) {
//                gc.setFill(Color.BLACK);
//                if (maze.getVerticalWall().get(i * maze.getNumberOfCols() + j) == 1) {
//                    gc.fillRect((j + 1)  * cellWidth - 2, i * cellHeight, 2, cellHeight);
//                }
//                if (maze.getHorizontalWall().get(i * maze.getNumberOfCols() + j) == 1) {
//                    gc.fillRect(j * cellWidth, (i + 1)  * cellHeight - 2 , cellWidth , 2);
//                }
//            }
//        }
//        Group root = new Group(canvas);
//        Scene scene = new Scene(root, 500, 500);
//        stage.setScene(scene);
//        stage.show();

    }
}
