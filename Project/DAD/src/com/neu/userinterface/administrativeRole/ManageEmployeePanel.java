/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.administrativeRole;

import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.employee.Schedule;
import com.neu.business.organization.DoctorOrganization;
import com.neu.business.organization.Organization;
import com.neu.business.organization.OrganizationDirectory;
import com.neu.business.userAccount.UserAccount;
import com.neu.userinterface.util.UIUtilities;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ankur
 */
public class ManageEmployeePanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem system;
    private OrganizationDirectory organizationDirectory;
    private String weekDay;
    private boolean sundayFlag;
    private boolean mondayFlag;
    private boolean tuesdayFlag;
    private boolean wednesdayFlag;
    private boolean thursdayFlag;
    private boolean fridayFlag;
    private boolean saturdayFlag;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public ManageEmployeePanel(OrganizationDirectory organizationDirectory) {

    }

    ManageEmployeePanel(EcoSystem ecoSystem, OrganizationDirectory organizationDirectory) {
        initComponents();
        this.system = ecoSystem;
        //system.getNetworkList().get(0).getEnterpriseDirectory().getEnterpriseList()
        this.organizationDirectory = organizationDirectory;
        if (null == organizationDirectory.searchOrganization(Organization.Type.Doctor)) {
            organizationDirectory.createOrganization(Organization.Type.Doctor);
        }
        if (null == organizationDirectory.searchOrganization(Organization.Type.Patient)) {
            organizationDirectory.createOrganization(Organization.Type.Patient);
        }
        //deleteBtn.setVisible(false);
        editUserButton.setVisible(false);
        //UIUtilities.initButtonComponents(addUserButton);
        UIUtilities.initButtonComponents(addUpdateDoctorBtn);
        //addUserButton.putClientProperty("Synthetica.background", Color.RED);
        detailsPanel.setVisible(false);
        jScrollPane3.setVisible(false);
        msgLabel.setVisible(false);
        populateEmpTable();
        populateComboBox();
        organizationCombo.setVisible(false);
        msgLabel.setVisible(false);
        enableDisableComponents(false);
        //initFlags(boolean);

    }

    private boolean isSlotSelected(String weekday) {
        boolean flag = false;
        switch (weekday) {
            case "sunday":
                if (sundayShift1.isSelected()) {
                    flag = true;
                }
                if (sundayShift2.isSelected()) {
                    flag = true;
                }
                if (sundayShift3.isSelected()) {
                    flag = true;
                }
                break;
            case "monday":
                if (mondayShif1.isSelected()) {
                    flag = true;
                }
                if (mondayShift2.isSelected()) {
                    flag = true;
                }
                if (mondayShift3.isSelected()) {
                    flag = true;
                }
                break;
            case "tuesday":
                if (tuesdayShift1.isSelected()) {
                    flag = true;
                }
                if (tuesdayShift2.isSelected()) {
                    flag = true;
                }
                if (tuesdayShift3.isSelected()) {
                    flag = true;
                }
                break;
            case "wednesday":
                if (wednesdayShift1.isSelected()) {
                    flag = true;
                }
                if (wednesdayShift2.isSelected()) {
                    flag = true;
                }
                if (wednesdayShift3.isSelected()) {
                    flag = true;
                }
                break;
            case "thursday":
                if (thursdayShift1.isSelected()) {
                    flag = true;
                }
                if (thursdayShift2.isSelected()) {
                    flag = true;
                }
                if (thursdayShift3.isSelected()) {
                    flag = true;
                }
                break;
            case "friday":
                if (fridayShift1.isSelected()) {
                    flag = true;
                }
                if (fridayShift2.isSelected()) {
                    flag = true;
                }
                if (fridayShift3.isSelected()) {
                    flag = true;
                }
                break;
            case "saturday":
                if (saturdayShift1.isSelected()) {
                    flag = true;
                }
                if (saturdayShift2.isSelected()) {
                    flag = true;
                }
                if (saturdayShift3.isSelected()) {
                    flag = true;
                }
                break;

        }
        return flag;
    }

    private void enableDisableComponents(boolean flag) {

        sundayShift1.setEnabled(flag);
        sundayShift2.setEnabled(flag);
        sundayShift3.setEnabled(flag);

        mondayShif1.setEnabled(flag);
        mondayShift2.setEnabled(flag);
        mondayShift3.setEnabled(flag);

        tuesdayShift1.setEnabled(flag);
        tuesdayShift2.setEnabled(flag);
        tuesdayShift3.setEnabled(flag);

        wednesdayShift1.setEnabled(flag);
        wednesdayShift2.setEnabled(flag);
        wednesdayShift3.setEnabled(flag);

        thursdayShift1.setEnabled(flag);
        thursdayShift2.setEnabled(flag);
        thursdayShift3.setEnabled(flag);

        fridayShift1.setEnabled(flag);
        fridayShift2.setEnabled(flag);
        fridayShift3.setEnabled(flag);

        saturdayShift1.setEnabled(flag);
        saturdayShift2.setEnabled(flag);
        saturdayShift3.setEnabled(flag);

    }

    private void resetFields() {
        nameJTextField.setText("");
        mobileJTextfield.setText("");
        //emailIdTxt.setText("");
        addresstxt.setText("");
        usernameTxt.setText("");
        pswdField.setText("");
        confirmPswdField.setText("");
        enableDisableComponents(false);
    }

    private void enableDisableComponents(boolean flag, String weekday) {
        switch (weekday) {
            case "sunday":
                sundayShift1.setEnabled(flag);
                sundayShift2.setEnabled(flag);
                sundayShift3.setEnabled(flag);
                sundayShift1.setSelected(flag);
                sundayShift2.setSelected(flag);
                sundayShift3.setSelected(flag);
                
                break;
            case "monday":
                mondayShif1.setEnabled(flag);
                mondayShift2.setEnabled(flag);
                mondayShift3.setEnabled(flag);
                mondayShif1.setSelected(flag);
                mondayShift2.setSelected(flag);
                mondayShift3.setSelected(flag);
                break;
            case "tuesday":
                tuesdayShift1.setEnabled(flag);
                tuesdayShift2.setEnabled(flag);
                tuesdayShift3.setEnabled(flag);
                tuesdayShift1.setSelected(flag);
                tuesdayShift2.setSelected(flag);
                tuesdayShift3.setSelected(flag);
                break;
            case "wednesday":
                wednesdayShift1.setEnabled(flag);
                wednesdayShift2.setEnabled(flag);
                wednesdayShift3.setEnabled(flag);
                wednesdayShift1.setSelected(flag);
                wednesdayShift2.setSelected(flag);
                wednesdayShift3.setSelected(flag);
                
                break;
            case "thursday":
                thursdayShift1.setEnabled(flag);
                thursdayShift2.setEnabled(flag);
                thursdayShift3.setEnabled(flag);
                thursdayShift1.setSelected(flag);
                thursdayShift2.setSelected(flag);
                thursdayShift3.setSelected(flag);
                
                break;
            case "friday":
                fridayShift1.setEnabled(flag);
                fridayShift2.setEnabled(flag);
                fridayShift3.setEnabled(flag);
                fridayShift1.setSelected(flag);
                fridayShift2.setSelected(flag);
                fridayShift3.setSelected(flag);
                
                break;
            case "saturday":
                saturdayShift1.setEnabled(flag);
                saturdayShift2.setEnabled(flag);
                saturdayShift3.setEnabled(flag);
                saturdayShift1.setSelected(flag);
                saturdayShift2.setSelected(flag);
                saturdayShift3.setSelected(flag);
                
                break;

        }
    }

    private void populateEmpTable() {
        DefaultTableModel model = (DefaultTableModel) employeesJTable.getModel();
        DefaultTableModel model1 = (DefaultTableModel) userAccount.getModel();

        model.setRowCount(0);
        model1.setRowCount(0);
        for (Organization organization : organizationDirectory.getOrganizationList()) {

            for (Employee e : organization.getEmployeeDirectory().getEmployeeList()) {
                Object[] row = new Object[10];
                row[0] = e;
                row[1] = e.getSpeciality();
                row[2] = e.getEmailId();
                row[3] = e.getMobileNum();
                model.addRow(row);
            }

            for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                //Object row[] = new Object[2];
                Object[] row = new Object[10];
                row[0] = ua;

//                ((DefaultTableModel) userJTable.getModel()).addRow(row);
                model1.addRow(row);
            }

        }

    }

    public void popData() {

    }

    private void populateComboBox() {
        organizationCombo.removeAllItems();
        for (Organization organization : organizationDirectory.getOrganizationList()) {
            organizationCombo.addItem(organization);
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

        addUserButton = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeesJTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        userAccount = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        detailsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addUpdateDoctorBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        organizationCombo = new javax.swing.JComboBox();
        msgLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tuesday = new javax.swing.JToggleButton();
        saturday = new javax.swing.JToggleButton();
        wednesday = new javax.swing.JToggleButton();
        thursday = new javax.swing.JToggleButton();
        friday = new javax.swing.JToggleButton();
        monday = new javax.swing.JToggleButton();
        mondayShif1 = new javax.swing.JToggleButton();
        mondayShift2 = new javax.swing.JToggleButton();
        mondayShift3 = new javax.swing.JToggleButton();
        tuesdayShift1 = new javax.swing.JToggleButton();
        tuesdayShift2 = new javax.swing.JToggleButton();
        tuesdayShift3 = new javax.swing.JToggleButton();
        wednesdayShift1 = new javax.swing.JToggleButton();
        wednesdayShift2 = new javax.swing.JToggleButton();
        wednesdayShift3 = new javax.swing.JToggleButton();
        thursdayShift1 = new javax.swing.JToggleButton();
        thursdayShift2 = new javax.swing.JToggleButton();
        thursdayShift3 = new javax.swing.JToggleButton();
        fridayShift1 = new javax.swing.JToggleButton();
        fridayShift2 = new javax.swing.JToggleButton();
        fridayShift3 = new javax.swing.JToggleButton();
        saturdayShift1 = new javax.swing.JToggleButton();
        saturdayShift2 = new javax.swing.JToggleButton();
        saturdayShift3 = new javax.swing.JToggleButton();
        sunday = new javax.swing.JToggleButton();
        sundayShift1 = new javax.swing.JToggleButton();
        sundayShift2 = new javax.swing.JToggleButton();
        sundayShift3 = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        usernameTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        addresstxt = new javax.swing.JTextField();
        confirmPswdField = new javax.swing.JPasswordField();
        pswdField = new javax.swing.JPasswordField();
        specialtyCombo = new javax.swing.JComboBox();
        mobileJTextfield = new javax.swing.JFormattedTextField();
        editUserButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(418, 520));

        addUserButton.setBackground(new java.awt.Color(204, 204, 204));
        addUserButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/math-add-icon.png"))); // NOI18N
        addUserButton.setText("Add");
        addUserButton.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        addUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addUserButton.setPreferredSize(new java.awt.Dimension(75, 31));
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(204, 204, 204));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteBtn.setText("Remove");
        deleteBtn.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setPreferredSize(new java.awt.Dimension(75, 31));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        employeesJTable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        employeesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "<html><b>Employee Name</b></html>", "<html><b>Speciality</b></html>"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeesJTable.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(employeesJTable);

        jScrollPane4.setBorder(null);

        userAccount.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        userAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "<html><b>Username</b></html>"
            }
        ));
        userAccount.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(userAccount);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(354, 525));

        detailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        detailsPanel.setBorder(dropShadowBorder1);
        detailsPanel.setAutoscrolls(true);
        detailsPanel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        detailsPanel.setPreferredSize(new java.awt.Dimension(352, 520));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setText("Doctor Name");

        nameJTextField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        nameJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameJTextFieldFocusLost(evt);
            }
        });
        nameJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameJTextFieldKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Mobile Number");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setText("Speciality");

        addUpdateDoctorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/submit-button_small.png"))); // NOI18N
        addUpdateDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUpdateDoctorBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel5.setText("Visiting Hours");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel6.setText("Shift Timings");

        organizationCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationCombo.setEnabled(false);

        msgLabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        msgLabel.setForeground(new java.awt.Color(255, 51, 0));
        msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif"))); // NOI18N
        msgLabel.setText("msgLabel");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tuesday.setText("T");
        tuesday.setBorder(null);
        tuesday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuesdayActionPerformed(evt);
            }
        });
        jPanel2.add(tuesday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 40, 42, 20));

        saturday.setText("S");
        saturday.setBorder(null);
        saturday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saturdayActionPerformed(evt);
            }
        });
        jPanel2.add(saturday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 120, 42, 20));

        wednesday.setText("W");
        wednesday.setBorder(null);
        wednesday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayActionPerformed(evt);
            }
        });
        jPanel2.add(wednesday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 60, 42, 20));

        thursday.setText("R");
        thursday.setBorder(null);
        thursday.setMinimumSize(new java.awt.Dimension(43, 23));
        thursday.setPreferredSize(new java.awt.Dimension(43, 23));
        thursday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayActionPerformed(evt);
            }
        });
        jPanel2.add(thursday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 80, -1, -1));

        friday.setText("F");
        friday.setBorder(null);
        friday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fridayActionPerformed(evt);
            }
        });
        jPanel2.add(friday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 100, 42, 20));

        monday.setText("M");
        monday.setBorder(null);
        monday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayActionPerformed(evt);
            }
        });
        jPanel2.add(monday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 20, 42, 20));

        mondayShif1.setText("8-11AM");
        mondayShif1.setBorder(null);
        jPanel2.add(mondayShif1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 80, 20));

        mondayShift2.setMnemonic('1');
        mondayShift2.setText("11-2PM");
        mondayShift2.setBorder(null);
        jPanel2.add(mondayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 80, 20));

        mondayShift3.setText("2-5PM");
        mondayShift3.setBorder(null);
        jPanel2.add(mondayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 80, 20));

        tuesdayShift1.setText("8-11AM");
        tuesdayShift1.setBorder(null);
        jPanel2.add(tuesdayShift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 80, 20));

        tuesdayShift2.setMnemonic('1');
        tuesdayShift2.setText("11-2PM");
        tuesdayShift2.setBorder(null);
        jPanel2.add(tuesdayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 80, 20));

        tuesdayShift3.setText("2-5PM");
        tuesdayShift3.setBorder(null);
        jPanel2.add(tuesdayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 80, 20));

        wednesdayShift1.setText("8-11AM");
        wednesdayShift1.setBorder(null);
        jPanel2.add(wednesdayShift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 80, 20));

        wednesdayShift2.setMnemonic('1');
        wednesdayShift2.setText("11-2PM");
        wednesdayShift2.setBorder(null);
        jPanel2.add(wednesdayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 80, 20));

        wednesdayShift3.setText("2-5PM");
        wednesdayShift3.setBorder(null);
        jPanel2.add(wednesdayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 80, 20));

        thursdayShift1.setText("8-11AM");
        thursdayShift1.setBorder(null);
        jPanel2.add(thursdayShift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 80, 20));

        thursdayShift2.setMnemonic('1');
        thursdayShift2.setText("11-2PM");
        thursdayShift2.setBorder(null);
        jPanel2.add(thursdayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 80, 20));

        thursdayShift3.setText("2-5PM");
        thursdayShift3.setBorder(null);
        jPanel2.add(thursdayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 80, 20));

        fridayShift1.setText("8-11AM");
        fridayShift1.setBorder(null);
        jPanel2.add(fridayShift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 80, 20));

        fridayShift2.setMnemonic('1');
        fridayShift2.setText("11-2PM");
        fridayShift2.setBorder(null);
        jPanel2.add(fridayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 80, 20));

        fridayShift3.setText("2-5PM");
        fridayShift3.setBorder(null);
        jPanel2.add(fridayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 80, 20));

        saturdayShift1.setText("8-11AM");
        saturdayShift1.setBorder(null);
        jPanel2.add(saturdayShift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 80, 20));

        saturdayShift2.setMnemonic('1');
        saturdayShift2.setText("11-2PM");
        saturdayShift2.setBorder(null);
        jPanel2.add(saturdayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 80, 20));

        saturdayShift3.setText("2-5PM");
        saturdayShift3.setBorder(null);
        jPanel2.add(saturdayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 80, 20));

        sunday.setText("S");
        sunday.setBorder(null);
        sunday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sundayActionPerformed(evt);
            }
        });
        jPanel2.add(sunday, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 42, 20));

        sundayShift1.setText("8-11AM");
        sundayShift1.setBorder(null);
        sundayShift1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sundayShift1ActionPerformed(evt);
            }
        });
        jPanel2.add(sundayShift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 80, 20));

        sundayShift2.setMnemonic('1');
        sundayShift2.setText("11-2PM");
        sundayShift2.setBorder(null);
        jPanel2.add(sundayShift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 80, 20));

        sundayShift3.setText("2-5PM");
        sundayShift3.setBorder(null);
        sundayShift3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sundayShift3ActionPerformed(evt);
            }
        });
        jPanel2.add(sundayShift3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 80, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setText("Username");

        usernameTxt.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        usernameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameTxtFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel9.setText("Confirm Password");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Create Account");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel11.setText("Address");

        addresstxt.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        addresstxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                addresstxtFocusLost(evt);
            }
        });

        confirmPswdField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        pswdField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        pswdField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pswdFieldFocusLost(evt);
            }
        });

        specialtyCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "General Physician", "Dentist", "Gynecologist/Obstetrician", "Dermatologist/Cosmetologist", "Homeopath", "Ayurveda", "Cardiologist", "Gastroenterologist", "ENT specialist", "Pediatrician", "Orthopedist", "Opthalmalogist" }));

        try {
            mobileJTextfield.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        mobileJTextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mobileJTextfieldFocusLost(evt);
            }
        });
        mobileJTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileJTextfieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addComponent(msgLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(organizationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(usernameTxt)
                                        .addComponent(confirmPswdField)
                                        .addComponent(pswdField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                    .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(detailsPanelLayout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel11))
                                            .addGap(17, 17, 17))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(addresstxt)
                                        .addComponent(mobileJTextfield)
                                        .addComponent(specialtyCombo, 0, 196, Short.MAX_VALUE)
                                        .addComponent(nameJTextField)))
                                .addComponent(jLabel10)
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(7, 7, 7)
                                    .addComponent(jLabel6)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(addUpdateDoctorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msgLabel)
                    .addComponent(organizationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mobileJTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(specialtyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(addresstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(confirmPswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addUpdateDoctorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jScrollPane3.setViewportView(detailsPanel);

        editUserButton.setBackground(new java.awt.Color(204, 204, 204));
        editUserButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/edit_small.png"))); // NOI18N
        editUserButton.setText("Edit");
        editUserButton.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        editUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editUserButton.setPreferredSize(new java.awt.Dimension(75, 31));
        editUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        if (employeesJTable.getSelectedRow() > 0) {
            Employee e = (Employee) employeesJTable.getValueAt(employeesJTable.getSelectedRow(), 0);
            Organization o = (Organization) organizationCombo.getSelectedItem();
            if (o instanceof DoctorOrganization) {
                o.getEmployeeDirectory().removeEmployee(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a record", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void sundayShift1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sundayShift1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sundayShift1ActionPerformed

    private void sundayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sundayActionPerformed
        // TODO add your handling code here:
        if (sunday.isSelected()) {
            enableDisableComponents(true, "sunday");
        } else {
            enableDisableComponents(false, "sunday");
        }
    }//GEN-LAST:event_sundayActionPerformed

    private void addUpdateDoctorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUpdateDoctorBtnActionPerformed
        // TODO add your handling code here:
        Organization organization = (Organization) organizationCombo.getSelectedItem();

        if (UIUtilities.isEmpty(nameJTextField) || UIUtilities.isEmpty(mobileJTextfield)
                || UIUtilities.isEmpty(pswdField) || UIUtilities.isEmpty(confirmPswdField)
                || UIUtilities.isEmpty(addresstxt) || !(isSlotSelected("sunday") || isSlotSelected("monday")
                || isSlotSelected("tuesday") || isSlotSelected("wednesdy")
                || isSlotSelected("thursday") || isSlotSelected("friday")
                || isSlotSelected("saturday"))) {
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Some fields are Mandatory!!");
            return;
        }

        String name = nameJTextField.getText();

        Employee doctor = organization.getEmployeeDirectory().createEmployee(name);
        HashMap schedule = doctor.getSchedule();
        //doctor.setEmailId(emailIdTxt.getText());
        doctor.setMobileNum("+1" + mobileJTextfield.getText());
        doctor.setAddress(addresstxt.getText());
        doctor.setSpeciality(specialtyCombo.getSelectedItem().toString());
        /**
         * Creating Schedule *
         */
        if (sunday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (sundayShift1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (sundayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (sundayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Sunday", shiftSun);
        }

        if (monday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (mondayShif1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (mondayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (mondayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Monday", shiftSun);
        }

        if (tuesday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (tuesdayShift1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (tuesdayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (tuesdayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Tuesday", shiftSun);
        }
        if (wednesday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (wednesdayShift1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (wednesdayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (wednesdayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Wednesday", shiftSun);
        }
        if (thursday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (thursdayShift1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (thursdayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (thursdayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Thursday", shiftSun);
        }
        if (friday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (fridayShift1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (fridayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (fridayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Friday", shiftSun);
        }
        if (saturday.isSelected()) {
            ArrayList shiftSun = new ArrayList();
            if (saturdayShift1.isSelected()) {
                if (!shiftSun.contains("8-11am")) {
                    shiftSun.add("8-11am");
                }
            } else {
                if (shiftSun.contains("8-11am")) {
                    shiftSun.remove("8-11am");
                }
            }
            if (saturdayShift2.isSelected()) {
                if (!shiftSun.contains("11-2pm")) {
                    shiftSun.add("11-2pm");
                }
            } else {
                if (shiftSun.contains("11-2pm")) {
                    shiftSun.remove("11-2pm");
                }
            }
            if (saturdayShift3.isSelected()) {
                if (!shiftSun.contains("5-9pm")) {
                    shiftSun.add("5-9pm");
                }
            } else {
                if (shiftSun.contains("5-9pm")) {
                    shiftSun.remove("5-9pm");
                }
            }
            shiftSun.add("30");
            schedule.put("Saturday", shiftSun);
        }
        doctor.setSchedule(schedule);
        if (pswdField.getText().equalsIgnoreCase(confirmPswdField.getText())) {
            if (organization.getUserAccountDirectory().checkIfUsernameIsUnique(usernameTxt.getText())) {
                UserAccount ua = organization.getUserAccountDirectory().
                        createUserAccount(usernameTxt.getText(), pswdField.getText(),
                                doctor, organization.getSupportedRole().get(0));
                msgLabel.setVisible(true);
                msgLabel.setText("Successfully created!!!");
                msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/success.gif")));
                msgLabel.setForeground(Color.green);
                resetFields();
            } else {
                msgLabel.setVisible(true);
                msgLabel.setText("Error : Username Exists!!!");
                msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif")));
                msgLabel.setForeground(Color.red);
                usernameTxt.setText("");
            }
        }
        populateEmpTable();
        resetFields();
    }//GEN-LAST:event_addUpdateDoctorBtnActionPerformed

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        if (detailsPanel.isVisible() && jScrollPane3.isVisible()) {
            detailsPanel.setVisible(false);
            jScrollPane3.setVisible(false);
            msgLabel.setVisible(false);
        } else {
            detailsPanel.setVisible(true);
            jScrollPane3.setVisible(true);
        }
        resetFields();

        repaint();
        revalidate();
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void editUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = employeesJTable.getSelectedRow();
        if (selectedRow > 0) {
            JOptionPane.showMessageDialog(null,
                    "Please Select a Employee record.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_editUserButtonActionPerformed

    private void sundayShift3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sundayShift3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sundayShift3ActionPerformed

    private void mobileJTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileJTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileJTextfieldActionPerformed

    private void nameJTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameJTextFieldKeyTyped
        // TODO add your handling code here:
        UIUtilities.restrictAlphabetinTxtFields(evt);
    }//GEN-LAST:event_nameJTextFieldKeyTyped

    private void nameJTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameJTextFieldFocusLost
        // TODO add your handling code here:
        if (UIUtilities.isEmpty(nameJTextField)) {
            nameJTextField.setText("");
            nameJTextField.setBackground(Color.yellow);
            //evt.consume();
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Name field is empty");
        } else {
            nameJTextField.setBackground(Color.white);
            msgLabel.setVisible(false);

        }
    }//GEN-LAST:event_nameJTextFieldFocusLost

    private void mondayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mondayActionPerformed
        // TODO add your handling code here:
        if (monday.isSelected()) {
            enableDisableComponents(true, "monday");
        } else {
            enableDisableComponents(false, "monday");
        }
    }//GEN-LAST:event_mondayActionPerformed

    private void tuesdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuesdayActionPerformed
        // TODO add your handling code here:
        if (tuesday.isSelected()) {
            enableDisableComponents(true, "tuesday");
        } else {
            enableDisableComponents(false, "tuesday");
        }
    }//GEN-LAST:event_tuesdayActionPerformed

    private void wednesdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wednesdayActionPerformed
        // TODO add your handling code here:
        if (wednesday.isSelected()) {
            enableDisableComponents(true, "wednesday");
        } else {
            enableDisableComponents(false, "wednesday");
        }
    }//GEN-LAST:event_wednesdayActionPerformed

    private void thursdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thursdayActionPerformed
        // TODO add your handling code here:
        if (thursday.isSelected()) {
            enableDisableComponents(true, "thursday");
        } else {
            enableDisableComponents(false, "thursday");
        }
    }//GEN-LAST:event_thursdayActionPerformed

    private void fridayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fridayActionPerformed
        // TODO add your handling code here:
        if (friday.isSelected()) {
            enableDisableComponents(true, "friday");
        } else {
            enableDisableComponents(false, "friday");
        }
    }//GEN-LAST:event_fridayActionPerformed

    private void saturdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saturdayActionPerformed
        // TODO add your handling code here:
        if (saturday.isSelected()) {
            enableDisableComponents(true, "saturday");
        } else {
            enableDisableComponents(false, "saturday");
        }
    }//GEN-LAST:event_saturdayActionPerformed

    private void mobileJTextfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobileJTextfieldFocusLost
        // TODO add your handling code here:
        if (UIUtilities.isEmpty(mobileJTextfield)) {
            mobileJTextfield.setText("");
            mobileJTextfield.setBackground(Color.yellow);
            //evt.consume();
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Mobile Num field is empty");
        } else {
            mobileJTextfield.setBackground(Color.white);
            msgLabel.setVisible(false);

        }
    }//GEN-LAST:event_mobileJTextfieldFocusLost

    private void addresstxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addresstxtFocusLost
        // TODO add your handling code here:
        if (UIUtilities.isEmpty(addresstxt)) {
            addresstxt.setText("");
            addresstxt.setBackground(Color.yellow);
            //evt.consume();
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Name field is empty");
        } else {
            addresstxt.setBackground(Color.white);
            msgLabel.setVisible(false);

        }
    }//GEN-LAST:event_addresstxtFocusLost

    private void usernameTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTxtFocusLost
        // TODO add your handling code here:
        if (UIUtilities.isEmpty(usernameTxt)) {
            usernameTxt.setText("");
            usernameTxt.setBackground(Color.yellow);
            //evt.consume();
            msgLabel.setVisible(true);
            msgLabel.setText("Error : User Name field is empty");
        } else {
            usernameTxt.setBackground(Color.white);
            msgLabel.setVisible(false);

        }
    }//GEN-LAST:event_usernameTxtFocusLost

    private void pswdFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswdFieldFocusLost
        // TODO add your handling code here:
        if (UIUtilities.isEmpty(pswdField)) {
            pswdField.setText("");
            pswdField.setBackground(Color.yellow);
            //evt.consume();
            msgLabel.setVisible(true);
            msgLabel.setText("Error : Password field is empty");
        } else {
            pswdField.setBackground(Color.white);
            //pswdField.setVisible(false);

        }
    }//GEN-LAST:event_pswdFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUpdateDoctorBtn;
    private javax.swing.JButton addUserButton;
    private javax.swing.JTextField addresstxt;
    private javax.swing.JPasswordField confirmPswdField;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JButton editUserButton;
    private javax.swing.JTable employeesJTable;
    private javax.swing.JToggleButton friday;
    private javax.swing.JToggleButton fridayShift1;
    private javax.swing.JToggleButton fridayShift2;
    private javax.swing.JToggleButton fridayShift3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JFormattedTextField mobileJTextfield;
    private javax.swing.JToggleButton monday;
    private javax.swing.JToggleButton mondayShif1;
    private javax.swing.JToggleButton mondayShift2;
    private javax.swing.JToggleButton mondayShift3;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JComboBox organizationCombo;
    private javax.swing.JPasswordField pswdField;
    private javax.swing.JToggleButton saturday;
    private javax.swing.JToggleButton saturdayShift1;
    private javax.swing.JToggleButton saturdayShift2;
    private javax.swing.JToggleButton saturdayShift3;
    private javax.swing.JComboBox specialtyCombo;
    private javax.swing.JToggleButton sunday;
    private javax.swing.JToggleButton sundayShift1;
    private javax.swing.JToggleButton sundayShift2;
    private javax.swing.JToggleButton sundayShift3;
    private javax.swing.JToggleButton thursday;
    private javax.swing.JToggleButton thursdayShift1;
    private javax.swing.JToggleButton thursdayShift2;
    private javax.swing.JToggleButton thursdayShift3;
    private javax.swing.JToggleButton tuesday;
    private javax.swing.JToggleButton tuesdayShift1;
    private javax.swing.JToggleButton tuesdayShift2;
    private javax.swing.JToggleButton tuesdayShift3;
    private javax.swing.JTable userAccount;
    private javax.swing.JTextField usernameTxt;
    private javax.swing.JToggleButton wednesday;
    private javax.swing.JToggleButton wednesdayShift1;
    private javax.swing.JToggleButton wednesdayShift2;
    private javax.swing.JToggleButton wednesdayShift3;
    // End of variables declaration//GEN-END:variables
}
