package com.chien;

import javax.swing.*;

import com.chien.flightcontrol.FlightControlTab;
import com.chien.flightcontrol.TabFrame;

public class SubTab extends JPanel {
    private String name;
    private JTabbedPane tabbedPane;
    private TabFrame tabFrame;
    private FlightControlTab flightControlTab;

    public SubTab(TabFrame tabFrame, String name) {
        this.name = name;
        this.tabFrame = tabFrame;
        this.tabbedPane = new JTabbedPane();
        this.flightControlTab = new FlightControlTab(tabFrame, "Flight Control");
        this.tabbedPane.addTab("Flight Control", flightControlTab.getTabbedPane());
    }

    public String getName() {
        return name;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}
