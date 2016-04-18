/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.workQueue;

import com.alee.extended.panel.WebAccordion;
import com.alee.extended.panel.WebAccordionStyle;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 *
 * @author Zak
 */
public class NewJFrame extends javax.swing.JFrame {

    private ImageIcon icon = null;
    private WebAccordion accordion = null;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        icon = new ImageIcon();
        webAccordion1.setAccordionStyle(WebAccordionStyle.accordionStyle);
        accordion = webAccordion1;
        WebAccordionExample();
    }

    public final void WebAccordionExample() {
        // Simple pane
        //accordion = new WebAccordion ( WebAccordionStyle.accordionStyle );
        BufferedImage img = null;
        try {
           img = ImageIO.read(new File("D:\\AED\\Bag_Ankur_001619221\\bag_ankur_001619221\\Project\\DAD\\src\\com\\imgs\\stop-the-creditor-calling.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon.setImage(dimg);
        accordion.setMultiplySelectionAllowed(false);
        accordion.addPane(icon, "Some pane", createCustomForContent("Hello"));
        accordion.addPane(icon, "Some long long pane", createCustomForContent("Zalak"));
        accordion.addPane(icon, "Some other pane", createCustomForContent("Baby"));
        //accordion.addPane("new work table", workRequestJTable);
        accordion.addPane(icon, "Table styling", createCustomForTableContent("Table is here"));
        accordion.addPane(icon, "Table", createtable());
    }
    
     public WebScrollPane createCustomForTableContent(String data) {
         JTable table = new JTable();
        return createCustomTableContent(150, 100, data,table);
    }

    public WebScrollPane createtable() {
        JTable table = new JTable();
        WebScrollPane scrollPane = new WebScrollPane(workRequestJTable);
        return scrollPane;
    }

    public WebScrollPane createCustomContent(final int w, final int h, String data) {
        // Content text area
        final WebTextArea textArea = new WebTextArea();// ( ExamplesManager.createLongString () );
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //Component add = textArea.add(jScrollPane1.add(workRequestJTable));
        textArea.setText(data);

        // Text area scroll
        final WebScrollPane scrollPane = new WebScrollPane(textArea, false);
        scrollPane.setPreferredSize(new Dimension(w, h));

        return scrollPane;
    }
    
     public WebScrollPane createCustomTableContent(final int w, final int h, String data,JTable tab) {
        // Content text area
        final WebTextArea textArea = new WebTextArea();// ( ExamplesManager.createLongString () );
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //Component add = textArea.add(jScrollPane1.add(workRequestJTable));
        textArea.setText(data);
        textArea.add(workRequestJTable);

        // Text area scroll
        final WebScrollPane scrollPane = new WebScrollPane(textArea, false);
        //scrollPane.add(workRequestJTable);
        scrollPane.setPreferredSize(new Dimension(w, h));

        return scrollPane;
    }

    public WebScrollPane createCustomForContent(String data) {
        return createCustomContent(150, 100, data);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webAccordion1 = new com.alee.extended.panel.WebAccordion();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Sender", "Receiver", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        webAccordion1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(webAccordion1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 264, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webAccordion1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.alee.extended.panel.WebAccordion webAccordion1;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}