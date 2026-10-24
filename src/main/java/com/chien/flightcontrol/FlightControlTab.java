package com.chien.flightcontrol;

import com.chien.flightcontrol.subtab.*;

import javax.swing.*;

public class FlightControlTab {
    private PitchSubTab pitchSubTab;
    private RollSubTab rollSubTab;
    private JTabbedPane tabbedPane;

    public FlightControlTab(TabFrame tabFrame, String label) {
        this.pitchSubTab = new PitchSubTab(tabFrame, "Pitch");
        this.rollSubTab = new RollSubTab(tabFrame, "Roll");

        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.addTab("Pitch", this.pitchSubTab.build());
        this.tabbedPane.addTab("Roll", this.rollSubTab.build());
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
    