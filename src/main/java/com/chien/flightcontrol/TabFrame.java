package com.chien.flightcontrol;

import javax.swing.*;

import com.chien.flightcontrol.subtab.PitchSubTab;


public class TabFrame {
    private JFrame frame;


    public TabFrame() {
        frame = new JFrame("Tab Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sub Tab 1", new PitchSubTab().build());

        frame.add(tabbedPane);
    }
}
