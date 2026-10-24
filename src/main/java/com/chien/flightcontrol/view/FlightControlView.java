package com.chien.flightcontrol.view;

import java.awt.*;
import javax.swing.*;

import com.chien.flightcontrol.controller.FlightControlController;
import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.model.Node;


public class FlightControlView extends JPanel {
    private FlightControlController controller;
    private FlightControlModel model;

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
}
