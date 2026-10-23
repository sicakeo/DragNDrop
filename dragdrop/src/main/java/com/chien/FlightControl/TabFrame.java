package com.chien.FlightControl;

import javax.swing.*;


public class TabFrame {
    private JFrame frame;


    public TabFrame() {
        frame = new JFrame("Tab Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();

        SubTab subTab = new SubTab();
        tabbedPane.addTab("Sub Tab 1", subTab);

        SubTab subTab2 = new SubTab();
        tabbedPane.addTab("Sub Tab 2", subTab2);

        frame.add(tabbedPane);
    }
}
