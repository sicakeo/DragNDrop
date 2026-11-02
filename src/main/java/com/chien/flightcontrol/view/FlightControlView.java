package com.chien.flightcontrol.view;

import java.awt.*;
import java.awt.geom.Path2D;

import javax.swing.*;

import com.chien.flightcontrol.controller.FlightControlController;
import com.chien.flightcontrol.model.Edge;
import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.model.Node;


public class FlightControlView extends JPanel {
    private FlightControlController controller;
    private FlightControlModel model;
    
    private Point previewTo;
    private Point previewFrom;

     // Visual constants
    public static final int PORT_SIZE = 12;
    public static final Stroke EDGE_STROKE = new BasicStroke(1f);
    public static final Stroke PREVIEW_STROKE = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f,
            new float[] { 8f, 8f }, 0f);

    public FlightControlView(FlightControlController controller, FlightControlModel model) {
        this.controller = controller;
        this.model = model;

        setOpaque(true);
        setBackground(Color.WHITE);
        setPreferredSize(new java.awt.Dimension(800, 600));
    }

    public FlightControlView(FlightControlModel controlModel) {
        this.model = controlModel;
    }
    
    public void setPreviewPoints(Point from, Point to) {
        this.previewFrom = from;
        this.previewTo = to;
        repaint();
    }

    public void clearPreviewPoints() {
        this.previewFrom = null;
        this.previewTo = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother text and shapes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw every node stored in the model
        for (Node node : model.getNodes()) {
            drawNode(g2, node);
        }
        // Draw every edge stored in the model
        for (Edge edge : model.getEdges()) {
            drawEdge(g2, edge);
        }

        drawPreviewEdge(g2);

    }

    private void drawNode(Graphics2D g2, Node node) {
        Rectangle b = node.bounds;
        
        // 1. Draw the background rectangle
        g2.setColor(new Color(240, 240, 240));
        g2.fillRect(b.x, b.y, b.width, b.height);
        
        // 2. Draw the border
        g2.setColor(Color.BLACK);
        g2.drawRect(b.x, b.y, b.width, b.height);
        
        // 3. Draw the label centered inside the rectangle
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(node.getLabel());
        int textX = b.x + (b.width - textWidth) / 2;
        int textY = b.y + ((b.height - fm.getHeight()) / 2) + fm.getAscent();
        
        g2.drawString(node.getLabel(), textX, textY);
    }

    private void drawEdge(Graphics2D g2, Edge edge) {
        g2.setColor(Color.BLACK);
        g2.setStroke(EDGE_STROKE);
        
        g2.drawLine(edge.getFromPoint().x,
                      edge.getFromPoint().y,
                      edge.getToPoint().x,
                      edge.getToPoint().y);
    }

    private void drawPreviewEdge(Graphics2D g2) {
        if(previewFrom != null && previewTo != null) {
            g2.setColor(new Color(255, 180, 0));
            g2.setStroke(PREVIEW_STROKE);
            drawOrthogonalEdge(g2, previewFrom, previewTo, 1);
            drawArrowHead(g2, previewTo);
        }
    }

    private void drawOrthogonalEdge(Graphics2D g2, Point from, Point to, int slots) {
        final double SPREAD = 0.5; // Spread factor for orthogonal edge routing
        double offset = SPREAD / slots; // Offset for each slot in the orthogonal edge routing
        double xmid = from.x + (to.x - from.x) / 2.0 + offset;
        double ymid = from.y + (to.y - from.y) / 2;
        int buffer = 20;
        double x1, x2, y1;
        x1 = from.x + buffer;

        // Edge routing logic - units are in pixels
        if ((from.x < to.x + 80) && (from.x > to.x - 20)) {
            x2 = from.x - 100;
        } else {
            x2 = to.x - buffer;
        }
        if (from.y > to.y) {
            y1 = from.y + (buffer * 3);
            if (from.y > to.y + 100) {
                y1 = ymid;
                if (from.x > to.x - 20) {
                    x2 = to.x - buffer;
                }
            }
        } else {
            y1 = from.y - (buffer * 3);
            if (from.y < to.y - 100) {
                y1 = ymid;
                if (from.x > to.x - 20) {
                    x2 = to.x - buffer;
                }
            }
        }

        // Draw the path behind the nodes if they are too close
        Path2D path = new Path2D.Double();
        path.moveTo(from.x, from.y);
        // Only reroute for area around and behind fromPoint node
        if ((from.x > to.x - 20) && !((from.x < to.x + 85) && ((from.y < (to.y + 42)) && (from.y > (to.y - 42))))) {
            path.lineTo(x1, from.y);
            path.lineTo(x1, y1);
            path.lineTo(x2, y1);
            path.lineTo(x2, to.y);
            path.lineTo(to.x, to.y);
        } else {
            path.lineTo(xmid, from.y);
            path.lineTo(xmid, to.y);
            path.lineTo(to.x, to.y);
        }
        g2.draw(path);
    }

    private void drawArrowHead(Graphics2D g2, Point to) {
        double x, y;

        x = to.x;
        y = to.y;
        g2.drawLine(to.x, to.y, (int)(x - 10), (int)(y - 10));
        g2.drawLine(to.x, to.y, (int)(x - 10), (int)(y + 10));

        to.x = (int)x;
        to.y = (int)y;
    }
}
