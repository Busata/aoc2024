package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class Day1 extends AbstractSolver {

    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "01";
    }

    @Override
    public void solvePart1(List<String> lines) {
        var columns = lines.stream().map(line -> Arrays.stream(line.split(" +")).toList()).toList();

        var transposed = transposeList(columns);

        var firstSorted = transposed.get(0).stream().map(Integer::parseInt).sorted().toList();
        var secondSorted = transposed.get(1).stream().map(Integer::parseInt).sorted().toList();

        var result = IntStream.range(0, firstSorted.size()).map(idx -> {
            return Math.abs(firstSorted.get(idx) - secondSorted.get(idx));
        }).sum();

        System.out.println(result);
    }

    @Override
    public void solvePart2(List<String> lines) {
        var columns = lines.stream().map(line -> Arrays.stream(line.split(" +")).toList()).toList();

        var transposed = transposeList(columns);

        var searchList = transposed.get(1).stream().map(Integer::parseInt).toList();

        var result = transposed.get(0).stream().map(Integer::parseInt).mapToInt(leftListNumber -> {
            return leftListNumber * Collections.frequency(searchList, leftListNumber);
        }).sum();

        System.out.println(result);

    }

    private <T> List<List<T>> transposeList(List<List<T>> lists) {
        var numRows = lists.size();
        var numCols = lists.get(0).size();

        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < numCols; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result.get(j).add(lists.get(i).get(j));
            }
        }

        return result;
    }

}
