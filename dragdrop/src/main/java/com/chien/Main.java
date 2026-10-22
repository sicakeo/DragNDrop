package com.chien;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drag and Drop Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            JLabel label = new JLabel("Drag and drop a file here");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setTransferHandler(new FileTransferHandler());

            frame.add(label, BorderLayout.CENTER);
            frame.setVisible(true);

            JPanel panel = new JPanel();

            JTabbedPane tabbedPane1 = new JTabbedPane();
            JTabbedPane tabbedPane2 = new JTabbedPane();
            JTabbedPane tabbedPane3 = new JTabbedPane();

            panel.add(tabbedPane1);
            panel.add(tabbedPane2);
            panel.add(tabbedPane3);


        });
    }
}