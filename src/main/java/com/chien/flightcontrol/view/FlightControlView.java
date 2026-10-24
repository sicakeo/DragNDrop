package com.chien.flightcontrol.view;

import javax.swing.*;

import com.chien.flightcontrol.controller.FlightControlController;
import com.chien.flightcontrol.model.FlightControlModel;

public class FlightControlView extends JComponent {
    private FlightControlController controller;
    private FlightControlModel model;

    public FlightControlView(FlightControlController controller, FlightControlModel model) {
        this.controller = controller;
        this.model = model;
    }
    
}
