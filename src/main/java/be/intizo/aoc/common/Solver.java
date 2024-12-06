package be.intizo.aoc.common;

import java.util.List;

public interface Solver {

    String getYear();
    String getDay();

    void solvePart1(List<String> lines);
    void solvePart2(List<String> lines);
}
