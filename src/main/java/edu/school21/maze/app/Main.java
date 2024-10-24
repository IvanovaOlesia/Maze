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

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(4,4);
        MazeGenerator mazeGenerator = new MazeGenerator(maze);
        mazeGenerator.generateRandomNumber();
    }
}
