package com.chien.FlightControl;

import javax.swing.*;

import com.chien.FlightControl.model.ControlModel;
import com.chien.FlightControl.controller.ControlController;

public class ControlView extends JComponent {
    private ControlController controller;
    private ControlModel model;

    public ControlView(ControlController controller, ControlModel model) {
        this.controller = controller;
        this.model = model;
    }
    
}
