package com.chien.FlightControl;

import javax.swing.*;

import com.chien.FlightControl.model.ControlModel;
import com.chien.FlightControl.controller.*;

public class SubTab extends JPanel {

    private JPanel panel;
    private ControlController controlController;
    private ControlModel controlModel;
    

    public SubTab() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();

        DragDropCanvas dragDropCanvas1 = new DragDropCanvas();
        tabbedPane.addTab("DragNdDrop 1", dragDropCanvas1);

        DragDropCanvas dragDropCanvas2 = new DragDropCanvas();
        tabbedPane.addTab("DragNdDrop 2", dragDropCanvas2);
        
        ControlController controlController = new ControlController();

        controlController.start(dragDropCanvas1);
        controlController.start(dragDropCanvas2);

    


        add(tabbedPane);
    }
    
}
