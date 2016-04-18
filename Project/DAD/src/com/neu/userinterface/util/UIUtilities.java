/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.util;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Ankur
 */
public class UIUtilities {

    public static void initJFrameConfigurations(JFrame myJFrame) {
        myJFrame.setShape(new RoundRectangle2D.Double(0, 0, myJFrame.getWidth(), myJFrame.getHeight(), 40, 40));
        myJFrame.getRootPane().
                setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(200, 200, 200)));

    }

    public static void initButtonComponents(JButton myButton) {
        myButton.setContentAreaFilled(false);
        myButton.setFocusPainted(false);
        myButton.setBorderPainted(false);
    }

    public static void initButtonComponents(JToggleButton myButton) {
        myButton.setContentAreaFilled(false);
        myButton.setFocusPainted(false);
        myButton.setBorderPainted(false);
    }

    public static void changeFontColor(java.awt.event.FocusEvent evt, Color fg, Color bg) {
        JTextField field = (JTextField) evt.getSource();
        field.setText("");
        field.setForeground(fg);
        field.setBackground(bg);
    }

    public static void restrictAlphabeticInputs(java.awt.event.KeyEvent evt) {
        char vChar = evt.getKeyChar();
        if (!Character.isLetter(vChar)
                || (vChar == KeyEvent.VK_BACK_SPACE)
                || (vChar == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }

    public static boolean isEmpty(JTextField jTextField) {
        boolean flag = false;
        if (jTextField.getText().trim().equalsIgnoreCase("") || jTextField.getText().length() == 0) {
            flag = true;
        }
        return flag;
    }

    public static boolean isEmpty(JPasswordField jPasswordField) {
        boolean flag = false;
        if (jPasswordField.getPassword().length == 0) {
            flag = true;
        }
        return flag;
    }
    
    public static void restrictAlphabetinTxtFields(java.awt.event.KeyEvent evt) {
      char c = evt.getKeyChar();
      JTextField jt = (JTextField) evt.getSource();
      if (!((c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z')
              || (c == KeyEvent.VK_SPACE)
              || (c == KeyEvent.VK_DELETE))) {
          //getToolkit().beep();
          jt.setText("");
          jt.setBackground(Color.yellow);
          evt.consume();

      } else {
          jt.setBackground(Color.white);
      }
  }
  
  public static void restrictNumericinTxtFields(java.awt.event.KeyEvent evt) {
      char c = evt.getKeyChar();
      JTextField jt = (JTextField) evt.getSource();
      if (!((c >= '0') && (c <= '9')
              || (c == KeyEvent.VK_BACK_SPACE)
              || (c == KeyEvent.VK_DELETE))) {
          //getToolkit().beep();
          jt.setText("");
          jt.setBackground(Color.yellow);
          evt.consume();

      } else {
          jt.setBackground(Color.white);
      }
  }
  
  public static void restrictNumberofCharacters(java.awt.event.KeyEvent evt, int MAX_NUM) {
       JTextField jt = (JTextField) evt.getSource();
       if (!(jt.getText().length() <= MAX_NUM)) {
           jt.setText(jt.getText().substring(0, MAX_NUM - 1));
       }
   }
  
  public static boolean validateEmailAddress(String email) {
       java.util.regex.Pattern p = java.util.regex.
               Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
       java.util.regex.Matcher m = p.matcher(email);
       return m.matches();
   }
}
