package com.chien.flightcontrol.subtab;

import javax.swing.*;

import com.chien.flightcontrol.TabFrame;
import com.chien.flightcontrol.controller.*;
import com.chien.flightcontrol.model.FlightControlModel;
import com.chien.flightcontrol.view.FlightControlView;
import com.chien.flightcontrol.widgets.baseTab;

import java.awt.*;

public class PitchSubTab extends baseTab {

    private JPanel panel;
    private FlightControlModel controlModel;
    private FlightControlView controlView;
    private TabFrame tabFrame;
    
    public PitchSubTab(TabFrame tabFrame, String label) {
        super(label);
        this.tabFrame = tabFrame;
        this.panel = new JPanel(new BorderLayout());  
        this.controlModel = new FlightControlModel();
        this.controlView = new FlightControlView(this.controlModel);
    }
    
    public JPanel build() {
        FlightControlController controller = new FlightControlController();
        controller.start(panel);
   
        return panel;
    }
}