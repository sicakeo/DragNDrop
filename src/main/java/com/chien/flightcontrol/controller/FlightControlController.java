package com.chien.flightcontrol.controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.chien.flightcontrol.controller.dnd.CanvasDropHandler;
import com.chien.flightcontrol.controller.dnd.CanvasNodeDragHandler;
import com.chien.flightcontrol.controller.dnd.PaletteDragHandler;
import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.model.NodeType;
import com.chien.flightcontrol.view.FlightControlView;

import java.awt.*;

public class FlightControlController extends JPanel {
    public FlightControlController() {

    }   

    public void start(JPanel mainPanel) {

        // Create the model and view
        FlightControlModel model = new FlightControlModel();
        FlightControlView view = new FlightControlView(this, model);
        CanvasNodeDragHandler dragNodeHandler = new CanvasNodeDragHandler(model, view);

        view.setTransferHandler(new CanvasDropHandler(model, view)); // Set a default TransferHandler for the view
        view.addMouseListener(dragNodeHandler); // Add a mouse listener for dragging nodes
        view.addMouseMotionListener(dragNodeHandler); // Add a mouse motion listener for dragging nodes
        
        Component pallete = buildPallete(view);
        
        mainPanel.add(pallete, BorderLayout.NORTH);
        mainPanel.add(view, BorderLayout.CENTER);
    }

    public static Component buildPallete(FlightControlView controlView) { // Create a panel to hold the palette items
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.GRAY);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        
        for (NodeType nodeType : NodeType.values()) {
    
            JLabel item = new JLabel(nodeType.getLabel().toUpperCase());
            item.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Gives the rectangle its border
            item.setBackground(Color.LIGHT_GRAY);
            item.setOpaque(true); // Ensures background color is visible
            item.setPreferredSize(new Dimension(50, 50)); // Sets the size of the rectangle
            item.setHorizontalAlignment(SwingConstants.CENTER); // Centers the text horizontally
            item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Changes cursor to hand when hovering
            // item.setToolTipText(nodeType.getDescription()); // Shows a tooltip with the description when hovering
            
            item.setTransferHandler(new PaletteDragHandler(nodeType));
            item.addMouseListener(PaletteDragHandler.createDragInitiator()); 
            panel.add(item);
        }

        return panel;
    }
}
