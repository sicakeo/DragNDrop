package com.chien;

import javax.swing.*;

import com.chien.flightcontrol.TabFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TabFrame tabFrame = new TabFrame();
        });
    }
}