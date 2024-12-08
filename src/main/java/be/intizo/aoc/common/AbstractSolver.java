package be.intizo.aoc.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AbstractSolver implements Solver{

    static String INPUTS = "inputs";
    static String EXAMPLES = "examples";


    public void testPart1(String year, String day) {
        this.solvePart1(readInputFile(year, EXAMPLES, day));
    }

    public void testPart2(String year, String day) {
        this.solvePart2(readInputFile(year, EXAMPLES, day));
    }

    public void solvePart1(String year, String day) {
        this.solvePart1(readInputFile(year, INPUTS, day));
    }

    public void solvePart2(String year, String day) {
        this.solvePart2(readInputFile(year, INPUTS, day));
    }

    public List<String> readInputFile(String year, String solutionType, String day) {
        String dayFile = day + ".txt";

        try {
            return Files.readAllLines(Path.of("files", year, solutionType, dayFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected char[][] makeCharGrid(List<String> lines) {
        return lines.stream().map(String::toCharArray).toArray(char[][]::new);
    }

    protected void iterateGrid(char[][] grid, BiConsumer<Vector2D, Character> action) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                action.accept(new Vector2D(x, y), grid[y][x]);
            }
        }
    }

    protected boolean isOutOfBounds(char[][] grid, Vector2D pos) {
        return pos.y() < 0 || pos.y() >= grid.length ||
                pos.x() < 0 || pos.x() >= grid[pos.y()].length;
    }

    protected void printGrid(char[][] grid, Set<Vector2D> visited) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (visited.contains(new Vector2D(x, y))) {
                    System.out.print("X");
                } else {
                    System.out.print(grid[y][x]);
                }
            }
            System.out.println();
        }
    }

}
