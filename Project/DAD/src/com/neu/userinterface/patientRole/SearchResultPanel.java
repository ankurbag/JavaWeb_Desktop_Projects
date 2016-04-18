/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.patientRole;

import com.alee.extended.layout.ComponentPanelLayout;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.WebAccordionStyle;
import com.alee.laf.button.WebButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.userAccount.UserAccount;
import com.neu.userinterface.util.UIUtilities;
import com.sun.glass.ui.Size;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author raunak
 */
public class SearchResultPanel extends javax.swing.JPanel {

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
    public SearchResultPanel(EcoSystem system) {

    }

    public SearchResultPanel(JPanel userProcessContainer, EcoSystem ecoSystem, Enterprise enterprise, ArrayList<Employee> doctors, UserAccount userAccount) {
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

        populateComboBox();
        init();

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
        //webAccordion1.addPane(icon, "Table", createtable());
    }

    public WebScrollPane createCustomForTableContent(String data) {
        JTable table = new JTable();
        return createCustomTableContent(150, 100, data, table);
    }

    public WebScrollPane createtable() {
        JTable table = new JTable();
        //WebScrollPane scrollPane = new WebScrollPane(workRequestJTable);
        //return scrollPane;
        return null;
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
            img = ImageIO.read(getClass().getResource("/com/imgs/administrator-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon.setImage(dimg);
        webAccordion1.setMultiplySelectionAllowed(false);
        for (Employee emp : doctors) {
            JPanel doctorPanel = new JPanel(new ComponentPanelLayout());
            doctorPanel.setBackground(Color.white);
            doctorPanel.setSize(400, 150);
            doctorPanel.setPreferredSize(new Dimension(400, 100));
            GridLayout grid = new GridLayout(3, 2);

            GridLayout layout = new GridLayout(3, 2);
            layout.setHgap(10);
            layout.setVgap(10);
            //doctorPanel.setLayout(layout);
            //doctorList[i].setBorder(BorderFactory.createLineBorder(Color.yellow));
            JLabel lab1 = new JLabel();
            lab1.setText("<html><b>Name</b> "+emp.getName() + " <b>Mob.</b>:<i> +1" +emp.getMobileNum()+"</i></html>");
            //lab1.setFont(new Font("Segoe UI", Font.BOLD, 12));
            JLabel lab2 = new JLabel();
            //emp.calculateAvgReviews();
            lab2.setText("<html><b>Speciality</b> "+emp.getSpeciality() + 
                    " <b>Rating</b>:<i style=color:red> " +emp.getAvgReviews()+"</i></html>");
            
            //lab2.setFont(new Font("Segoe UI"));
            JLabel lab3 = new JLabel();
            lab3.setText("<html><b>Address</b><i> "+emp.getAddress()+"</i></html>");
            
            //lab3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            JButton button = new JButton(" Book ");
            button.setBackground(Color.BLACK);
            button.setFont(new Font("Segoe UI", Font.BOLD, 12));
            button.setSize(70, 20);
            button.setPreferredSize(new Dimension(70, 20));
            button.setForeground(Color.WHITE);
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //Execute when button is pressed
                    MakeAnAppointmentPanel panel
                            = new MakeAnAppointmentPanel(userProcessContainer, system, enterprise, emp, userAccount);
                    userProcessContainer.add("MakeAnAppointmentPanel", panel);
                    CardLayout cardLayout = (CardLayout) userProcessContainer.getLayout();
                    cardLayout.next(userProcessContainer);
                    System.out.println("You clicked the button");
                }
            });

            JButton button1 = new JButton(" Book ");
            button1.setBackground(Color.BLACK);
            button1.setFont(new Font("Segoe UI", Font.BOLD, 12));
            button1.setSize(70, 20);
            button1.setPreferredSize(new Dimension(70, 20));
            button1.setForeground(Color.WHITE);
            //setLayout(new FlowLayout());
            doctorPanel.add(lab1);
            doctorPanel.add(lab2);
            doctorPanel.add(lab3);
            doctorPanel.add(button);
            //doctorPanel.add(button1);
            

            webAccordion1.addPane(icon, emp.getName(), createCustomForContent(doctorPanel));

            i++;
        }
        if (doctors.size() == 0) {
            webAccordion1.addPane(icon, "No Result", createCustomForContent("No doctor found in this criteria"));
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

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(420, 537));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Arrow-Back-3-icon.png"))); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("My Search Result");

        webAccordion1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(webAccordion1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
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
                .addContainerGap(25, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane2;
    private com.alee.extended.panel.WebAccordion webAccordion1;
    // End of variables declaration//GEN-END:variables
}
