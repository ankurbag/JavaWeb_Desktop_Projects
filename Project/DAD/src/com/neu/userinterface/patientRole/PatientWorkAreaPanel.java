/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.patientRole;

import com.neu.userinterface.administrativeRole.*;
import com.neu.userinterface.systemAdminWorkArea.*;
import com.neu.business.DB4OUtil.DB4OUtil;
import com.neu.userinterface.*;
import com.neu.business.EcoSystem;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.network.Network;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
import com.neu.userinterface.util.UIUtilities;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ankur
 */
public class PatientWorkAreaPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoginPanel
     */
    private EcoSystem ecoSystem;
    private JPanel userProcessContainer;
    DB4OUtil dB4OUtil;
    private Enterprise enterprise;
    private UserAccount account;

    public PatientWorkAreaPanel(EcoSystem ecoSystem, JPanel userProcessContainer, DB4OUtil dB4OUtil) {
    }

    public PatientWorkAreaPanel(JPanel userProcessContainer, UserAccount account, Enterprise enterprise, Organization organization, DB4OUtil dB4OUtil, EcoSystem ecoSystem) {
        initComponents();
        this.enterprise = enterprise;
        this.userProcessContainer = userProcessContainer;
        this.dB4OUtil = dB4OUtil;
        this.account = account;
        this.ecoSystem = ecoSystem;
        UIUtilities.initButtonComponents(menuButton);
        menuPanel.setVisible(false);
        username.setText(account.getUsername());
        headingLabel.setText("My Dashboard");
        //splitPane.setBottomComponent(new PatientsDashboard(ecoSystem));
        PatientsDashboard panel = new PatientsDashboard(displayPanel, ecoSystem, enterprise, account);
        displayPanel.add("PatientsDashboard", panel);
        CardLayout cardLayout = (CardLayout) displayPanel.getLayout();
        cardLayout.next(displayPanel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        menuButton = new javax.swing.JToggleButton();
        menuPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        splitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        headingLabel = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        username = new javax.swing.JLabel();

        setBackground(new java.awt.Color(8, 157, 227));
        setPreferredSize(new java.awt.Dimension(420, 650));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Patient Login");

        menuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/User-Interface-Menu-icon.png"))); // NOI18N
        menuButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        menuButton.setName("menuBtn"); // NOI18N
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        menuPanel.setBackground(new java.awt.Color(8, 157, 227));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        menuPanel.setBorder(dropShadowBorder1);
        menuPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPanelMouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(8, 157, 227));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Logout");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(415, 541));
        jPanel3.setLayout(new java.awt.BorderLayout());

        splitPane.setDividerLocation(40);
        splitPane.setDividerSize(1);
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 51)));

        headingLabel.setBackground(new java.awt.Color(255, 255, 255));
        headingLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        headingLabel.setText("Manage Staff");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(headingLabel)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        splitPane.setTopComponent(jPanel1);

        displayPanel.setBackground(new java.awt.Color(239, 239, 239));
        displayPanel.setAutoscrolls(true);
        displayPanel.setPreferredSize(new java.awt.Dimension(420, 494));
        displayPanel.setLayout(new java.awt.CardLayout());
        splitPane.setBottomComponent(displayPanel);

        jPanel3.add(splitPane, java.awt.BorderLayout.CENTER);

        username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("Username");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        // TODO add your handling code here:
        if (menuButton.isSelected()) {
            menuPanel.setVisible(true);
        } else {
            menuPanel.setVisible(false);
        }
    }//GEN-LAST:event_menuButtonActionPerformed

    private void menuPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanelMouseClicked
        // TODO add your handling code here:
        userProcessContainer.removeAll();
        LoginPanel panel = new LoginPanel(ecoSystem, userProcessContainer, dB4OUtil);
        userProcessContainer.add("LoginPanel", panel);
        CardLayout cardLayout = (CardLayout) userProcessContainer.getLayout();
        cardLayout.next(userProcessContainer);
        dB4OUtil.storeSystem(ecoSystem);
    }//GEN-LAST:event_menuPanelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel displayPanel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton menuButton;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}