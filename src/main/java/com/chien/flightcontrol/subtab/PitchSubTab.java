package com.chien.flightcontrol.subtab;

import javax.swing.*;

import com.chien.flightcontrol.DragDropCanvas;
import com.chien.flightcontrol.controller.*;
import com.chien.flightcontrol.model.FlightControlModel;

import java.awt.*;

public class PitchSubTab extends JPanel {

    private JPanel panel;
    private FlightControlModel controlModel;
    

    public PitchSubTab() {
        this.panel = new JPanel(new BorderLayout());  
        this.controlModel = new FlightControlModel();
  
    }
    

    public JComponent build() {
        FlightControlController controller = new FlightControlController();
        DragDropCanvas canvas = new DragDropCanvas();
        panel.add(canvas, BorderLayout.CENTER);
        return panel;
    }
}