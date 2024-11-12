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

    public static void main(String[] args) {
        Application.launch();
    }


    @Override
    public void start(Stage stage){
        Controller mazeApp = new Controller();
        mazeApp.startProgram(stage);
    }
}
