package edu.school21.maze.view;

import edu.school21.maze.model.Maze;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeCanvas extends Canvas {
    private int canvasWidth;
    private int canvasHeight;

    public MazeCanvas(int width, int height) {
        super(width, height);
        this.canvasWidth = width;
        this.canvasHeight = height;
    }

    public void drawMaze(Maze maze) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        int cellWidth = (int) Math.floor((double) canvasWidth / maze.getNumberOfCols());
        int cellHeight = (int) Math.floor((double) canvasHeight / maze.getNumberOfRows());

        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            for (int j = 0; j < maze.getNumberOfCols(); j++) {
                gc.setFill(Color.BLACK);
                if (maze.getVerticalWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.fillRect((j + 1) * cellWidth - 2, i * cellHeight, 2, cellHeight);
                }
                if (maze.getHorizontalWall().get(i * maze.getNumberOfCols() + j) == 1) {
                    gc.fillRect(j * cellWidth, (i + 1) * cellHeight - 2, cellWidth, 2);
                }
            }
        }
    }
}
