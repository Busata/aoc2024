package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends AbstractSolver {
    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "05";
    }

    @Override
    public void solvePart1(List<String> lines) {
        var result = calculateResult(lines, CalculateMode.INCLUDE_SORTED);
        System.out.println(result);
    }

    @Override
    public void solvePart2(List<String> lines) {
        var result = calculateResult(lines, CalculateMode.INCLUDE_UNSORTED);
        System.out.println(result);

    }

    enum CalculateMode {
        INCLUDE_SORTED,
        INCLUDE_UNSORTED
    }

    private static int calculateResult(List<String> lines, CalculateMode mode) {
        var splitIndex = lines.indexOf("");

        var sorting = lines.subList(0, splitIndex);
        var updates = lines.subList(splitIndex + 1, lines.size());

        var sortingRules = sorting.stream().map(sort -> sort.split("\\|")).collect
                (Collectors.groupingBy(e -> e[0], Collectors.mapping(e -> e[1], Collectors.toList())));

        var result = updates.stream().map(update ->  {
            List<String> tokens =  Arrays.stream(update.split(",")).toList();
            return tokens;
        }).map(unsortedList -> {
            List<String> sortedList =  unsortedList.stream().sorted((String a, String b) -> {

                if (sortingRules.containsKey(a)) {
                    var numbersToBeBefore = sortingRules.get(a);
                    if(numbersToBeBefore.contains(b)) {
                        return -1;
                    }
                }
                return 0;
            }).toList();

            var include = (mode == CalculateMode.INCLUDE_UNSORTED && !sortedList.equals(unsortedList)) ||
                    (mode == CalculateMode.INCLUDE_SORTED && sortedList.equals(unsortedList));

            if(include) {
                return sortedList;
            } else {
                return new ArrayList<String>();
            }

        }).filter(list -> !list.isEmpty()).mapToInt((sortedList) -> {
            var middle = sortedList.size() / 2;
            String s = sortedList.get(middle);
            return Integer.parseInt(s);
        }).sum();
        return result;
    }
}
