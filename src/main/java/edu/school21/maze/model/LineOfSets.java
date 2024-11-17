package edu.school21.maze.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineOfSets {
    private final List<Integer> line;
    private final List<Integer> indexSetWithHorizontalWall;

    public LineOfSets(int size) {
        this.line = Stream.generate(() -> 0).limit(size).collect(Collectors.toCollection(ArrayList::new));
        this.indexSetWithHorizontalWall = new ArrayList<>();
    }
    public List<Integer> getLine() {
        return line;
    }
    public List<Integer> getIndexSetWithHorizontalWall() {
        return indexSetWithHorizontalWall;
    }
}
