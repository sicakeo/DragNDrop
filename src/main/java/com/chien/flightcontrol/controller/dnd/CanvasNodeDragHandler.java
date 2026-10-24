package com.chien.flightcontrol.controller.dnd;

import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.model.Node;
import com.chien.flightcontrol.view.FlightControlView;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CanvasNodeDragHandler extends MouseAdapter {
    private final FlightControlModel model;
    private final FlightControlView view;
    
    private Node draggingNode = null;
    private Point dragOffset = null;

    public CanvasNodeDragHandler(FlightControlModel model, FlightControlView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        List<Node> nodes = model.getNodes();
        
        // Loop in reverse so if nodes overlap, you grab the top one
        for (int i = nodes.size() - 1; i >= 0; i--) {
            Node n = nodes.get(i);
            if (n.bounds.contains(p)) {
                draggingNode = n;
                // Calculate the offset between the mouse click and the node's top-left corner
                dragOffset = new Point(p.x - n.bounds.x, p.y - n.bounds.y);
                
                // Bring the clicked node to the front of the Z-order
                nodes.remove(i);
                nodes.add(n);
                view.repaint();
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggingNode != null && dragOffset != null) {
            // Calculate the raw proposed position
            int newX = e.getX() - dragOffset.x;
            int newY = e.getY() - dragOffset.y;

            // Determine the maximum boundaries so the node doesn't slide off the right/bottom edges
            int maxX = view.getWidth() - draggingNode.bounds.width;
            int maxY = view.getHeight() - draggingNode.bounds.height;

            // Clamp the X and Y coordinates to keep the node strictly inside the view
            draggingNode.bounds.x = Math.max(0, Math.min(newX, maxX));
            draggingNode.bounds.y = Math.max(0, Math.min(newY, maxY));
            
            view.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Clear the dragging state
        draggingNode = null;
        dragOffset = null;
    }
}