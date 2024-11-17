package edu.school21.maze.generation;

import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Point;
import edu.school21.maze.model.Solution;
import edu.school21.maze.view.MazeCanvas;
import edu.school21.maze.waveAlgoritm.WaveAlgorithm;

import java.util.List;

public class MazeGenerator {
    private MazeGenerator() {
    }

    public static void generateMaze(MazeCanvas mazeCanvas, Maze maze) {
        MazeAlgorithm mazeGenerator = new MazeAlgorithm(maze);
        mazeGenerator.mazeGeneration();
        mazeCanvas.drawMaze(maze);
    }

    public static void generateSolution(MazeCanvas mazeCanvas, Maze maze, List<Point> coordinates) {
        Solution solution = new Solution(coordinates);
        solution.convertingPixelsToCells(mazeCanvas.getCellHeight(), mazeCanvas.getCellWidth());
        WaveAlgorithm waveAlgorithm = new WaveAlgorithm(maze, solution);
        waveAlgorithm.findPath();
        solution.convertingCellsToPixels(mazeCanvas.getCellHeight(), mazeCanvas.getCellWidth());
        mazeCanvas.drawSolution(solution);
    }
    public static boolean checkForPermissibleRangeOfValues(int x, int y, Maze maze) {
        return (x < MazeCanvas.CANVAS_WIDTH) && (y < MazeCanvas.CANVAS_HEIGHT) && (maze != null);
    }


}
