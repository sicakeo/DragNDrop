package com.chien.FlightControl;

import javax.swing.*;
public class DragDropCanvas extends JPanel {

    private JPanel panel;

    public DragDropCanvas() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("This is Drag Drop Canvas Content");
        panel.add(label);
    }
}
