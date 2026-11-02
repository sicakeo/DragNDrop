package com.chien.flightcontrol.model;

import java.awt.Point;

public class Edge {
        public int toInputIndex; // Index of the input port on the target node

        public Node fromNode;
        public Node toNode;

        private Point fromPoint;
        private Point toPoint;

    public Edge(Node fromNode, Node toNode, int toInputIndex) {
        if (fromNode == null || toNode == null || fromNode == toNode) {
            throw new IllegalArgumentException("From and to nodes cannot be null or identical.");
        }

        if (toInputIndex < 0) {
            throw new IllegalArgumentException("toInputIndex cannot be negative.");
        }
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.toInputIndex = toInputIndex;
        updatePoints();
    }

     public void updatePoints() {
            // Always recompute based on current node bounds & input count
            fromPoint = fromNode.outputPort();

            int n = Math.max(1, toNode.getInputPortCount());
            int idx = toInputIndex;
            if (idx < 0) idx = 0;
            if (idx >= n) idx = n - 1;

            toPoint = toNode.inputPort(idx);
        }

    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public Point getFromPoint() {
        return fromPoint;
    }

    public Point getToPoint() {
        return toPoint;
    }
}
