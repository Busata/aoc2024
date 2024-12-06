package be.intizo.aoc.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

}
