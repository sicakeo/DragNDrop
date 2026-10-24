package com.chien.flightcontrol;

import java.awt.BorderLayout;

import javax.swing.*;

import com.chien.SubTab;
import com.chien.flightcontrol.subtab.*;

public class TabFrame {
    private JFrame frame;

    public TabFrame() {
        frame = new JFrame("Tab Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        FlightControlTab flightControlTab = new FlightControlTab(this, "Flight Control");
        tabbedPane.addTab("Flight Control", flightControlTab.getTabbedPane());
        
        SubTab anotherSubTab = new SubTab(this, "Another Sub Tab");
        tabbedPane.addTab("Another Sub Tab", anotherSubTab.getTabbedPane());
        
        frame.add(tabbedPane, BorderLayout.CENTER);
    }
}
