package com.chien.flightcontrol.controller.dnd;

import com.chien.flightcontrol.model.NodeType;

import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaletteDragHandler extends TransferHandler {
    private final NodeType nodeType;

    public PaletteDragHandler(NodeType nodeType) {
        super("text");
        this.nodeType = nodeType;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        // Send the label of the node being dragged
        return new StringSelection(nodeType.label);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    // A reusable mouse listener to start the drag process
    public static MouseAdapter createDragInitiator() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent c = (JComponent) e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
        };
    }
}