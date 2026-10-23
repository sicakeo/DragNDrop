package com.chien.FlightControl.controller;

import com.chien.FlightControl.model.ControlModel;
import com.chien.FlightControl.ControlView;
import com.chien.FlightControl.DragDropCanvas;
import com.chien.FlightControl.FileTransferHandler;
import com.chien.FlightControl.model.NodeType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class ControlController {
    public ControlController() {

    }   

    public void start(JPanel mainPanel) {
        ControlModel model = new ControlModel();
        ControlView view = new ControlView(this, model);

        Component pallete = buildPallete(view);
        DragDropCanvas dragDropCanvas = new DragDropCanvas();
        
        mainPanel.add(pallete, BorderLayout.NORTH);
        mainPanel.add(view, BorderLayout.CENTER);
        mainPanel.add(dragDropCanvas, BorderLayout.SOUTH);
    }

    public static Component buildPallete(ControlView controlView) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.GRAY);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        
        for (NodeType nodeType : NodeType.values()) {
    
            JLabel item = new JLabel(nodeType.getLabel());
            item.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Gives the rectangle its border
            item.setBackground(Color.LIGHT_GRAY);
            item.setOpaque(true); // Ensures background color is visible
            item.setPreferredSize(new Dimension(50, 50)); // Sets the size of the rectangle
            item.setHorizontalAlignment(SwingConstants.CENTER); // Centers the text horizontally

            item.setTransferHandler(new FileTransferHandler());
            
            item.addMouseListener(new MouseAdapter() {
                @Override 
                public void mousePressed(java.awt.event.MouseEvent e) {    
                    JComponent c = (JComponent) item;
                    TransferHandler handler = c.getTransferHandler();

                    handler.exportAsDrag(c, null, TransferHandler.COPY);
                }
            });
            panel.add(item);
        }

        return panel;
    }
}
