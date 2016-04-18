/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.swing.custom.component.collapsable;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Ankur
 */
public class test {
    public static void main(String[] args) {
       JFrame frame = new JFrame("Custom Buttons Demo");
		frame.setLayout(new FlowLayout());
		
        CollapasableContainer cc = new CollapasableContainer(1);
        cc.paintComponent(null);
    }
   
}
