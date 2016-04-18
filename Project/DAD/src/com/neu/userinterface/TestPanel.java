/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface;

/**
 *
 * @author Ankur
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 * @author rohan
 */
public class TestPanel {

    private JFrame frame;
    private JPanel panel;
    private JTextArea jTextArea;
    private JButton butt;

    TestPanel() {
        createGUI();
        process1();
    }

    private void process1() {
        jTextArea.setText("hii");
        frame.setVisible(true);
    }

    private void createGUI() {
        butt = new JButton("button");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        jTextArea = new JTextArea(20, 20);
        panel.add(jTextArea);
        panel.add(butt);
        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                final JPanel pan = new JPanel();
                JButton but = new JButton("CHANGED");
                pan.add(but);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.add(pan);
                        butt.setVisible(false);
                        jTextArea.setVisible(false);
                        pan.revalidate();
                        pan.repaint();
                    }
                });
            }
        });
        frame.add(panel);
        frame.pack();
        frame.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestPanel();
            }
        });
    }
}