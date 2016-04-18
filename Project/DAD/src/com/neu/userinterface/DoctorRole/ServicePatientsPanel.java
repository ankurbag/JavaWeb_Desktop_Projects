/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.DoctorRole;

import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.organization.DoctorOrganization;
import com.neu.business.organization.Organization;
import com.neu.business.patients.MedicalHistory;
import com.neu.business.patients.MedicalReportEntity;
import com.neu.business.userAccount.UserAccount;
import com.neu.business.workQueue.AppointmentRequest;
import com.neu.business.workQueue.EmergencyCallRequest;
import com.neu.business.workQueue.WorkRequest;
import com.neu.userinterface.patientRole.CallMessageUtility;
import com.neu.userinterface.patientRole.MakeAnAppointmentPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class ServicePatientsPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;
    private UserAccount userAccount;
    private DoctorOrganization organization;
    private Employee doctor;
    private long startTime = -1;
    private long duration = 10000;
    private HashMap<String, ArrayList> genPhysicianMap;
    private HashMap<String, ArrayList> dentistMap;
    private HashMap<String, ArrayList> gyneMap;
    private HashMap<String, ArrayList> dermaMap;
    private HashMap<String, ArrayList> homeoMap;
    private HashMap<String, ArrayList> orthoMap;
    private HashMap<String, ArrayList> cardioMap;
    private HashMap<String, ArrayList> gastroMap;
    private HashMap<String, ArrayList> ayurvedMap;

    private ArrayList<String> generalPhysicianList;
    private ArrayList<String> dentistList;
    private ArrayList<String> gynecologistList;
    private ArrayList<String> dermaList;
    private ArrayList<String> homeoList;
    private ArrayList<String> ayurvedaList;
    private ArrayList<String> cardioList;
    private ArrayList<String> gastroList;
    private ArrayList<String> orthList;
    private Timer timer;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public ServicePatientsPanel(JPanel userProcessContainer, EcoSystem system, UserAccount userAccount, Organization organization) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.ecoSystem = system;
        this.userAccount = userAccount;
        this.organization = (DoctorOrganization) organization;
        this.doctor = userAccount.getEmployee();
        initSpecialityServicesRecommendations();
        init();
        initServices(doctor.getSpeciality());
        populateEmergencyRequestTable();
        populateRequestTable();
    }

    public void initSpecialityServicesRecommendations() {
        genPhysicianMap = new HashMap<String, ArrayList>();
        genPhysicianMap.put("Cough & Cold",
                new ArrayList<String>(Arrays.asList("Lots of rest is the key treating a cold. "
                                + "You may find you need 12 hours of sleep each night, so don't set that alarm.")));
        genPhysicianMap.put("Fever",
                new ArrayList<String>(Arrays.asList("For temperatures below 102 F (38.9 C), "
                                + "don't use fever-lowering drugs ")));
        genPhysicianMap.put("Stomach Ache", new ArrayList<String>(Arrays.asList("Abdominal pain occurs due to multiple causes. Pain due to obstruction in "
                + "the small intestine appears around or above the umbilicus or belly button\n")));
        generalPhysicianList
                = new ArrayList<String>(Arrays.asList("Cough & Cold", "Fever", "Stomach Ache"));

        dentistMap = new HashMap<String, ArrayList>();
        dentistMap.put("Root Canal",
                new ArrayList<String>(Arrays.asList("Your lips and gums may remain numb for a few hours until the anesthetic wears off.Please take  ibuprofen, acetaminophen", "5000")));
        dentistMap.put("Bleaching",
                new ArrayList<String>(Arrays.asList(" I find the AcquaBrite whitening system to be the most effective, even for stubbornly stained teeth (like tetracycline-stained grey/brown teeth),"
                                + " with little to no sensitivity", "100")));
        dentistMap.put("Fillings",
                new ArrayList<String>(Arrays.asList("CVS Temporary Lost Filling & Loose Cap Repair, Dentist Strength", "100")));

        dentistList
                = new ArrayList<String>(Arrays.asList("Root Canal", "Bleaching", "Fillings"));

        orthoMap = new HashMap<String, ArrayList>();
        orthoMap.put("Joint Pain Management",
                new ArrayList<String>(Arrays.asList("PHYSICAL THERAPY at MGH is recommended.", "1000")));
        orthoMap.put("Arthroscopy",
                new ArrayList<String>(Arrays.asList("ACUPUNCTURE at MGH is recommended.", "5000")));

        orthoMap.put("Fracture treatment",
                new ArrayList<String>(Arrays.asList("PAIN MEDICATION\n"
                                + "(RX AND OTC) at MGH is recommended.", "5000")));

        orthList
                = new ArrayList<String>(Arrays.asList("Joint Pain Management", "Arthroscopy", "Fracture treatment"));

        gyneMap = new HashMap<String, ArrayList>();
        gyneMap.put("High-Risk Pregnanc",
                new ArrayList<String>(Arrays.asList("Schedule an Appointment with Tufts Medical Center Expert Physicians", "700")));

        gyneMap.put("Gynaec Problems",
                new ArrayList<String>(Arrays.asList("Smears,Colposcopy are recommended", "850")));

        gyneMap.put("Infertility treatment",
                new ArrayList<String>(Arrays.asList("Cosmetic gynaecology or insemination recommended", "1100")));

        gynecologistList
                = new ArrayList<String>(Arrays.asList("High-Risk Pregnancy", "Gynaec Problems", "Infertility Treatment"));

        dermaMap = new HashMap<String, ArrayList>();

        dermaMap.put("Acne Treatment",
                new ArrayList<String>(Arrays.asList("A product containing benzoyl "
                                + "peroxide or salicylic acid often clears the skin.", "5000")));

        dermaMap.put("Skin Biopsy",
                new ArrayList<String>(Arrays.asList("Excision recommended ", "500")));

        homeoMap = new HashMap<String, ArrayList>();
        homeoMap.put("Homeopathic Treatment",
                new ArrayList<String>(Arrays.asList("Arnica  recommended ", "1000")));
        homeoMap.put("Anxiety",
                new ArrayList<String>(Arrays.asList("Chamomilla   recommended ", "850")));

        homeoList
                = new ArrayList<String>(Arrays.asList("Homeopathic Treatment", "Anxiety", "Common Skin Treatment"));

        gastroMap = new HashMap<String, ArrayList>();
        gastroMap.put("Gastroscopy",
                new ArrayList<String>(Arrays.asList("Benzodiazepines   recommended ", "1000")));

        gastroMap.put("Acidity",
                new ArrayList<String>(Arrays.asList("Opioids   recommended ", "4000")));

        gastroList
                = new ArrayList<String>(Arrays.asList("Endoscopy", "Gastroscopy", "Acidity"));

        ayurvedMap = new HashMap<String, ArrayList>();
        ayurvedMap.put("Panchkarma",
                new ArrayList<String>(Arrays.asList("Panchakarma is a Sanskrit word that "
                                + "means “five actions” or “five treatments”. This is a process used to clean the body"
                                + " of toxic materials left by disease and poor nutrition.  ", "1000")));

        ayurvedMap.put("Siddha",
                new ArrayList<String>(Arrays.asList("Naadi (pulse) , Na (tongue): black . SO rest recommended", "500")));

        ayurvedaList
                = new ArrayList<String>(Arrays.asList("Panchkarma", "Siddha", "Vasthi"));

        cardioMap = new HashMap<String, ArrayList>();

        cardioMap.put("Chest Pain",
                new ArrayList<String>(Arrays.asList("Artery relaxers , Aspirin recommended", "1600")));

        cardioMap.put("Angiogram",
                new ArrayList<String>(Arrays.asList("Acute coronary syndrome detected", "4500")));

        cardioList
                = new ArrayList<String>(Arrays.asList("Chest Pain", "Angiogram", "Cardiac Procedure"));

    }

    public int generateRandomValues(float max, float min) {
        return (int) (min + Math.random() * (max - min));
    }

    public void initServices(String speciality) {
        servicesCombo.removeAllItems();
        switch (speciality) {
            case "General Physician":
                for (Map.Entry<String, ArrayList> entry : genPhysicianMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Heart Rate");
                    param2.setText("Respiratory Rate");
                    param3.setText("Blood Pressure");
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText("200");
                }

                break;
            case "Dentist":
                for (Map.Entry<String, ArrayList> entry : dentistMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Dental Decay");
                    param2.setText("Bleeding Gums");
                    param3.setVisible(false);
                    param2Txt.setVisible(true);
                    param3Txt.setVisible(false);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
            case "Gynecologist/Obstetrician":
                for (Map.Entry<String, ArrayList> entry : gyneMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Gynec problem detected");
                    param2.setVisible(false);
                    param3.setVisible(false);
                    param2Txt.setVisible(false);
                    param3Txt.setVisible(false);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
            case "Dermatologist/Cosmetologist":
                for (Map.Entry<String, ArrayList> entry : dermaMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Skin problem detected");
                    param2.setVisible(true);
                    param2.setText("Hydration of Skin(%)");
                    param3.setVisible(false);
                    param2Txt.setVisible(true);
                    param3Txt.setVisible(false);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
            case "Homeopath":
                for (Map.Entry<String, ArrayList> entry : homeoMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Heart Rate");
                    param2.setText("Respiratory Rate");
                    param3.setText("Blood Pressure");
                    param2.setVisible(true);
                    param3.setVisible(true);
                    param2Txt.setVisible(true);
                    param3Txt.setVisible(true);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
            case "Ayurveda":
                for (Map.Entry<String, ArrayList> entry : ayurvedMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Heart Rate");
                    param2.setText("Respiratory Rate");
                    param3.setText("Blood Pressure");
                    param2.setVisible(true);
                    param3.setVisible(true);
                    param2Txt.setVisible(true);
                    param3Txt.setVisible(true);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
            case "Cardiologist":
                for (Map.Entry<String, ArrayList> entry : cardioMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Heart Rate");
                    param2.setText("Respiratory Rate");
                    param3.setText("Blood Pressure");
                    param2.setVisible(true);
                    param3.setVisible(true);
                    param2Txt.setVisible(true);
                    param3Txt.setVisible(true);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
            case "Gastroenterologist":
                for (Map.Entry<String, ArrayList> entry : gastroMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Heart Rate");
                    param2.setText("Respiratory Rate");
                    param3.setText("Blood Pressure");
                    param2.setVisible(true);
                    param3.setVisible(true);
                    param2Txt.setVisible(true);
                    param3Txt.setVisible(false);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;

            case "Orthopedist":
                for (Map.Entry<String, ArrayList> entry : orthoMap.entrySet()) {
                    String serviceName = entry.getKey();
                    ArrayList treatment = entry.getValue();
                    servicesCombo.addItem(serviceName);
                    param1.setText("Calcium in Bones");
                    param2.setVisible(false);
                    param3.setVisible(false);
                    param2Txt.setVisible(false);
                    param3Txt.setVisible(false);
                    commentsTxt.setText(String.valueOf(treatment.get(0)));
                    costTxt.setText(String.valueOf(treatment.get(1)));
                }
                break;
        }
    }

    public void init() {
        recordPanel.setVisible(false);
        medPanel.setVisible(false);
        historyPanel.setVisible(false);
        emergencyPanel.setVisible(true);
        emergencyBtn.setSelected(true);
        msgLabel.setVisible(false);
        msgLabel1.setVisible(false);
    }

    public void populateDetails() {
        if (requestTable.getSelectedRow() > 0) {
            UserAccount patientAccount = (UserAccount) requestTable.getValueAt(requestTable.getSelectedRow(), 0);
            patientAgeTxt.setText(patientAccount.getPatients().getName());
//            patientId.setText(String.valueOf(patientAccount.getPatients().getId()));
//            patientMobileTxt.setText(patientAccount.getPatients().getMobileNum());
        } else {
            JOptionPane.showMessageDialog(null, "Select a row!!");
        }
    }

    public void populateEmergencyRequestTable() {
        DefaultTableModel model = (DefaultTableModel) emergencyRequestTable.getModel();

        model.setRowCount(0);
        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof EmergencyCallRequest) {
                
                Object[] row = new Object[4];
                row[0] = request;
                row[1] = request.getMessage();
                row[2] = request.getStatus();
                model.addRow(row);
            }
        }
    }

//    public void checkForTodaysDate() {
//        Date date1 = null;
//        Date date2 = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
//
//        String strDate1 = sdf.format(new Date());
//
//        try {
//            date2 = df.parse(selectedDate.getText());
//            String strDate2 = sdf.format(date2);
//            date1 = sdf.parse(strDate1);
//            date2 = sdf.parse(strDate2);
//
//            if (date1.compareTo(date2) == 0) {
//                //enableDisableComponents(false, false, false, "");
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(MakeAnAppointmentPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public void populateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
        model.setRowCount(0);
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()) {

            if (request instanceof AppointmentRequest) {
                AppointmentRequest request1 = (AppointmentRequest) request;
                String time = request1.getAppointmentSlot();
                time = time.substring(0, time.indexOf('-')).trim();
                Date nd = null;
                try {
                    nd = df.parse(time);
                } catch (ParseException ex) {
                    Logger.getLogger(DoctorAdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
                Date dt = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String dt1 = sdf.format(new Date());
                    String nd1 = sdf.format(nd);
                    dt = sdf.parse(dt1);
                    nd = sdf.parse(nd1);
                } catch (ParseException ex) {
                    Logger.getLogger(ServicePatientsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (dt.after(nd) && request.getStatus().equalsIgnoreCase("Confirmed")) {
                    Object[] row = new Object[4];
                    row[0] = request;
                    row[1] = request.getSender().getUsername();
                    row[2] = request.getStatus();
                    model.addRow(row);
                }

            }
            if (request instanceof EmergencyCallRequest) {
                if (request.getStatus().equalsIgnoreCase("Confirmed")) {
                    Object[] row = new Object[4];
                    row[0] = request;
                    row[1] = "Emergency";
                    row[2] = request.getStatus();
                    model.addRow(row);
                }
            }

//            if (request.getStatus().equalsIgnoreCase("Confirmed")) {
//                Object[] row = new Object[4];
//                row[0] = request;
//                row[1] = request.getMessage();
//                row[2] = request.getStatus();
//                model.addRow(row);
//            }
        }
        requestTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                String status = (String) table.getModel().getValueAt(row, 1);
                if ("emergency".equalsIgnoreCase(status)) {
                    setBackground(new Color(253, 78, 34));
                    setForeground(Color.WHITE);

                } else {
                    setBackground(new Color(61, 226, 119));
                    setForeground(Color.WHITE);
                }
//                if(isSelected){
//                    setBackground(new Color(11,107,253));
//                    setForeground(Color.WHITE);
//                }
                return this;
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recordPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        patientAgeTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        param3 = new javax.swing.JLabel();
        param2 = new javax.swing.JLabel();
        param1 = new javax.swing.JLabel();
        param1Txt = new javax.swing.JTextField();
        param2Txt = new javax.swing.JTextField();
        param3Txt = new javax.swing.JTextField();
        generateReportBtn = new javax.swing.JButton();
        servicesCombo = new javax.swing.JComboBox();
        startrecordbtn = new javax.swing.JButton();
        patientNameTxt = new javax.swing.JTextField();
        msgLabel = new javax.swing.JLabel();
        medPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        recordBtn = new javax.swing.JToggleButton();
        medicineBtn = new javax.swing.JToggleButton();
        historyBtn = new javax.swing.JToggleButton();
        historyPanel = new javax.swing.JPanel();
        sendResult = new javax.swing.JButton();
        costTxt = new javax.swing.JTextField();
        param4 = new javax.swing.JLabel();
        param5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commentsTxt = new javax.swing.JTextArea();
        msgLabel1 = new javax.swing.JLabel();
        emergencyBtn = new javax.swing.JToggleButton();
        emergencyPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        emergencyRequestTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        setBorder(dropShadowBorder1);
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(418, 537));

        recordPanel.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(204, 204, 204));
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        recordPanel.setBorder(dropShadowBorder2);
        recordPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        recordPanel.setPreferredSize(new java.awt.Dimension(290, 220));
        recordPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel1.setText("Select Service ");
        recordPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 10));

        patientAgeTxt.setEditable(false);
        patientAgeTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        recordPanel.add(patientAgeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 70, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setText("Age");
        recordPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 30, -1));

        param3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        param3.setText("Blood Pressure");
        recordPanel.add(param3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 10));

        param2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        param2.setText("Respiratory Rate");
        recordPanel.add(param2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 10));

        param1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        param1.setText("Heart Rate");
        recordPanel.add(param1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 10));

        param1Txt.setEditable(false);
        param1Txt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        recordPanel.add(param1Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 70, -1));

        param2Txt.setEditable(false);
        param2Txt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        recordPanel.add(param2Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 70, 20));

        param3Txt.setEditable(false);
        param3Txt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        param3Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                param3TxtActionPerformed(evt);
            }
        });
        recordPanel.add(param3Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 70, -1));

        generateReportBtn.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        generateReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Europa-Report-icon.png"))); // NOI18N
        generateReportBtn.setText("Generate Report");
        generateReportBtn.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        generateReportBtn.setEnabled(false);
        generateReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportBtnActionPerformed(evt);
            }
        });
        recordPanel.add(generateReportBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 120, 30));

        servicesCombo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        servicesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        servicesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicesComboActionPerformed(evt);
            }
        });
        recordPanel.add(servicesCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 170, -1));

        startrecordbtn.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        startrecordbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/Play-icon.png"))); // NOI18N
        startrecordbtn.setText("record");
        startrecordbtn.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        startrecordbtn.setEnabled(false);
        startrecordbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startrecordbtnActionPerformed(evt);
            }
        });
        recordPanel.add(startrecordbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 120, 30));

        patientNameTxt.setEditable(false);
        patientNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        patientNameTxt.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                patientNameTxtPropertyChange(evt);
            }
        });
        recordPanel.add(patientNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        msgLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        msgLabel.setForeground(new java.awt.Color(255, 0, 51));
        msgLabel.setText("jLabel1");
        recordPanel.add(msgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 260, 20));

        medPanel.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShadowColor(new java.awt.Color(204, 204, 204));
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        medPanel.setBorder(dropShadowBorder3);
        medPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        medPanel.setPreferredSize(new java.awt.Dimension(290, 220));
        medPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requestTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Slot", "Patient Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        requestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(requestTable);

        medPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, 80));

        recordBtn.setText("Record Vital Sign");
        recordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordBtnActionPerformed(evt);
            }
        });

        medicineBtn.setText("Appointment Table");
        medicineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineBtnActionPerformed(evt);
            }
        });

        historyBtn.setText("Medical Report");
        historyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyBtnActionPerformed(evt);
            }
        });

        historyPanel.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setShadowColor(new java.awt.Color(204, 204, 204));
        dropShadowBorder4.setShowLeftShadow(true);
        dropShadowBorder4.setShowTopShadow(true);
        historyPanel.setBorder(dropShadowBorder4);
        historyPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        historyPanel.setPreferredSize(new java.awt.Dimension(290, 220));
        historyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sendResult.setText("Send Result");
        sendResult.setEnabled(false);
        sendResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendResultActionPerformed(evt);
            }
        });
        historyPanel.add(sendResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        costTxt.setEnabled(false);
        historyPanel.add(costTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 110, -1));

        param4.setText("Resolution/Comments");
        historyPanel.add(param4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 10));

        param5.setText("Cost");
        historyPanel.add(param5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 10));

        commentsTxt.setColumns(20);
        commentsTxt.setLineWrap(true);
        commentsTxt.setRows(5);
        commentsTxt.setWrapStyleWord(true);
        commentsTxt.setEnabled(false);
        jScrollPane1.setViewportView(commentsTxt);

        historyPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 240, 70));

        msgLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        msgLabel1.setForeground(new java.awt.Color(255, 0, 51));
        msgLabel1.setText("jLabel1");
        historyPanel.add(msgLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 20));

        emergencyBtn.setBackground(new java.awt.Color(255, 0, 102));
        emergencyBtn.setText("Emergency Center");
        emergencyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emergencyBtnActionPerformed(evt);
            }
        });

        emergencyPanel.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder5 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder5.setShadowColor(new java.awt.Color(204, 204, 204));
        dropShadowBorder5.setShowLeftShadow(true);
        dropShadowBorder5.setShowTopShadow(true);
        emergencyPanel.setBorder(dropShadowBorder5);
        emergencyPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emergencyPanel.setPreferredSize(new java.awt.Dimension(290, 143));
        emergencyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emergencyRequestTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emergencyRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Patient Name", "Appointment Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(emergencyRequestTable);

        emergencyPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 260, 70));

        jButton2.setText("Assign to me");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        emergencyPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emergencyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emergencyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(recordBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medicineBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(recordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(historyPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(historyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(emergencyBtn)
                .addGap(0, 0, 0)
                .addComponent(emergencyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(medicineBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(recordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(historyBtn)
                .addGap(5, 5, 5)
                .addComponent(historyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void recordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordBtnActionPerformed
        // TODO add your handling code here:
        if (recordBtn.isSelected()) {
            emergencyBtn.setSelected(false);
            emergencyPanel.setVisible(false);
            recordPanel.setVisible(true);
            medicineBtn.setSelected(false);
            medPanel.setVisible(false);
            historyBtn.setSelected(false);
            historyPanel.setVisible(false);

            int row = requestTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "No row selected!!!");
                //return;
            } else {
                if (!patientNameTxt.getText().trim().equalsIgnoreCase("")) {
                    startrecordbtn.setEnabled(true);
                } else {
                    startrecordbtn.setEnabled(false);
                }
            }

        }
    }//GEN-LAST:event_recordBtnActionPerformed

    private void medicineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineBtnActionPerformed
        // TODO add your handling code here:
        if (medicineBtn.isSelected()) {
            emergencyBtn.setSelected(false);
            emergencyPanel.setVisible(false);
            recordPanel.setVisible(false);
            recordBtn.setSelected(false);
            medPanel.setVisible(true);
            historyBtn.setSelected(false);
            historyPanel.setVisible(false);
        }
    }//GEN-LAST:event_medicineBtnActionPerformed

    private void historyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyBtnActionPerformed
        // TODO add your handling code here:
        if (historyBtn.isSelected()) {
            emergencyBtn.setSelected(false);
            emergencyPanel.setVisible(false);
            recordPanel.setVisible(false);
            recordBtn.setSelected(false);
            medPanel.setVisible(false);
            medicineBtn.setSelected(false);
            historyPanel.setVisible(true);
            if (patientNameTxt.getText().trim().length() != 0) {
                populatePrescription();

            }
        }
    }//GEN-LAST:event_historyBtnActionPerformed

    private void populatePrescription() {
        String speciality = doctor.getSpeciality();

        switch (speciality) {
            case "General Physician":
                ArrayList treatment = null;

                for (Map.Entry<String, ArrayList> entry : genPhysicianMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment = entry.getValue();

                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment.get(0)));
                        costTxt.setText("200");

                    }
                }

                break;
            case "Dentist":
                ArrayList treatment2 = null;
                for (Map.Entry<String, ArrayList> entry : dentistMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment2 = entry.getValue();

                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment2.get(0)));
                        costTxt.setText(String.valueOf(treatment2.get(1)));

                    }
                }
                break;
            case "Gynecologist/Obstetrician":
                ArrayList treatment3 = null;
                for (Map.Entry<String, ArrayList> entry : gyneMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment3 = entry.getValue();

                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment3.get(0)));
                        costTxt.setText(String.valueOf(treatment3.get(1)));

                    }
                }

                break;
            case "Dermatologist/Cosmetologist":

                ArrayList treatment4 = null;
                for (Map.Entry<String, ArrayList> entry : dermaMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment4 = entry.getValue();
                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment4.get(0)));
                        costTxt.setText(String.valueOf(treatment4.get(1)));

                    }
                }

                break;
            case "Homeopath":
                ArrayList treatment5 = null;
                for (Map.Entry<String, ArrayList> entry : homeoMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment5 = entry.getValue();

                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment5.get(0)));
                        costTxt.setText(String.valueOf(treatment5.get(1)));

                    }

                }

                break;
            case "Ayurveda":

                ArrayList treatment6 = null;
                for (Map.Entry<String, ArrayList> entry : ayurvedMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment6 = entry.getValue();

                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment6.get(0)));
                        costTxt.setText(String.valueOf(treatment6.get(1)));

                    }
                }

                break;
            case "Cardiologist":

                ArrayList treatment7 = null;
                for (Map.Entry<String, ArrayList> entry : cardioMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment7 = entry.getValue();
                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment7.get(0)));
                        costTxt.setText(String.valueOf(treatment7.get(1)));

                    }
                }

                break;
            case "Gastroenterologist":

                ArrayList treatment8 = null;
                for (Map.Entry<String, ArrayList> entry : gastroMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment8 = entry.getValue();
                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment8.get(0)));
                        costTxt.setText(String.valueOf(treatment8.get(1)));
                        break;
                    }
                }
                break;

            case "Orthopedist":
                ArrayList treatment9 = null;
                for (Map.Entry<String, ArrayList> entry : orthoMap.entrySet()) {
                    String serviceName = entry.getKey();
                    treatment9 = entry.getValue();

                    if (servicesCombo.getSelectedItem().equals(serviceName)) {
                        commentsTxt.setText(String.valueOf(treatment9.get(0)));
                        costTxt.setText(String.valueOf(treatment9.get(1)));

                    }
                }

                break;
        }

    }
    private void generateReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportBtnActionPerformed
        // TODO add your handling code here:
        storeData(doctor.getSpeciality());
        generateReportBtn.setEnabled(false);
        sendResult.setEnabled(true);
    }//GEN-LAST:event_generateReportBtnActionPerformed

    private void sendResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendResultActionPerformed
        // TODO add your handling code here:
        int row = requestTable.getSelectedRow();
        if (row < 0) {
            commentsTxt.setText("");
            costTxt.setText("");
            msgLabel1.setVisible(false);

            return;

        }
        WorkRequest workRequest = (WorkRequest) requestTable.getValueAt(row, 0);
        if (workRequest instanceof AppointmentRequest) {
            ((AppointmentRequest) workRequest).setPrescription(commentsTxt.getText());
            ((AppointmentRequest) workRequest).setCost(costTxt.getText());
        } else {
            workRequest.setMessage(commentsTxt.getText());
        }
            CallMessageUtility.sendMessage("+1" + workRequest.getSender().getPatients().getMobileNum(), "Dear Customer,Appointment has been placed!!");
            
        msgLabel1.setVisible(true);
        msgLabel1.setForeground(Color.red);
        msgLabel1.setText("Your Report has been sent");
        msgLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/success.gif")));
        populateRequestTable();
        populateEmergencyRequestTable();

    }//GEN-LAST:event_sendResultActionPerformed

    private void emergencyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emergencyBtnActionPerformed
        // TODO add your handling code here:
        if (emergencyBtn.isSelected()) {
            //emergencyBtn.setSelected(true);
            emergencyPanel.setVisible(true);
            recordPanel.setVisible(false);
            recordBtn.setSelected(false);
            medPanel.setVisible(false);
            medicineBtn.setSelected(false);
            historyPanel.setVisible(false);
        }
    }//GEN-LAST:event_emergencyBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row = emergencyRequestTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "No row selected!!!");
            return;
        }
        //try

        WorkRequest emergencyWorkRequest = (WorkRequest) emergencyRequestTable.getValueAt(row, 0);
        if (!emergencyWorkRequest.getStatus().equalsIgnoreCase("Confirmed") && 
                !emergencyWorkRequest.getStatus().equalsIgnoreCase("Completed")) {
            emergencyWorkRequest.setStatus("Confirmed");
            emergencyWorkRequest.setReceiver(userAccount);
            userAccount.getWorkQueue().getWorkRequestList().add(emergencyWorkRequest);
            populateEmergencyRequestTable();
            populateRequestTable();
            JOptionPane.showMessageDialog(null, "Transferred to your work Queue");
        }

    }//GEN-LAST:event_jButton2ActionPerformed
    public void initParamData(String speciality) {
        //servicesCombo.removeAllItems();
        int age = 0;
        try {
            age = Integer.valueOf(patientAgeTxt.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error : Age has not been populated", "Age Error", JOptionPane.ERROR_MESSAGE);
        }

        //age = 25;
        switch (speciality) {
            case "General Physician":
            case "Homeopath":
            case "Ayurveda":
            case "Cardiologist":
            case "Gastroenterologist":
                param1Txt.setText(String.valueOf(generateRandomValues(50, 150)));
                param2Txt.setText(String.valueOf(generateRandomValues(15, 40)));
                param3Txt.setText(String.valueOf(generateRandomValues(80, 150)));

                float respiratoryRate = Float.valueOf(param2Txt.getText());
                float heartRate = Float.valueOf(param1Txt.getText());
                float bloodPressure = Float.valueOf(param3Txt.getText());
                if (age >= 1 && age <= 3) {
                    if ((respiratoryRate >= 20 && respiratoryRate <= 30)) {
                        param2Txt.setBackground(new Color(1, 126, 20));
                        param2Txt.setForeground(Color.white);
                    } else {
                        param2Txt.setBackground(new Color(238, 28, 4));
                        param2Txt.setForeground(Color.white);
                    }
                    if (heartRate >= 80 && heartRate <= 130) {
                        param1Txt.setBackground(new Color(1, 126, 20));
                        param1Txt.setForeground(Color.white);
                    } else {
                        param1Txt.setBackground(new Color(238, 28, 4));
                        param1Txt.setForeground(Color.white);
                    }
                    if (bloodPressure >= 80 && bloodPressure <= 110) {
                        param3Txt.setBackground(new Color(1, 126, 20));
                        param3Txt.setForeground(Color.white);
                    } else {
                        param3Txt.setBackground(new Color(238, 28, 4));
                        param3Txt.setForeground(Color.white);
                    }

                }
                /**
                 * Preschooler (4 – 5years) *
                 */
                if (age >= 4 && age == 5) {
                    if ((respiratoryRate >= 20 && respiratoryRate <= 30)) {
                        param2Txt.setBackground(new Color(1, 126, 20));
                        param2Txt.setForeground(Color.white);
                    } else {
                        param2Txt.setBackground(new Color(238, 28, 4));
                        param2Txt.setForeground(Color.white);
                    }
                    if (heartRate >= 80 && heartRate <= 120) {
                        param1Txt.setBackground(new Color(1, 126, 20));
                        param1Txt.setForeground(Color.white);
                    } else {
                        param1Txt.setBackground(new Color(238, 28, 4));
                        param1Txt.setForeground(Color.white);
                    }
                    if (bloodPressure >= 80 && bloodPressure <= 110) {
                        param3Txt.setBackground(new Color(1, 126, 20));
                        param3Txt.setForeground(Color.white);
                    } else {
                        param3Txt.setBackground(new Color(238, 28, 4));
                        param3Txt.setForeground(Color.white);
                    }

                }
                /**
                 * School Age (6 – 12 years) *
                 */
                if (age >= 6 && age <= 12) {
                    if ((respiratoryRate >= 20 && respiratoryRate <= 30)) {
                        param2Txt.setBackground(new Color(1, 126, 20));
                        param2Txt.setForeground(Color.white);
                    } else {
                        param2Txt.setBackground(new Color(238, 28, 4));
                        param2Txt.setForeground(Color.white);
                    }
                    if (heartRate >= 70 && heartRate <= 110) {
                        param1Txt.setBackground(new Color(1, 126, 20));
                        param1Txt.setForeground(Color.white);
                    } else {
                        param1Txt.setBackground(new Color(238, 28, 4));
                        param1Txt.setForeground(Color.white);
                    }
                    if (bloodPressure >= 80 && bloodPressure <= 120) {
                        param3Txt.setBackground(new Color(1, 126, 20));
                        param3Txt.setForeground(Color.white);
                    } else {
                        param3Txt.setBackground(new Color(238, 28, 4));
                        param3Txt.setForeground(Color.white);
                    }

                }
                /**
                 * Adolescent (13+years) *
                 */
                if (age >= 13) {
                    if ((respiratoryRate >= 12 && respiratoryRate <= 20)) {
                        param2Txt.setBackground(new Color(1, 126, 20));
                        param2Txt.setForeground(Color.white);
                    } else {
                        param2Txt.setBackground(new Color(238, 28, 4));
                        param2Txt.setForeground(Color.white);
                    }
                    if (heartRate >= 55 && heartRate <= 105) {
                        param1Txt.setBackground(new Color(1, 126, 20));
                        param1Txt.setForeground(Color.white);
                    } else {
                        param1Txt.setBackground(new Color(238, 28, 4));
                        param1Txt.setForeground(Color.white);
                    }
                    if (bloodPressure >= 110 && bloodPressure <= 120) {
                        param3Txt.setBackground(new Color(1, 126, 20));
                        param3Txt.setForeground(Color.white);
                    } else {
                        param3Txt.setBackground(new Color(238, 28, 4));
                        param3Txt.setForeground(Color.white);
                    }

                }

                break;
            case "Gynecologist/Obstetrician":
                Random randomno_1 = new Random();

                // get next next boolean value 
                boolean value_1 = randomno_1.nextBoolean();

                if (!value_1) {
                    param1Txt.setText("NO");
                    param1Txt.setBackground(new Color(1, 126, 20));
                    param1Txt.setForeground(Color.white);
                } else {
                    param1Txt.setText("Yes");
                    param1Txt.setBackground(new Color(238, 28, 4));
                    param1Txt.setForeground(Color.white);
                }
                break;
            case "Dentist":
                // create random object
                Random randomno1 = new Random();

                // get next next boolean value 
                boolean value_ = randomno1.nextBoolean();
                if (!value_) {
                    param2Txt.setText("NO");
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                } else {
                    param2Txt.setText("Yes");
                    param2Txt.setBackground(new Color(1, 126, 20));
                    param2Txt.setForeground(Color.white);
                }

                int dentalDecay = generateRandomValues(55, 80);
                param1Txt.setText(String.valueOf(dentalDecay));
                if (dentalDecay >= 55 && dentalDecay <= 80) {
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                } else {
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                }
                break;

            case "Dermatologist/Cosmetologist":
                // create random object
                Random randomno = new Random();

                // get next next boolean value 
                boolean value = randomno.nextBoolean();
                if (!value) {
                    param1Txt.setText("NO");
                    param1Txt.setBackground(new Color(238, 28, 4));
                    param1Txt.setForeground(Color.white);
                } else {
                    param1Txt.setText("Yes");
                    param1Txt.setBackground(new Color(1, 126, 20));
                    param1Txt.setForeground(Color.white);
                }

                int dermaMoisture = generateRandomValues(45, 100);
                param2Txt.setText(String.valueOf(dermaMoisture));
                if (dermaMoisture >= 45 && dermaMoisture <= 100) {
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                } else {
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                }
                break;

            case "Orthopedist":
                int calciumConten = generateRandomValues(45, 100);
                param1Txt.setText(String.valueOf(calciumConten));
                if (calciumConten >= 45 && calciumConten <= 100) {
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                } else {
                    param2Txt.setBackground(new Color(238, 28, 4));
                    param2Txt.setForeground(Color.white);
                }

                break;
        }
    }
    private void startrecordbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startrecordbtnActionPerformed
        // TODO add your handling code here:
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
                        //param1Txt.setBackground(Color.yellow);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Timer error has occurred", "Error", JOptionPane.ERROR);
                    }
//                    if () {
//
//                    }
                }
                initParamData(doctor.getSpeciality());
                startrecordbtn.setEnabled(false);
                generateReportBtn.setEnabled(true);
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
    }//GEN-LAST:event_startrecordbtnActionPerformed
    public void storeData(String speciality) {
        //servicesCombo.removeAllItems();
        int age = Integer.valueOf(patientAgeTxt.getText());
        //age = 25;
        int row = requestTable.getSelectedRow();
        msgLabel.setVisible(false);

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "No row selected!!!");
            return;
        }
        try {
            WorkRequest workRequest = (WorkRequest) requestTable.getValueAt(row, 0);
            workRequest.setReceiver(userAccount);
            MedicalHistory medicalHistory = workRequest.getSender().getPatients().getVitalSignHistory();
            MedicalReportEntity medicalReportEntity = medicalHistory.addMedicalEntity();
            switch (speciality) {
                case "General Physician":
                case "Homeopath":
                case "Ayurveda":
                case "Cardiologist":
                case "Gastroenterologist":
                    float respiratoryRate = Float.valueOf(param2Txt.getText());
                    float heartRate = Float.valueOf(param1Txt.getText());
                    float bloodPressure = Float.valueOf(param3Txt.getText());
                    medicalReportEntity.setRespiratoryRate(respiratoryRate);
                    medicalReportEntity.setBloodPressure(bloodPressure);
                    medicalReportEntity.setHeartRate(heartRate);
                    boolean flaghr = false,
                     flagbp = false,
                     flagrr = false;

                    if (age >= 1 && age <= 3) {
                        if ((respiratoryRate >= 20 && respiratoryRate <= 30)) {
                            flagrr = true;
                        }
                        if (heartRate >= 80 && heartRate <= 130) {
                            flaghr = true;
                        }
                        if (bloodPressure >= 80 && bloodPressure <= 110) {
                            flagbp = true;
                        }

                    }
                    /**
                     * Preschooler (4 – 5years) *
                     */
                    if (age >= 4 && age == 5) {
                        if ((respiratoryRate >= 20 && respiratoryRate <= 30)) {
                            flagrr = true;
                        }
                        if (heartRate >= 80 && heartRate <= 120) {
                            flaghr = true;
                        }
                        if (bloodPressure >= 80 && bloodPressure <= 110) {
                            flagbp = true;
                        }

                    }
                    /**
                     * School Age (6 – 12 years) *
                     */
                    if (age >= 6 && age <= 12) {
                        if ((respiratoryRate >= 20 && respiratoryRate <= 30)) {
                            flagrr = true;
                        }
                        if (heartRate >= 70 && heartRate <= 110) {
                            flaghr = true;
                        }
                        if (bloodPressure >= 80 && bloodPressure <= 120) {
                            flagbp = true;
                        }

                    }
                    /**
                     * Adolescent (13+years) *
                     */
                    if (age >= 13) {
                        if ((respiratoryRate >= 12 && respiratoryRate <= 20)) {
                            flagrr = true;
                        }
                        if (heartRate >= 55 && heartRate <= 105) {
                            flaghr = true;
                        }
                        if (bloodPressure >= 110 && bloodPressure <= 120) {
                            flagbp = true;
                        }

                    }
                    if (flagrr & flagbp & flaghr) {
                        medicalReportEntity.setStatus("Normal");
                    } else {
                        medicalReportEntity.setStatus("Abnormal");

                    }

                    break;
                case "Dentist":
                    // create random object
                    Random randomno1 = new Random();

                    // get next next boolean value 
                    boolean value_ = randomno1.nextBoolean();
                    if (param2Txt.getText().equalsIgnoreCase("yes")) {
                        medicalReportEntity.setBleedingGumsPresent(true);
                    } else {
                        medicalReportEntity.setBleedingGumsPresent(false);
                    }

                    int dentalDecay = Integer.valueOf(param1Txt.getText());
                    medicalReportEntity.setDentalDecay(dentalDecay);
                    if (dentalDecay >= 55 && dentalDecay <= 80) {
                        medicalReportEntity.setStatus("Normal");
                    } else {
                        medicalReportEntity.setStatus("Normal");
                    }
                    break;

                case "Gynecologist/Obstetrician":
                    if (param1Txt.getText().equalsIgnoreCase("yes")) {
                        medicalReportEntity.setGynecProbFlag(true);
                        medicalReportEntity.setStatus("Abnormal");
                    } else {
                        medicalReportEntity.setGynecProbFlag(false);
                        medicalReportEntity.setStatus("Normal");
                    }
                    break;

                case "Dermatologist/Cosmetologist":
                    if (param1Txt.getText() != null) {
                        if (param1Txt.getText().equalsIgnoreCase("yes")) {
                            medicalReportEntity.setSkinProbFlag(true);
                        } else {
                            medicalReportEntity.setSkinProbFlag(false);
                        }
                    }
                    int dermaMoisture = Integer.valueOf(param2Txt.getText());
                    medicalReportEntity.setPercentHydrationinSkin(dermaMoisture);
                    if ((dermaMoisture >= 45 && dermaMoisture <= 100)
                            && (medicalReportEntity.isSkinProbFlag())) {
                        medicalReportEntity.setStatus("Normal");
                    } else {
                        medicalReportEntity.setStatus("Abnormal");
                    }
                    break;

                case "Orthopedist":
                    int calciumContent = Integer.valueOf(param1Txt.getText());
                    medicalReportEntity.setOrthoCalciumTest(calciumContent);
                    if (calciumContent >= 45 && calciumContent <= 100) {
                        medicalReportEntity.setStatus("Normal");
                    } else {
                        medicalReportEntity.setStatus("Abnormal");
                    }

                    break;
            }
            if (medicalReportEntity.getStatus().equalsIgnoreCase("Normal")) {
                msgLabel.setVisible(true);
                msgLabel.setForeground(Color.green);
                msgLabel.setText("Your Report seems Normal");
                msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/success.gif")));
            } else {
                msgLabel.setVisible(true);
                msgLabel.setForeground(Color.red);
                msgLabel.setText("Your Report seems Abnormal");
                msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/imgs/alert-bad.gif")));
            }
            workRequest.setStatus("Completed");
            workRequest.setResolveDate(new Date());
            workRequest.setMessage(msgLabel.getText());
            workRequest.setReceiver(userAccount);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "System Error :" + e.getMessage());
            return;
        }

    }
    private void param3TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_param3TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_param3TxtActionPerformed

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        // TODO add your handling code here:
        WorkRequest request = (WorkRequest) requestTable.getValueAt(requestTable.getSelectedRow(), 0);
        patientAgeTxt.setText(String.valueOf(request.getSender().getPatients().getAge()));
        patientNameTxt.setText(String.valueOf(request.getSender().getPatients().getName()));
    }//GEN-LAST:event_requestTableMouseClicked

    private void servicesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicesComboActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_servicesComboActionPerformed

    private void patientNameTxtPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_patientNameTxtPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_patientNameTxtPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea commentsTxt;
    private javax.swing.JTextField costTxt;
    private javax.swing.JToggleButton emergencyBtn;
    private javax.swing.JPanel emergencyPanel;
    private javax.swing.JTable emergencyRequestTable;
    private javax.swing.JButton generateReportBtn;
    private javax.swing.JToggleButton historyBtn;
    private javax.swing.JPanel historyPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel medPanel;
    private javax.swing.JToggleButton medicineBtn;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JLabel msgLabel1;
    private javax.swing.JLabel param1;
    private javax.swing.JTextField param1Txt;
    private javax.swing.JLabel param2;
    private javax.swing.JTextField param2Txt;
    private javax.swing.JLabel param3;
    private javax.swing.JTextField param3Txt;
    private javax.swing.JLabel param4;
    private javax.swing.JLabel param5;
    private javax.swing.JTextField patientAgeTxt;
    private javax.swing.JTextField patientNameTxt;
    private javax.swing.JToggleButton recordBtn;
    private javax.swing.JPanel recordPanel;
    private javax.swing.JTable requestTable;
    private javax.swing.JButton sendResult;
    private javax.swing.JComboBox servicesCombo;
    private javax.swing.JButton startrecordbtn;
    // End of variables declaration//GEN-END:variables
}
