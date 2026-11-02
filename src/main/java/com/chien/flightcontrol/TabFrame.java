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
        
        SubTab anotherSubTab = new SubTab(this, "System");
        tabbedPane.addTab("System", anotherSubTab.getTabbedPane());

        
    
        frame.add(tabbedPane, BorderLayout.CENTER);
    }
}
