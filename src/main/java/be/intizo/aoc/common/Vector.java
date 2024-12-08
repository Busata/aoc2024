package be.intizo.aoc.common;

import java.util.Objects;

public class Vector {

    private final double x;
    private final double y;


    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector direction) {
        return new Vector(this.x + direction.x, this.y + direction.y);
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public Vector copy() {
        return new Vector(this.x, this.y);
    }

    public Vector rotate(double angleInDegrees) {
        double angleInRadians = Math.toRadians(angleInDegrees);

        // Calculate new coordinates
        double newX = (double) Math.round(x * Math.cos(angleInRadians) - y * Math.sin(angleInRadians));
        double newY = (double) Math.round(x * Math.sin(angleInRadians) + y * Math.cos(angleInRadians));

        return new Vector(newX, newY);
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
    public double distance(Vector other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector2D = (Vector) o;
        return x == vector2D.x && y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector divideScalar(double magnitude) {
        return new Vector(x / magnitude, y / magnitude);
    }

    public Vector multiplyScalar(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }
}
