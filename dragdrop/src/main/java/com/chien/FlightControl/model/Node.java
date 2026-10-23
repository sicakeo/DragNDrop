package com.chien.FlightControl.model;

import java.awt.Point;
import java.awt.Rectangle;

public final class Node {
    public Rectangle bounds;
    public String label;

    private int id;
    private NodeType type;
    private int inputPorts = 0;

    public Node(int id, NodeType type, int x, int y) {
        this.id = id;
        this.type = type;
        this.bounds = new Rectangle(x, y);
        this.label = type.label;
        this.inputPorts = type.inPorts;
    }

    //Returns the position of the input port at the given index. 
    //If the index is out of bounds, it will be clamped to the valid range.
    public Point inputPort(int index) {
        int basePorts = (type != null) ? type.inPorts : 0;
        int n = Math.max(1, Math.max(inputPorts, basePorts));

        if (index < 0) index = 0;
        if (index >= n) index = n - 1;

        double slot = (index + 1) / (double) (n + 1);
        int y = (int) Math.round(bounds.y + slot * bounds.height);
        int x = bounds.x;
        return new Point(x, y);
    }

    public Point outputPort() {
        return new Point(bounds.x + bounds.width, bounds.y + bounds.height / 2);
    }

    public Rectangle inputPortRect(int index, int size) {
        Point p = inputPort(index);
        return new Rectangle(p.x - size / 2, p.y - size / 2, size, size);
    }

    public Rectangle inputPortRect(int size) {
        return inputPortRect(0, size);
    }

    public Rectangle outputPortRect(int size) {
        Point p = outputPort();
        return new Rectangle(p.x - size / 2, p.y - size / 2, size, size);
    }

    public NodeType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }
}
