package com.chien.flightcontrol.widgets;

import javax.swing.*;
import java.awt.*;

public class baseTab {
    // attributes common to all tabs
    public JPanel panel;
    public String label;
    public Integer version;

    public void loadData() {

    }

    public baseTab(String LABEL) {

        label = LABEL;
        panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for panel1

        panel.setBackground(new Color(200, 220, 255)); // Light blue background
    }
}
