package com.chien.flightcontrol.controller;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.model.Node;
import com.chien.flightcontrol.model.Edge;
import com.chien.flightcontrol.view.FlightControlView;

public class AttachedMouseController extends MouseAdapter {
    final int RESIZE_MARGIN = 10;
    final int MIN_SIZE = 40;
    private final FlightControlView view;
    private final FlightControlModel model;
    private Node resizingNode;
    private Point resizeAnchor;
    private Node connectFrom;
    private Node draggingNode;
    private Point dragOffset;

    public AttachedMouseController(FlightControlView view, FlightControlModel model) {
        this.view = view;
        this.model = model;

    }

    private boolean isInResizeZone(Node n, Point p) {
        Rectangle b = n.bounds;
        return p.x >= b.x + b.width - RESIZE_MARGIN &&
                p.x <= b.x + b.width &&
                p.y >= b.y + b.height - RESIZE_MARGIN &&
                p.y <= b.y + b.height;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();

        Node nForResize = view.nodeAt(p);
        if (nForResize != null && isInResizeZone(nForResize, p)) {
            resizingNode = nForResize;
            resizeAnchor = new Point(nForResize.bounds.x, nForResize.bounds.y);
            model.getNodes().remove(nForResize);
            model.getNodes().add(nForResize);
            view.repaint();
            return;
        }

        // Start connection if press on an output port
        Node onOut = view.nodeWithOutputAt(p);
        if (onOut != null) {
            connectFrom = onOut;
            view.setPreviewPoints(connectFrom.outputPort(), p);
            return;
        }

        // Otherwise maybe start dragging a node
        Node n = view.nodeAt(p);
        if (n != null) {
            draggingNode = n;
            dragOffset = new Point(p.x - n.bounds.x, p.y - n.bounds.y);
            // Bring node to front (simple z-order)
            model.getNodes().remove(n);
            model.getNodes().add(n);
            view.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = e.getPoint();

        if (resizingNode != null) {
            resizingNode = null;
            resizeAnchor = null;
            return;
        }

        if (connectFrom != null) {
            // Check if we can connect to a specific INPUT port
            FlightControlView.InputHit hit = view.inputPortAt(p);

            if (hit != null && hit.node != connectFrom) {
                Node src = connectFrom;
                Node dst = hit.node;
                int inputIndex = hit.portIndex;

                // Node type validation first
                if (!isValidConnection(src.getType(), dst.getType())) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid connection: " + src.getType().label + " -> " + dst.getType().label,
                            "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    connectFrom = null;
                    view.clearPreviewPoints();
                    return;
                }

                // Allow at most one edge per input port
                for (Edge d : model.getEdges()) {
                    if (d.toNode == dst && d.toInputIndex == inputIndex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "That input port is already connected.",
                                "Connection Error",
                                JOptionPane.ERROR_MESSAGE);
                        connectFrom = null;
                        view.clearPreviewPoints();
                        return;
                    }
                }

                // Create the edge
                model.addEdge(src, dst, inputIndex);

                // keep the backing JAXB <input> list in sync with the graph (if any)
                syncNodeInputsFromEdges(dst, model);
            }

            connectFrom = null;
            view.clearPreviewPoints();
            return;
        }

        draggingNode = null;
        dragOffset = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Allows the cursor to update when hovering over a node's output port or the
        // node itself
        Point p = e.getPoint();
        Node n = view.nodeAt(p);
        if (n != null && isInResizeZone(n, p)) {
            view.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else if (view.nodeWithOutputAt(e.getPoint()) != null) {
            view.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else if (n != null) {
            view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            view.setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();

                if (resizingNode != null && resizeAnchor != null) {
                    int newW = Math.max(MIN_SIZE, p.x - resizeAnchor.x);
                    int newH = Math.max(MIN_SIZE, p.y - resizeAnchor.y);
                    resizingNode.bounds.width = newW;
                    resizingNode.bounds.height = newH;

                    for (Edge edge : model.getEdges()) {
                        if (edge.fromNode == resizingNode || edge.toNode == resizingNode) {
                            edge.updatePoints();
                        }
                    }

                    view.repaint();
                    return;
                }

                if (connectFrom != null) {
                    // Update the preview of a potential connection
                    view.setConnectionPreview(connectFrom, p);
                    return;
                }

                if (draggingNode != null && dragOffset != null) {
                    draggingNode.bounds.x = p.x - dragOffset.x;
                    draggingNode.bounds.y = p.y - dragOffset.y;

                    for (Edge edge : model.getEdges()) {
                        if (edge.fromNode == draggingNode || edge.toNode == draggingNode) {
                            edge.updatePoints();
                        }
                    }

                    view.repaint();
                }
            }
    };

        view.addMouseListener(ma);
        view.addMouseMotionListener(ma);
    }
}