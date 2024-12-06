package be.intizo.aoc.v2024;

import be.intizo.aoc.common.AbstractSolver;
import be.intizo.aoc.common.Vector2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Day6 extends AbstractSolver {
    @Override
    public String getYear() {
        return "2024";
    }

    @Override
    public String getDay() {
        return "06";
    }

    @Override
    public void solvePart1(List<String> lines) {
        var grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        var characterPosition = findCharacter(grid, '^');

        Vector2D direction = new Vector2D(0, -1);
        if (characterPosition == null) {
            return;
        }

        var currentPosition = characterPosition;

        Set<Vector2D> positionsVisited = new HashSet<>();
        positionsVisited.add(characterPosition);


        final Set<Vector2D> possibleObstructions = new HashSet<>();

        while (true) {

            var nextPosition = currentPosition.add(direction);

            if (isOutOfBounds(nextPosition, grid)) {
                break;
            }
            if (grid[nextPosition.y()][nextPosition.x()] == '#') {
                direction = direction.rotate(90);
                continue;
            }

            checkLoopPossibility(grid, currentPosition, direction).ifPresent(possibleObstructions::add);


            currentPosition = nextPosition;
            positionsVisited.add(currentPosition);
        }

        System.out.println(positionsVisited.size());
    }

    private boolean isOutOfBounds(Vector2D pos, char[][] grid) {
        return pos.y() < 0 || pos.y() >= grid.length ||
                pos.x() < 0 || pos.x() >= grid[pos.y()].length;
    }

    private Optional<Vector2D> checkLoopPossibility(char[][] grid, Vector2D currentPosition, Vector2D direction) {
        var possibleDirection = direction.rotate(90);

        var position = currentPosition.copy();

        while (true) {
            position = position.add(possibleDirection);

            if (isOutOfBounds(position, grid)) {
                break;
            }

            if (grid[position.y()][position.x()] == '#') {
                return Optional.of(currentPosition.add(direction));
            }
        }

        return Optional.empty();
    }

    private Vector2D findCharacter(char[][] grid, char token) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == token) {
                    return new Vector2D(x, y);
                }
            }
        }

        return null;
    }

    private void printGrid(char[][] grid, Set<Vector2D> visited) {
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

    @Override
    public void solvePart2(List<String> lines) {
        var grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        var characterPosition = findCharacter(grid, '^');

        Vector2D direction = new Vector2D(0, -1);
        if (characterPosition == null) {
            return;
        }

        var currentPosition = characterPosition.copy();

        Set<Vector2D> positionsVisited = new HashSet<>();
        positionsVisited.add(characterPosition);

        final Set<Vector2D> possibleObstructions = new HashSet<>();

        while (true) {

            var nextPosition = currentPosition.add(direction);

            if (isOutOfBounds(nextPosition, grid)) {
                break;
            }
            if (grid[nextPosition.y()][nextPosition.x()] == '#') {
                direction = direction.rotate(90);
                continue;
            }

            checkLoopPossibility(grid, currentPosition, direction).ifPresent(possibleObstructions::add);


            currentPosition = nextPosition;
            positionsVisited.add(currentPosition);
        }

        //Now check which obstructions cause loops.

        int count = 0;
        for (Vector2D possibleObstruction : possibleObstructions) {
            if(containsLoop(characterPosition, grid, possibleObstruction)) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    private boolean containsLoop(Vector2D characterPosition, char[][] grid, Vector2D obstruction) {
        var currentPosition = characterPosition.copy();
        var currentDirection = new Vector2D(0, -1);

        Set<String> visitedStates = new HashSet<>();
        while (true) {
            var nextPosition = currentPosition.add(currentDirection);



            if (isOutOfBounds(nextPosition, grid)) {
                break;
            }

            if (grid[nextPosition.y()][nextPosition.x()] == '#' || nextPosition.equals(obstruction)) {
                currentDirection = currentDirection.rotate(90);
                continue;
            }

            String state = nextPosition + "|" + currentDirection;

            if(visitedStates.contains(state)) {
                return true;
            }

            visitedStates.add(nextPosition + "|" + currentDirection);

            currentPosition = nextPosition;
        }

        return false;
    }
}
