/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.patientRole;

import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.organization.DoctorOrganization;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
import com.neu.business.workQueue.AppointmentRequest;
import com.neu.business.workQueue.WorkRequest;
import com.neu.userinterface.util.UIUtilities;
import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class MakeAnAppointmentPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;
    private Employee doctor;
    private UserAccount userAccount;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public MakeAnAppointmentPanel(JPanel userProcessContainer, EcoSystem system,
            Enterprise enterprise, Employee doctor, UserAccount ua) {
        initComponents();
        UIUtilities.initButtonComponents(previousDateBtn);
        UIUtilities.initButtonComponents(nextDateBtn);

        this.userProcessContainer = userProcessContainer;
        this.ecoSystem = system;
        this.doctor = doctor;
        this.userAccount = ua;
        this.enterprise = enterprise;
        docName.setText(doctor.getName());
        speciality.setText(doctor.getSpeciality());
        morningCapacity.setVisible(false);
        afternoonCapacity.setVisible(false);
        eveningCapacity.setVisible(false);
        try {
            initDate();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error : Date error");
        }
        populateSchedule();
        populateRequestTable();
        msgLabel.setVisible(false);
    }

    public void populateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();

        model.setRowCount(0);
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()) {
            if (request instanceof AppointmentRequest) {
                Object[] row = new Object[4];
                row[0] = (AppointmentRequest) request;
                row[1] = request.getStatus();

                model.addRow(row);
            }
        }
    }

    private void populateSchedule() {
        HashMap<String, ArrayList> doctorSchedule = doctor.getSchedule();
        int spaceInd = selectedDate.getText().indexOf(' ');
        String dayOftheWeek = selectedDate.getText().substring(0, spaceInd);
        System.out.println("" + selectedDate.getText().substring(0, spaceInd));
        boolean morningFlag = false, afternoonFlag = false, eveningFlag = false;
        String capacity = "";
        for (Map.Entry<String, ArrayList> entry : doctorSchedule.entrySet()) {
            String key = entry.getKey();
            ArrayList shiftTimings = entry.getValue();
            if (dayOftheWeek.contains(key.substring(0, spaceInd))) {
                if (shiftTimings.contains("8-11am")) {
                    morningFlag = true;

                    capacity = (String) shiftTimings.get(shiftTimings.size() - 1);

                }
                if (shiftTimings.contains("11-2pm")) {
                    afternoonFlag = true;
                    capacity = (String) shiftTimings.get(shiftTimings.size() - 1);

                }
                if (shiftTimings.contains("5-9pm")) {
                    eveningFlag = true;
                    capacity = (String) shiftTimings.get(shiftTimings.size() - 1);

                }
                enableDisableComponents(morningFlag, afternoonFlag, eveningFlag, capacity);
            } else {
                enableDisableComponents(morningFlag, afternoonFlag, eveningFlag, capacity);
            }
        }

    }

    public void enableDisableComponents(boolean morningFlag, boolean afternoonFlag,
            boolean eveningFlag, String capacity) {
        morningSchedule1.setEnabled(morningFlag);
        morningSchedule2.setEnabled(morningFlag);
        morningSchedule3.setEnabled(morningFlag);
        morningCapacity.setEnabled(morningFlag);
        morningCapacity.setText((capacity));

        afternoonSchedule1.setEnabled(afternoonFlag);
        afternoonSchedule2.setEnabled(afternoonFlag);
        afternoonSchedule3.setEnabled(afternoonFlag);
        afternoonCapacity.setEnabled(afternoonFlag);
        afternoonCapacity.setText(capacity);

        eveningSchedule1.setEnabled(eveningFlag);
        eveningSchedule2.setEnabled(eveningFlag);
        eveningCapacity.setEnabled(eveningFlag);
        eveningCapacity.setText(capacity);
    }

    private void initDate() throws ParseException {
        Calendar now = Calendar.getInstance();
        String[] strDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
            "Friday", "Saturday"};

        // Day_OF_WEEK starts from 1 while array index starts from 0
        System.out.println("Current day is : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
        System.out.println(df.format(date));
        //String dayOftheWeek = datePicker
        if (datePicker.getDate() != null) {
            selectedDate.setText(df.format(datePicker.getDate()));
        } else {
            selectedDate.setText(df.format(date));
        }
        checkForTodaysDate();

    }

    public void checkForTodaysDate() {
        Date date1 = null;
        Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
       
        
        String strDate1 = sdf.format(new Date());
       
        try {
             date2 = df.parse(selectedDate.getText());
              String strDate2 = sdf.format(date2);
            date1 = sdf.parse(strDate1);
            date2 = sdf.parse(strDate2);

            if (date1.compareTo(date2) == 0) {
                enableDisableComponents(false, false, false, "");
            }
        } catch (ParseException ex) {
            Logger.getLogger(MakeAnAppointmentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        selectedDate = new javax.swing.JLabel();
        schedulePanel = new javax.swing.JPanel();
        schedule = new javax.swing.JLabel();
        schedule1 = new javax.swing.JLabel();
        schedule2 = new javax.swing.JLabel();
        schedule3 = new javax.swing.JLabel();
        morningCapacity = new javax.swing.JTextField();
        afternoonCapacity = new javax.swing.JTextField();
        eveningCapacity = new javax.swing.JTextField();
        morningSchedule1 = new javax.swing.JRadioButton();
        morningSchedule2 = new javax.swing.JRadioButton();
        morningSchedule3 = new javax.swing.JRadioButton();
        afternoonSchedule1 = new javax.swing.JRadioButton();
        afternoonSchedule2 = new javax.swing.JRadioButton();
        afternoonSchedule3 = new javax.swing.JRadioButton();
        eveningSchedule1 = new javax.swing.JRadioButton();
        eveningSchedule2 = new javax.swing.JRadioButton();
        msgLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        speciality = new javax.swing.JLabel();
        reviews = new javax.swing.JLabel();
        previousDateBtn = new javax.swing.JButton();
        nextDateBtn = new javax.swing.JButton();
        docName1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        datePicker = new com.toedter.calendar.JDateChooser();
        docName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        setBorder(dropShadowBorder1);
        setPreferredSize(new java.awt.Dimension(418, 537));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectedDate.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        selectedDate.setForeground(new java.awt.Color(51, 51, 255));
        selectedDate.setText("jLabel1");
        selectedDate.setEnabled(false);
        add(selectedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 140, 120, -1));

        schedulePanel.setBackground(new java.awt.Color(255, 255, 255));
        schedulePanel.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        schedulePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        schedule.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        schedule.setText("Choose Slot");
        schedulePanel.add(schedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 5, -1, -1));

        schedule1.setText("Morning");
        schedulePanel.add(schedule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        schedule2.setText("Afternoon");
        schedulePanel.add(schedule2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        schedule3.setText("Evening");
        schedulePanel.add(schedule3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        morningCapacity.setEnabled(false);
        schedulePanel.add(morningCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 40, -1));

        afternoonCapacity.setEnabled(false);
        schedulePanel.add(afternoonCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 40, -1));

        eveningCapacity.setEnabled(false);
        schedulePanel.add(eveningCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 40, -1));

        buttonGroup1.add(morningSchedule1);
        morningSchedule1.setText("8-9am");
        morningSchedule1.setEnabled(false);
        morningSchedule1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                morningSchedule1ActionPerformed(evt);
            }
        });
        schedulePanel.add(morningSchedule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        buttonGroup1.add(morningSchedule2);
        morningSchedule2.setText("9-10am");
        morningSchedule2.setEnabled(false);
        morningSchedule2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                morningSchedule2ActionPerformed(evt);
            }
        });
        schedulePanel.add(morningSchedule2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        buttonGroup1.add(morningSchedule3);
        morningSchedule3.setText("10-11am");
        morningSchedule3.setEnabled(false);
        morningSchedule3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                morningSchedule3ActionPerformed(evt);
            }
        });
        schedulePanel.add(morningSchedule3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        buttonGroup1.add(afternoonSchedule1);
        afternoonSchedule1.setText("11-12pm");
        afternoonSchedule1.setEnabled(false);
        afternoonSchedule1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afternoonSchedule1ActionPerformed(evt);
            }
        });
        schedulePanel.add(afternoonSchedule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        buttonGroup1.add(afternoonSchedule2);
        afternoonSchedule2.setText("12-1pm");
        afternoonSchedule2.setEnabled(false);
        afternoonSchedule2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afternoonSchedule2ActionPerformed(evt);
            }
        });
        schedulePanel.add(afternoonSchedule2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        buttonGroup1.add(afternoonSchedule3);
        afternoonSchedule3.setText("1-2pm");
        afternoonSchedule3.setEnabled(false);
        afternoonSchedule3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afternoonSchedule3ActionPerformed(evt);
            }
        });
        schedulePanel.add(afternoonSchedule3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 67, -1));

        buttonGroup1.add(eveningSchedule1);
        eveningSchedule1.setText("5-7pm");
        eveningSchedule1.setEnabled(false);
        eveningSchedule1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eveningSchedule1ActionPerformed(evt);
            }
        });
        schedulePanel.add(eveningSchedule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        buttonGroup1.add(eveningSchedule2);
        eveningSchedule2.setText("7-8pm");
        eveningSchedule2.setEnabled(false);
        eveningSchedule2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eveningSchedule2ActionPerformed(evt);
            }
        });
        schedulePanel.add(eveningSchedule2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        msgLabel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        msgLabel.setForeground(new java.awt.Color(255, 0, 51));
        msgLabel.setText("jLabel1");
        schedulePanel.add(msgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 260, -1));

        add(schedulePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 300, 170));

        jPanel2.setBackground(new java.awt.Color(234, 253, 240));
        jPanel2.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());

        speciality.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        speciality.setText("jLabel2");
        jPanel2.add(speciality);

        reviews.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        reviews.setForeground(new java.awt.Color(255, 51, 51));
        reviews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/success.gif"))); // NOI18N
        reviews.setText("Note : Appointments can be booked for future dates only");
        jPanel2.add(reviews);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 350, 50));

        previousDateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Fast-backward-icon.png"))); // NOI18N
        previousDateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousDateBtnActionPerformed(evt);
            }
        });
        add(previousDateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 60, 40));

        nextDateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Skip-forward-icon.png"))); // NOI18N
        nextDateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextDateBtnActionPerformed(evt);
            }
        });
        add(nextDateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 50, 40));

        docName1.setText("Choose Date");
        add(docName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment", "Status"
            }
        ));
        jScrollPane1.setViewportView(requestTable);
        if (requestTable.getColumnModel().getColumnCount() > 0) {
            requestTable.getColumnModel().getColumn(0).setPreferredWidth(180);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 300, 170));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Button-Ok-icon.png"))); // NOI18N
        jButton3.setText("Request Appointment");
        jButton3.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 500, 418, 40));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Arrow-Back-3-icon.png"))); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 60, 40));

        datePicker.setDateFormatString("EEE MMM dd, yyyy");
        datePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePickerPropertyChange(evt);
            }
        });
        add(datePicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 210, -1));

        docName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        docName.setText("jLabel1");
        add(docName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
    private void changeDate(int prevNext) {
        Date dt = datePicker.getDate();
        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
        Calendar c = Calendar.getInstance();
        if (datePicker.getDate() != null) {
            c.setTime(dt);
        }
        c.add(Calendar.DATE, prevNext);  // number of days to add      
        datePicker.setDate(c.getTime());
        //System.out.println("Tomorrows date is " + tomorrow);
        selectedDate.setText(df.format(datePicker.getDate()));
    }
    private void nextDateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextDateBtnActionPerformed
        // TODO add your handling code here:
        msgLabel.setVisible(true);
        changeDate(1);
        populateSchedule();
        checkForTodaysDate();
    }//GEN-LAST:event_nextDateBtnActionPerformed

    private void previousDateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousDateBtnActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd, yyyy");
        Date today = new Date();
        Date selected = datePicker.getDate();
        
//        try {
//            today = sdf.parse(new Date().toString());
//            selected = sdf.parse(selectedDate.getText());
//        } catch (ParseException ex) {
//            Logger.getLogger(MakeAnAppointmentPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (selected != null) {
            if (selected.compareTo(today) >= 0) {
                changeDate(-1);
                populateSchedule();
            }
        } else {
            try {
                if (sdf.parse(selectedDate.getText()).compareTo(today) >= 0) {
                    changeDate(-1);
                    populateSchedule();
                }
            } catch (ParseException ex) {
                Logger.getLogger(MakeAnAppointmentPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        checkForTodaysDate();

    }//GEN-LAST:event_previousDateBtnActionPerformed
    public void disableRadioButtons() {
        Enumeration<AbstractButton> allRadioButton = buttonGroup1.getElements();
        while (allRadioButton.hasMoreElements()) {
            JRadioButton temp = (JRadioButton) allRadioButton.nextElement();
            if (temp.isEnabled()) {
                temp.setEnabled(false);
            }
        }
        //return selectedSlot;
    }

    public String selectedSlot() {
        Enumeration<AbstractButton> allRadioButton = buttonGroup1.getElements();
        String selectedSlot = "";
        while (allRadioButton.hasMoreElements()) {
            JRadioButton temp = (JRadioButton) allRadioButton.nextElement();
            if (temp.isSelected()) {
                selectedSlot = temp.getText();
            }
        }
        return selectedSlot;
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (!selectedSlot().equalsIgnoreCase("")) {
            // if()
            for (WorkRequest appointmentRequest : userAccount.getWorkQueue().getWorkRequestList()) {
                if (appointmentRequest instanceof AppointmentRequest) {
                    if (((AppointmentRequest) appointmentRequest).getAppointmentSlot()
                            .equalsIgnoreCase(selectedDate.getText() + " - " + selectedSlot())) {
                        msgLabel.setVisible(true);
                        msgLabel.setText("Duplicate : Request already placed for this slot!");
                        msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif")));
                        return;
                    } else {
                        msgLabel.setVisible(false);
                    }
                }
            }

            AppointmentRequest appointmentRequest = new AppointmentRequest();
            appointmentRequest.setRequestId("RQ00" + userAccount.getWorkQueue().getWorkRequestList().size());
            appointmentRequest.setMessage(selectedDate.getText() + " - " + selectedSlot());
            appointmentRequest.setSender(userAccount);
            appointmentRequest.setStatus("Pending");
            appointmentRequest.setAppointmentTimeSlot(selectedDate.getText() + " - " + selectedSlot());

            Organization org = null;
            for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (organization instanceof DoctorOrganization) {
                    org = organization;
                    break;
                }
            }
            if (org != null) {
                //org.getWorkQueue().getWorkRequestList().add(appointmentRequest);
                //org.getUserAccountDirectory().searchUserAccount(doctor).getWorkQueue().getWorkRequestList().add(appointmentRequest);
                userAccount.getWorkQueue().getWorkRequestList().add(appointmentRequest);
            }
            populateRequestTable();

            AppointmentConfirmationPanel panel = new AppointmentConfirmationPanel(userProcessContainer, ecoSystem, enterprise, doctor, userAccount, appointmentRequest,this);
            userProcessContainer.add("AppointmentConfirmationPanel", panel);
            CardLayout cardLayout = (CardLayout) userProcessContainer.getLayout();
            cardLayout.next(userProcessContainer);
        } else {
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Please Select a slot!");
            msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif")));
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed

    private void morningSchedule1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_morningSchedule1ActionPerformed
        // TODO add your handling code here:
        if (morningSchedule1.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_morningSchedule1ActionPerformed

    private void morningSchedule2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_morningSchedule2ActionPerformed
        // TODO add your handling code here:
        if (morningSchedule2.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_morningSchedule2ActionPerformed

    private void morningSchedule3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_morningSchedule3ActionPerformed
        // TODO add your handling code here:
        if (morningSchedule3.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_morningSchedule3ActionPerformed

    private void afternoonSchedule1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afternoonSchedule1ActionPerformed
        // TODO add your handling code here:
        if (afternoonSchedule1.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_afternoonSchedule1ActionPerformed

    private void afternoonSchedule2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afternoonSchedule2ActionPerformed
        // TODO add your handling code here:
        if (afternoonSchedule2.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_afternoonSchedule2ActionPerformed

    private void afternoonSchedule3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afternoonSchedule3ActionPerformed
        // TODO add your handling code here:
        if (afternoonSchedule3.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_afternoonSchedule3ActionPerformed

    private void eveningSchedule1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eveningSchedule1ActionPerformed
        // TODO add your handling code here:
        if (eveningSchedule1.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_eveningSchedule1ActionPerformed

    private void eveningSchedule2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eveningSchedule2ActionPerformed
        // TODO add your handling code here:
        if (eveningSchedule2.isSelected()) {
            msgLabel.setVisible(false);
        }
    }//GEN-LAST:event_eveningSchedule2ActionPerformed

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        // TODO add your handling code here:
        //System.out.println("" + datePicker.getDate());
        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
        Date today = new Date();
        if (datePicker.getDate() != null && datePicker.getDate().compareTo(today) >= 0) {
            msgLabel.setVisible(false);
            selectedDate.setText(df.format(datePicker.getDate()));
            populateSchedule();
        }
        if (datePicker.getDate() != null && datePicker.getDate().compareTo(today) < 0) {
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Previous date cannot be selected!");
            msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif")));
            //datePicker.setDate(today);
            disableRadioButtons();
        }
        checkForTodaysDate();
    }//GEN-LAST:event_datePickerPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField afternoonCapacity;
    private javax.swing.JRadioButton afternoonSchedule1;
    private javax.swing.JRadioButton afternoonSchedule2;
    private javax.swing.JRadioButton afternoonSchedule3;
    private javax.swing.JButton backBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JLabel docName;
    private javax.swing.JLabel docName1;
    private javax.swing.JTextField eveningCapacity;
    private javax.swing.JRadioButton eveningSchedule1;
    private javax.swing.JRadioButton eveningSchedule2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField morningCapacity;
    private javax.swing.JRadioButton morningSchedule1;
    private javax.swing.JRadioButton morningSchedule2;
    private javax.swing.JRadioButton morningSchedule3;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JButton nextDateBtn;
    private javax.swing.JButton previousDateBtn;
    private javax.swing.JTable requestTable;
    private javax.swing.JLabel reviews;
    private javax.swing.JLabel schedule;
    private javax.swing.JLabel schedule1;
    private javax.swing.JLabel schedule2;
    private javax.swing.JLabel schedule3;
    private javax.swing.JPanel schedulePanel;
    private javax.swing.JLabel selectedDate;
    private javax.swing.JLabel speciality;
    // End of variables declaration//GEN-END:variables
}
