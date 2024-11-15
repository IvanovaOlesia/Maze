/** MazeSimulator project
 *
 * Copyright (C) Maze Project Developers. All Rights Reserved
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
