package com.chien.flightcontrol.model;

import java.util.List;
import java.util.ArrayList;

public class FlightControlModel {
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public FlightControlModel() {
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdge(Node source, Node target, int toInputIndex) {
        Edge edge = new Edge(source, target, toInputIndex);
        edges.add(edge);
    }
    
}
