package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;
import be.intizo.aoc.common.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day4 extends AbstractSolver {
    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "04";
    }

    @Override
    public void solvePart1(List<String> lines) {
        var searchGrid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        int counter = 0;
        for(int x = 0; x < searchGrid.length; x++) {
            for (int y = 0; y < searchGrid[x].length; y++) {

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if(dx == 0 && dy == 0) {
                            continue;
                        }
                        if(findWord(searchGrid, x, y, dx, dy, "XMAS", 0)) {
                            counter+=1;
                        };
                    }
                }
            }
        }

        System.out.println(counter);
    }


    private boolean findWord(char[][] searchGrid, int x, int y, int directionX, int directionY, String word, int index) {
        if(x < 0 || x >= searchGrid.length || y < 0 || y >= searchGrid[x].length) {
            //Out of bounds
            return false;
        }

        if(!(searchGrid[x][y] == word.charAt(index))) {
            //Letter is not correct
            return false;
        }

        if (word.length() == index + 1) {
            //letter is correct & we're at the last word!
            return true;
        } else {
            //Letter is correct, we're off to find the next letter
            return findWord(searchGrid, x + directionX, y + directionY, directionX, directionY, word, index + 1);
        }
    }

    @Override
    public void solvePart2(List<String> lines) {
        var searchGrid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        Map<Pair, Integer> results = new HashMap<>();

        for(int x = 0; x < searchGrid.length; x++) {
            for (int y = 0; y < searchGrid[x].length; y++) {

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if(dx == 0 || dy == 0) {
                            continue;
                        }
                        //find all diagonal MAS words
                        if(findWord(searchGrid, x, y, dx, dy, "MAS", 0)) {
                            var middleX = x + dx;
                            var middleY = y + dy;

                            results.computeIfPresent(new Pair(middleX, middleY), (key, value) -> {
                             return value + 1;
                            });
                            results.putIfAbsent(new Pair(middleX, middleY), 1);
                        };
                    }
                }
            }
        }

        long count = results.values().stream().filter(x -> x == 2).count();
        System.out.println(count);
    }
}
