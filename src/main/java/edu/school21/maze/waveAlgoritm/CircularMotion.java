package edu.school21.maze.waveAlgoritm;

import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Point;

import java.util.List;

public interface CircularMotion {
    boolean step(Point newPoint, Point oldPoint,List<List<Integer>>mazeSolution, Maze maze, boolean waveLaunched);
}
