package edu.school21.maze.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineOfSets {
    private final List<Integer> line;
    private final List<Integer> indexSetWithBottomWall;

    public LineOfSets(int size) {
        this.line = Stream.generate(() -> 0).limit(size).collect(Collectors.toCollection(ArrayList::new));
        this.indexSetWithBottomWall = new ArrayList<>();
    }

    public List<Integer> getLine() {
        return line;
    }

    public List<Integer> getIndexSetWithBottomWall() {
        return indexSetWithBottomWall;
    }
    public int getSize(){
        return line.size();
    }
    public Integer getCellByIndex(int index){
        return line.get(index);
    }
    public void setCellByIndex(int index, int value){
        line.set(index, value);
    }

    @Override
    public String toString() {
        return "LineOfSets{" +
                "line=" + line +
                ", indexSetWithBottomWall=" + indexSetWithBottomWall +
                '}';
    }
}
