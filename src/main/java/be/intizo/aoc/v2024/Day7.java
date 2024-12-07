package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 extends AbstractSolver {
    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "07";
    }

    @Override
    public void solvePart1(List<String> lines) {
        Operator[] operators = new Operator[]{Operator.COMBINE, Operator.MULTIPLY, Operator.PLUS};
        long result = 0L;

        for (String line : lines) {
            String[] split = line.split(":");
            long resultValue = Long.parseLong(split[0].trim());
            List<Long> values = Arrays.stream(split[1].trim().split(" ")).map(Long::parseLong).toList();

            List<List<Operator>> operatorCombinations = generateOperators(operators, values.size() - 1);

            for (List<Operator> combination : operatorCombinations) {
                Long solution = values.get(0);

                for (int j = 0; j < combination.size(); j++) {
                    Operator op = combination.get(j);
                    Long number = values.get(j + 1);

                    if (op == Operator.PLUS) {
                        solution += number;
                    } else if (op == Operator.MULTIPLY) {
                        solution *= number;
                    } else {
                        solution = Long.parseLong(solution.toString() + number.toString());
                    }

                    if (solution > resultValue) {
                        // No point in trying further when this combo already exceeds
                        break;
                    }
                }

                if (solution.equals(resultValue)) {
                    result += resultValue;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    private List<List<Operator>> generateOperators(Operator[] operators, int length) {
        List<List<Operator>> result = new ArrayList<>();

        result.add(new ArrayList<>());

        for (int i = 0; i < length; i++) {
            List<List<Operator>> newCombinations = new ArrayList<>();
            for (List<Operator> combination : result) {
                for (Operator operator : operators) {
                    List<Operator> newCombination = new ArrayList<>(combination);
                    newCombination.add(operator);
                    newCombinations.add(newCombination);
                }
            }
            result = newCombinations;
        }

        return result;
    }


    enum Operator {
        PLUS,
        MULTIPLY,
        COMBINE
    }

    @Override
    public void solvePart2(List<String> lines) {

    }
}
