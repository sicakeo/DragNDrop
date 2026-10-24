package com.chien.flightcontrol.controller.dnd;

import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.model.Node;
import com.chien.flightcontrol.model.NodeType;
import com.chien.flightcontrol.view.FlightControlView;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class CanvasDropHandler extends TransferHandler {
    private final FlightControlModel model;
    private final FlightControlView view;
    private int idCounter = 1;

    public CanvasDropHandler(FlightControlModel model, FlightControlView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        // Only accept strings (our node labels)
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) return false;

        try {
            Transferable t = support.getTransferable();
            String label = (String) t.getTransferData(DataFlavor.stringFlavor);
            
            // Match the string back to a NodeType
            NodeType type = NodeType.fromLabel(label);
            
            // Get coordinates of the drop
            Point dropPoint = support.getDropLocation().getDropPoint();
            
            // Add to model
            Node newNode = new Node(idCounter++, type, dropPoint.x, dropPoint.y);
            model.addNode(newNode);
            
            // Tell the canvas to redraw
            view.repaint();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}