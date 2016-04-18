/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.patientRole;

import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.organization.AdminOrganization;
import com.neu.business.organization.DoctorOrganization;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
import com.neu.business.workQueue.EmergencyCallRequest;
import com.neu.userinterface.util.UIUtilities;
import com.twilio.sdk.TwilioRestException;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author raunak
 */
public class CallHospitalAdminPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private Timer timer;
    private long startTime = -1;
    private long duration = 15000;
    EmergencyCallRequest emergencyCallRequest;
    private JLabel label;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public CallHospitalAdminPanel(EcoSystem ecoSystem, Enterprise enterprise, JPanel userProcessContainer, UserAccount userAccount, EmergencyCallRequest emergencyCallRequest) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.ecoSystem = ecoSystem;
        this.enterprise = enterprise;
        this.userAccount = userAccount;
        this.emergencyCallRequest = emergencyCallRequest;
        msgLabel.setVisible(false);
        cancelRequest.setEnabled(true);
        UIUtilities.initButtonComponents(backBtn);

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    try {
                        clockTime = duration;
                        timer.stop();
                        replaceImage();
                        cancelRequest.setEnabled(false);
                        msgLabel.setVisible(true);
                        msgLabel.setText("Emergency Call has been placed!!");
                        String mob ="6172598868";
                        for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                            if (org instanceof AdminOrganization) {
                                for (Employee emp : org.getEmployeeDirectory().getEmployeeList()) {
                                    System.out.print(emp.getMobileNum());
                                    //mob = 
                                    if (emp.getMobileNum() != null) {
                                        mob = emp.getMobileNum();
                                        break;
                                    }
                                }
                            }
                        }
                        CallMessageUtility.makeCallSendMessage("+1" + mob);
                        
                        updateEmergencyCallRequest("Emergency Call has been placed!!", "Waiting");
                        //timer.setDelay(10000);
                        //CallMessageUtility.sendMessage("+16172598868", "Dear Customer,Emergency Call has been placed to the Emergency ward!!");
                    } catch (Exception ex) {
                        //Logger.getLogger(CallHospitalAdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Error : Timer Error", "Network Error", JOptionPane.ERROR_MESSAGE);
                        cancelRequest.setEnabled(false);
                    }

                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                timerLabel.setText(df.format(duration - clockTime));
            }
        });
        timer.setInitialDelay(0);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CallHospitalAdminPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (!timer.isRunning()) {
            startTime = -1;
            timer.start();
        }

        ///new ReminderBeep(150);
        //replaceImage();
        // cancelRequest.setEnabled(false);
        //populateRequestTable();
    }

    public void initTImer() {

    }

    public void updateEmergencyCallRequest(String message, String status) {
        emergencyCallRequest.setMessage(message);
        //emergencyCallRequest.setSender(userAccount);
        emergencyCallRequest.setStatus(status);
        emergencyCallRequest.setResolveDate(new Date());
        timerLabel.setText(message);

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DoctorOrganization) {
                org = organization;
                break;
            }
        }
        if (org != null) {

            org.getWorkQueue().getWorkRequestList().add(emergencyCallRequest);
            //userAccount.getWorkQueue().getWorkRequestList().add(emergencyCallRequest);
        }

    }

    public void replaceImageSuccess() {
        BufferedImage img = null;
        try {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/ezgif-3367568429.gif")));
        } catch (Exception e) {
            System.err.println("oops");
        }

    }

    public void replaceImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/com/imgs/stop_Call.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

        jLabel1.setIcon(new javax.swing.ImageIcon(dimg));
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
        timerLabel = new javax.swing.JLabel();
        cancelRequest = new javax.swing.JButton();
        headingLabel = new javax.swing.JLabel();
        msgLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        setBorder(dropShadowBorder1);
        setPreferredSize(new java.awt.Dimension(400, 537));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Arrow-Back-3-icon.png"))); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/please_wait.gif"))); // NOI18N
        jLabel1.setName("callImgLbl"); // NOI18N

        timerLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        timerLabel.setForeground(new java.awt.Color(255, 0, 0));
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLabel.setText("jLabel2");

        cancelRequest.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cancelRequest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Stop-Sign-icon.png"))); // NOI18N
        cancelRequest.setText("Revert the Call");
        cancelRequest.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        cancelRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelRequestActionPerformed(evt);
            }
        });

        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        headingLabel.setForeground(new java.awt.Color(255, 51, 0));
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("Emergency Appointment");

        msgLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        msgLabel.setForeground(new java.awt.Color(255, 0, 0));
        msgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif"))); // NOI18N
        msgLabel.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cancelRequest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(timerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(msgLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(cancelRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(402, Short.MAX_VALUE)
                    .addComponent(msgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(95, 95, 95)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed

    private void cancelRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelRequestActionPerformed
        // TODO add your handling code here:
        msgLabel.setVisible(true);
        msgLabel.setText("Emergency Call reverted!!");
        updateEmergencyCallRequest("Emergency Call reverted", "Cancelled");
        
        String mobNum =userAccount.getPatients().getMobileNum();
        CallMessageUtility.sendMessage("+1"+mobNum, "Dear Customer,Emergency Call has been cancelled!!");
        timer.stop();
        replaceImage();
        cancelRequest.setEnabled(false);
    }//GEN-LAST:event_cancelRequestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelRequest;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JLabel timerLabel;
    // End of variables declaration//GEN-END:variables
}
