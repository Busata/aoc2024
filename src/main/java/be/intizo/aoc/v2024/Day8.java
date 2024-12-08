package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;
import be.intizo.aoc.common.Vector;
import be.intizo.aoc.common.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 extends AbstractSolver {
    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "08";
    }

    @Override
    public void solvePart1(List<String> lines) {
        var grid = makeCharGrid(lines);

        final var antennas = findAntennas(grid);

        var antinodes = antennas.values().stream().flatMap((positions) -> {
            return positions.stream().flatMap(p1 -> {
                return positions.stream().filter(p2 -> !p2.equals(p1)).map(p2 -> {
                    var betweenPoints = new Vector2D(p2.x() - p1.x(), p2.y() - p1.y());
                    return p2.add(betweenPoints);
                });
            }).filter(possiblePosition -> !isOutOfBounds(grid, possiblePosition));
        }).collect(Collectors.toSet());

        System.out.println(antinodes);
        System.out.println(antinodes.size());


    }

    @Override
    public void solvePart2(List<String> lines) {
        var grid = makeCharGrid(lines);
        final var antennas = findAntennas(grid);

        var antinodes = antennas.values().stream().flatMap((positions) -> {
            return positions.stream().flatMap(p1 -> {
                return positions.stream().filter(p2 -> !p2.equals(p1)).flatMap(p2 -> {
                    List<Vector2D> points = new ArrayList<>();

                    int dx = p2.x() - p1.x();
                    int dy = p2.y() - p1.y();

                    var currentPoint = p2.copy();

                    while(true) {
                        var point = new Vector2D(currentPoint.x() + dx, currentPoint.y() + dy);

                        if(isOutOfBounds(grid, point)) {
                            break;
                        }

                        points.add(point);
                        currentPoint = point;
                    }

                    //Add antennas itself(?)
                    points.add(p1);
                    points.add(p2);

                    return points.stream();
                });
            }).filter(possiblePosition -> !isOutOfBounds(grid, possiblePosition));
        }).collect(Collectors.toSet());
        System.out.println(antinodes.size());

    }



    private HashMap<Character, List<Vector2D>> findAntennas(char[][] grid) {
        final var antennas = new HashMap<Character, List<Vector2D>>();

        iterateGrid(grid, (position, token) -> {
            if (token == '.') {
                return;
            }

            antennas.putIfAbsent(token, new ArrayList<>());
            antennas.computeIfPresent(token, (key, positions) -> {
                positions.add(position);
                return positions;
            });
        });
        return antennas;
    }
}
