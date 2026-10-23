package com.chien.FlightControl.model;

import java.awt.Point;

public class Edge {
        public int toInputIndex; // Index of the input port on the target node

        public Point fromPoint;
        public Point toPoint;

    public Edge(Node source, Node target, int toInputIndex) {
        if (source == null || target == null || source == target) {
            throw new IllegalArgumentException("Source and target nodes cannot be null or identical.");
        }
        this.fromPoint = source.outputPort();
        this.toPoint = target.inputPort(toInputIndex);
        this.toInputIndex = toInputIndex;
        updateFromPoint(this.fromPoint);
    }

    public Edge(Point fromPoint, Point toPoint, int toInputIndex) {
        if (fromPoint == null || toPoint == null || fromPoint == toPoint) {
            throw new IllegalArgumentException("From and to points cannot be null or identical.");
        }

        if (toInputIndex < 0) {
            throw new IllegalArgumentException("toInputIndex cannot be negative.");
        }
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.toInputIndex = toInputIndex;
        updateFromPoint(this.fromPoint);
    }

    public void updateFromPoint(Point fromPoint) {
        if (fromPoint == null) {
            throw new IllegalArgumentException("From point cannot be null.");
        }
        this.fromPoint = fromPoint;
        this.toPoint = new Point(toPoint.x, toPoint.y);
    }
}
