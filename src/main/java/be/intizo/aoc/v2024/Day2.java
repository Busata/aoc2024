package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day2 extends AbstractSolver {

    @Override
    public void solvePart1(List<String> lines) {
        var safeReports = lines.stream().filter(report -> {

            var levels =  Arrays.stream(report.split(" +")).map(Integer::parseInt).toList();

            return isValidReport(levels);
        }).count();

        System.out.println(safeReports);
    }

    @Override
    public void solvePart2(List<String> lines) {
        var safeReports = lines.stream().filter(report -> {
            var levels =  Arrays.stream(report.split(" +")).map(Integer::parseInt).toList();

           return IntStream.range(0, levels.size()).mapToObj(excludeIndex -> {
                var filteredList = IntStream.range(0, levels.size())
                        .filter(i -> i != excludeIndex) // Exclude the index
                        .mapToObj(levels::get)
                        .toList();

                return isValidReport(filteredList);

            }).anyMatch(x -> x);



        }).count();

        System.out.println(safeReports);
    }


    private boolean isValidReport(List<Integer> levels) {
        var deltas = IntStream.range(0, levels.size() - 1).mapToObj(idx -> {
            return levels.get(idx) - levels.get(idx + 1);
        }).toList();

        return (deltas.stream().allMatch(x -> x < 0) || deltas.stream().allMatch(x -> x >= 0)) &&
                deltas.stream().map(Math::abs).allMatch(x -> x >= 1 && x <= 3);
    }

    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "02";
    }


}
