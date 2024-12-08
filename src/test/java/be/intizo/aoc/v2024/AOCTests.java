package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;
import org.junit.jupiter.api.Test;


class AOCTests {

    @Test
    public void solveDays() {
        //solve(new Day1());
        //solve(new Day2());
        //solve(new Day3());
        //solve(new Day4());
        //solve(new Day5());
        //solve(new Day6());
        //solve(new Day7());
        solve(new Day8());
    }

    private void solve(AbstractSolver day) {
        System.out.println("DAY: " + day.getDay() + "/" + day.getYear());
        System.out.println("--------------------");
        System.out.println("Part 1: test");

       day.testPart1(day.getYear(), day.getDay() + "_01");
        //System.out.println("Part 1: solution");
        day.solvePart1(day.getYear(), day.getDay());

       System.out.println("Part 2: test");
       day.testPart2(day.getYear(), day.getDay() + "_02");
//        System.out.println("Part 2: solution");
        day.solvePart2(day.getYear(), day.getDay());
//        System.out.println("-----------------------");
    }
}