package edu.school21.maze.file;

import edu.school21.maze.model.Maze;

import java.io.*;
import java.util.Objects;

public class MazeReader {


    public Maze readFile() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/maze.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
            String[] str = reader.readLine().trim().split(" ");
            int numberOfRows = Integer.parseInt(str[0]);
            int numberOfCols = Integer.parseInt(str[1]);
            Maze maze = new Maze(numberOfRows, numberOfCols);
            for (int i = 0; i < numberOfRows; i++) {
                String line = reader.readLine();
                if (!line.isEmpty()) {
                    String[] vertical = line.trim().split(" ");
                    for (var value : vertical) {
                        maze.putVerticalWall(Integer.parseInt(value));
                    }
                } else {
                    i--;
                }
            }
            for (int i = 0; i < numberOfRows; i++) {
                String line = reader.readLine();
                if (!line.isEmpty()) {
                    String[] horizontal = line.trim().split(" ");
                    for (var value : horizontal) {
                        maze.putHorizontalWall(Integer.parseInt(value));
                    }
                } else {
                    i--;
                }
            }

            return maze;

        } catch (IOException e) {
            return null;
        }
    }
}
