/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.patientRole;

import com.alee.extended.layout.ComponentPanelLayout;
import com.alee.extended.panel.WebAccordionStyle;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.userAccount.UserAccount;
import com.neu.business.workQueue.AppointmentRequest;
import com.neu.business.workQueue.WorkRequest;
import com.neu.userinterface.util.UIUtilities;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author raunak
 */
public class MyReportPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem system;
    JPanel jPanel7;
    ArrayList<Employee> doctors;
    Enterprise enterprise;
    private UserAccount userAccount;
    private ImageIcon icon = null;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public MyReportPanel(EcoSystem system) {

    }

    public MyReportPanel(EcoSystem ecoSystem, Enterprise enterprise,
            JPanel userProcessContainer,  UserAccount userAccount) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = ecoSystem;
        this.doctors = doctors;
        this.userAccount = userAccount;
        this.enterprise = enterprise;
        icon = new ImageIcon();
        //populateTable();
        UIUtilities.initButtonComponents(backBtn);
        webAccordion1.setAccordionStyle(WebAccordionStyle.accordionStyle);

        //populateComboBox();
        init();
        //initWebAccordion();
    }

    public void initWebAccordion() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/com/imgs/administrator-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon.setImage(dimg);
        webAccordion1.setMultiplySelectionAllowed(false);
        webAccordion1.addPane(icon, "Some pane", createCustomForContent("Hello"));
        webAccordion1.addPane(icon, "Some long long pane", createCustomForContent("Zalak"));
        webAccordion1.addPane(icon, "Some other pane", createCustomForContent("Baby"));
        //accordion.addPane("new work table", workRequestJTable);
        webAccordion1.addPane(icon, "Table styling", createCustomForTableContent("Table is here"));
        webAccordion1.addPane(icon, "Table", createtable());
    }

    public WebScrollPane createCustomForTableContent(String data) {
        JTable table = new JTable();
        return createCustomTableContent(150, 100, data, table);
    }

    public WebScrollPane createtable() {
        JTable table = new JTable();
        WebScrollPane scrollPane = new WebScrollPane(jTable1);
        return scrollPane;
        //return null;
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

    public WebScrollPane createCustomContent(final int w, final int h, JPanel jPanel) {
        final WebScrollPane scrollPane = new WebScrollPane(jPanel, false);
        scrollPane.setPreferredSize(new Dimension(w, h));

        return scrollPane;
    }

    public WebScrollPane createCustomTableContent(final int w, final int h, String data, JTable tab) {
        // Content text area
        final WebTextArea textArea = new WebTextArea();// ( ExamplesManager.createLongString () );
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //Component add = textArea.add(jScrollPane1.add(workRequestJTable));
        textArea.setText(data);
        //textArea.add(workRequestJTable);

        // Text area scroll
        final WebScrollPane scrollPane = new WebScrollPane(textArea, false);
        //scrollPane.add(workRequestJTable);
        scrollPane.setPreferredSize(new Dimension(w, h));

        return scrollPane;
    }

    public WebScrollPane createCustomForContent(String data) {
        return createCustomContent(150, 100, data);
    }

    public WebScrollPane createCustomForContent(JPanel doctorJPanel) {
        return createCustomContent(110, 100, doctorJPanel);
    }

    public void init() {
        
        
        JPanel[] doctorList = new JPanel[200];
        ArrayList<JPanel> doctorPanelList = new ArrayList<>();
        int i = 0;
        BufferedImage img = null;
        try {
            //img = ImageIO.read(getClass().getResource("/com/imgs/administrator-icon.png"));
            img = ImageIO.read(getClass().getResource("/com/imgs/medical-report-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        icon.setImage(dimg);
        webAccordion1.setMultiplySelectionAllowed(false);
        for ( WorkRequest workRequest : userAccount.getWorkQueue().getWorkRequestList()) {
            
            JLabel label1 = new JLabel();
            label1.setText("<html><b>Report Status</b> " + workRequest.getStatus() +"</html>");
            
            JLabel label2 = new JLabel();
            label2.setText("<html><b>Prescription</b></html>");
            
            JTextArea prescriptionText = new JTextArea();
            prescriptionText.setRows(5);
            prescriptionText.setLineWrap(true);
            if(workRequest instanceof AppointmentRequest)
                prescriptionText.setText(((AppointmentRequest)workRequest).getPrescription());
            else
                prescriptionText.setText(workRequest.getMessage());
            JPanel requestPanel = new JPanel(new ComponentPanelLayout());
            
            JButton reviewButton = new JButton(" Review ");
            reviewButton.setBackground(Color.BLACK);
            reviewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            reviewButton.setSize(70, 20);
            reviewButton.setPreferredSize(new Dimension(70, 20));
            reviewButton.setForeground(Color.WHITE);
            reviewButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //Execute when reviewButton is pressed
                    ReviewDoctorPanel panel
                            = new ReviewDoctorPanel(userProcessContainer, system, enterprise, 
                                    workRequest.getReceiver().getEmployee(), userAccount);
                    userProcessContainer.add("ReviewDoctorPanel", panel);
                    CardLayout cardLayout = (CardLayout) userProcessContainer.getLayout();
                    cardLayout.next(userProcessContainer);
                    System.out.println("You clicked the reviewButton");
                }
            });

            JButton button1 = new JButton(" Book ");
            button1.setBackground(Color.BLACK);
            button1.setFont(new Font("Segoe UI", Font.BOLD, 12));
            button1.setSize(70, 20);
            button1.setPreferredSize(new Dimension(70, 20));
            button1.setForeground(Color.WHITE);
            requestPanel.add(label1);
            requestPanel.add(label2);
            requestPanel.add(prescriptionText);
            requestPanel.add(reviewButton);
            webAccordion1.addPane(icon, workRequest.getRequestId(), createCustomForContent(requestPanel));
            i++;
        }
        if (i == 0) {
            webAccordion1.addPane(icon, "No Result", createCustomForContent("No Report found."));
        }

        revalidate();
        repaint();

    }

    private void populateComboBox() {
//        networkJComboBox.removeAllItems();
//        enterpriseTypeJComboBox.removeAllItems();
//
//        for (Network network : system.getNetworkList()) {
//            networkJComboBox.addItem(network);
//        }
//
//        for (Enterprise.EnterpriseType type : Enterprise.EnterpriseType.values()) {
//            enterpriseTypeJComboBox.addItem(type);
//        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        webAccordion1 = new com.alee.extended.panel.WebAccordion();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(239, 239, 239));
        setPreferredSize(new java.awt.Dimension(420, 537));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Arrow-Back-3-icon.png"))); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("My Search Result");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Request", "Report"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        webAccordion1.add(jScrollPane1);

        jScrollPane2.setViewportView(webAccordion1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private com.alee.extended.panel.WebAccordion webAccordion1;
    // End of variables declaration//GEN-END:variables
}
