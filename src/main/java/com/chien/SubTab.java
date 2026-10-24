package com.chien;

import javax.swing.*;

import com.chien.flightcontrol.TabFrame;

public class SubTab extends JPanel {
    private String name;
    private JTabbedPane tabbedPane;
    private TabFrame tabFrame;

    public SubTab(TabFrame tabFrame, String name) {
        this.name = name;
        this.tabFrame = tabFrame;
        this.tabbedPane = new JTabbedPane();
    }

    public String getName() {
        return name;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}
