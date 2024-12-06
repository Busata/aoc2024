package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;

import java.util.List;
import java.util.regex.Pattern;

public class Day3 extends AbstractSolver {

    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "03";
    }

    @Override
    public void solvePart1(List<String> lines) {
        var line = String.join("", lines);

        var pattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");
        var matcher = pattern.matcher(line);

        var sum = 0;
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }

        System.out.println(sum);
    }

    @Override
    public void solvePart2(List<String> lines) {
        var line = "do()" + String.join("", lines) + "don't()";

        var pattern = Pattern.compile("do\\(\\)(.*?)don't\\(\\)");
        var matcher = pattern.matcher(line);

        var sum = 0;
        while (matcher.find()) {
            String mulInstructions = matcher.group(1);

            var mulPattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");
            var mulMatcher = mulPattern.matcher(mulInstructions);


            while(mulMatcher.find()) {
                sum += Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
            }
        }

        System.out.println(sum);
    }

}
