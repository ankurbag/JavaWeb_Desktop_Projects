/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.administrativeRole;

import com.alee.extended.layout.ComponentPanelLayout;
import com.neu.userinterface.patientRole.*;
import com.alee.extended.panel.WebAccordionStyle;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import com.neu.business.EcoSystem;
import com.neu.business.employee.Employee;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.organization.DoctorOrganization;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author raunak
 */
public class HospitalAdminDashboard extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem system;
    JPanel jPanel7;
    ArrayList<Employee> doctors;
    Enterprise enterprise;
    private UserAccount userAccount;
    private ImageIcon icon = null;
    private ImageIcon icon1 = null;
    private ImageIcon icon2 = null;
    private ImageIcon icon3 = null;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public HospitalAdminDashboard(EcoSystem system) {

    }

    public HospitalAdminDashboard(JPanel userProcessContainer, EcoSystem ecoSystem, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = ecoSystem;
        this.doctors = doctors;
        //this.userAccount = userAccount;
        this.enterprise = enterprise;
        icon = new ImageIcon();
        icon1 = new ImageIcon();
        icon2 = new ImageIcon();
        
        //populateTable();
        //UIUtilities.initButtonComponents(backBtn);
        webAccordion1.setAccordionStyle(WebAccordionStyle.accordionStyle);

        //populateComboBox();
        populateAppointmentStatistics();
        populateStarPerformerStatistics();
        initWebAccordion();

    }

    public void initWebAccordion() {
        BufferedImage img = null;
        BufferedImage img1 = null;
        BufferedImage img2 = null;
        try {
            img = ImageIO.read(getClass().getResource("/com/imgs/Business-Statistics-icon.png"));
            img1 = ImageIO.read(getClass().getResource("/com/imgs/statistics-icon.png"));
            img2 = ImageIO.read(getClass().getResource("/com/imgs/Statistics-icon-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        Image dimg1 = img1.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        Image dimg2 = img2.getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        icon.setImage(dimg);
        icon1.setImage(dimg1);
        icon2.setImage(dimg2);

        JPanel requestPanel = populatePieChart();

        webAccordion1.setMultiplySelectionAllowed(false);
        //webAccordion1.addPane(icon, "Table styling", createCustomForTableContent("Table is here"));
        webAccordion1.addPane(icon, "Appointment Statistics", createtable(appointmentStatsTable));
        webAccordion1.addPane(icon2, "Doctor Statistics", createtable(topperformersTable));
        webAccordion1.addPane(icon1, "Top Contributors", createCustomForContent(requestPanel));
    }

    public JPanel populatePieChart() {
        final PieDataset dataset = createSampleDataset();

        // create the chart...
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(370,200));
        //setContentPane(chartPanel);
        return chartPanel;
    }

    private PieDataset createSampleDataset() {

        Organization org = null;
        //Printing to the Table
        for (Organization organization
                : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DoctorOrganization) {
                org = organization;
            }
        }
        List<UserAccount> userAccounts = null;
        if (org != null) {
            userAccounts = org.getUserAccountDirectory().getUserAccountList();
            Collections.sort(userAccounts, new Comparator<UserAccount>() {
                @Override
                public int compare(UserAccount o1, UserAccount o2) {
                    return Integer.valueOf(o2.getWorkQueue().getWorkRequestList().size()).
                            compareTo(o1.getWorkQueue().getWorkRequestList().size());
                }
            });

        }

        final DefaultPieDataset result = new DefaultPieDataset();
        if(userAccounts!=null){
        for(UserAccount userAccount :userAccounts ){
            result.setValue(userAccount.getEmployee().getName(), userAccount.getWorkQueue().getWorkRequestList().size());
        }
        }
//        result.setValue("Java", new Double(43.2));
//        result.setValue("Visual Basic", new Double(10.0));
//        result.setValue("C/C++", new Double(17.5));
//        result.setValue("PHP", new Double(32.5));
//        result.setValue("Perl", new Double(1.0));
        return result;

    }

    private JFreeChart createChart(final PieDataset dataset) {

        final JFreeChart chart = ChartFactory.createPieChart3D(
                "", // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;

    }

    public void populateAppointmentStatistics() {

        int appointmentAppWaitCnt = 0;
        int appointmentAppConfirmedCnt = 0;
        int appointmentAppCompletedCnt = 0;
        int emergencyAppWaitCnt = 0;
        int emergencyAppConfirmedCnt = 0;
        int emergencyAppCompletedCnt = 0;
        //Appointment Requests
        for (Organization organization
                : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DoctorOrganization) {
                for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                    for (WorkRequest wr : ua.getWorkQueue().getWorkRequestList()) {
                        if (wr.getStatus().toLowerCase().contains("Requested")) {
                            appointmentAppWaitCnt = appointmentAppWaitCnt + 1;
                        }
                        if (wr.getStatus().equalsIgnoreCase("Confirmed")) {
                            appointmentAppConfirmedCnt = appointmentAppConfirmedCnt + 1;
                        }
                        if (wr.getStatus().equalsIgnoreCase("Completed")) {
                            appointmentAppCompletedCnt = appointmentAppCompletedCnt + 1;
                        }
                    }
                    //Completed
                }
            }
        }
        //Emergency Appointments Statistics
        for (Organization organization
                : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DoctorOrganization) {
                for (WorkRequest wr : organization.getWorkQueue().getWorkRequestList()) {
                    if (wr.getStatus().equalsIgnoreCase("Waiting")) {
                        emergencyAppWaitCnt = emergencyAppWaitCnt + 1;
                    }
                    if (wr.getStatus().equalsIgnoreCase("Confirmed")) {
                        emergencyAppConfirmedCnt = emergencyAppConfirmedCnt + 1;
                    }
                    if (wr.getStatus().equalsIgnoreCase("Completed")) {
                        emergencyAppCompletedCnt = emergencyAppCompletedCnt + 1;
                    }
                    //Completed
                }
            }
        }

        //populate table
        DefaultTableModel model = (DefaultTableModel) appointmentStatsTable.getModel();
        Object[] row = new Object[4];
        model.setRowCount(0);

        row[0] = "<html><b>Emergency</b></html>";
        row[1] = emergencyAppWaitCnt;
        row[2] = emergencyAppConfirmedCnt;
        row[3] = emergencyAppCompletedCnt;
        model.addRow(row);

        Object[] row2 = new Object[4];
        row2[0] = "<html><b>Appointments</b></html>";
        row2[1] = appointmentAppWaitCnt;
        row2[2] = appointmentAppConfirmedCnt;
        row2[3] = appointmentAppCompletedCnt;
        model.addRow(row2);

        Object[] row3 = new Object[4];
        row3[0] = "<html><b>Total</b></html>";
        row3[1] = appointmentAppWaitCnt + emergencyAppWaitCnt;
        row3[2] = appointmentAppConfirmedCnt + emergencyAppConfirmedCnt;
        row3[3] = appointmentAppCompletedCnt + emergencyAppCompletedCnt;
        model.addRow(row3);
        model.setNumRows(4);

        //model.setRowCount(0);
    }

    public void populateStarPerformerStatistics() {
        DefaultTableModel model = (DefaultTableModel) topperformersTable.getModel();
        Object[] row = new Object[4];
        model.setRowCount(0);
        Organization org = null;
        //Printing to the Table
        for (Organization organization
                : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DoctorOrganization) {
                org = organization;

            }
        }
        List<Employee> doctorList = null;
        if (org != null) {
            doctorList = org.getEmployeeDirectory().getEmployeeList();
            Collections.sort(doctorList, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return Float.valueOf(o2.getAvgReviews()).compareTo(o1.getAvgReviews());
                }
            });

            for (Employee e : doctorList) {
                row[0] = e.getName();
                row[1] = e.getAvgReviews();
                row[2] = e.getSpeciality();
                model.addRow(row);
            }
        }
    }

    public WebScrollPane createCustomForTableContent(String data) {
        JTable table = new JTable();
        return createCustomTableContent(150, 100, data, table);
    }

    public WebScrollPane createtable(JTable table) {
        //JTable table = new JTable();
        WebScrollPane scrollPane = new WebScrollPane(table);
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
        return createCustomContent(390, 200, doctorJPanel);
    }

    public void init() {
        JPanel[] doctorList = new JPanel[200];
        ArrayList<JPanel> doctorPanelList = new ArrayList<>();
        int i = 0;
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/com/imgs/administrator-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon.setImage(dimg);
        webAccordion1.setMultiplySelectionAllowed(false);
        for (Employee emp : doctors) {
            JPanel doctorPanel = new JPanel();
            doctorPanel.setBackground(Color.white);
            doctorPanel.setSize(400, 150);
            doctorPanel.setPreferredSize(new Dimension(400, 100));
            GridLayout grid = new GridLayout(3, 2);

            GridLayout layout = new GridLayout(3, 2);
            layout.setHgap(10);
            layout.setVgap(10);
            doctorPanel.setLayout(layout);
            //doctorList[i].setBorder(BorderFactory.createLineBorder(Color.yellow));
            JLabel lab1 = new JLabel(emp.getName(), JLabel.LEFT);
            lab1.setFont(new Font("Segoe UI", Font.BOLD, 12));
            JLabel lab2 = new JLabel(emp.getSpeciality(), JLabel.LEFT);
            lab2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            JLabel lab3 = new JLabel(emp.getAddress(), JLabel.LEFT);
            lab3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
            doctorPanel.add(button);
            //doctorPanel.add(button1);
            doctorPanel.add(lab2);

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

        jScrollPane2 = new javax.swing.JScrollPane();
        webAccordion1 = new com.alee.extended.panel.WebAccordion();
        jScrollPane1 = new javax.swing.JScrollPane();
        appointmentStatsTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        topperformersTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(420, 537));

        webAccordion1.setBackground(new java.awt.Color(255, 255, 255));
        webAccordion1.setPreferredSize(new java.awt.Dimension(370, 500));

        appointmentStatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Emergency", "", " ", ""},
                {"Appointment", "", "", ""},
                {"<html><b>Total</b></html>", null, null, null}
            },
            new String [] {
                "", "Request", "Confirmed", "Completed"
            }
        ));
        jScrollPane1.setViewportView(appointmentStatsTable);
        if (appointmentStatsTable.getColumnModel().getColumnCount() > 0) {
            appointmentStatsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            appointmentStatsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            appointmentStatsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        webAccordion1.add(jScrollPane1);

        topperformersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b style=\"color:blue\">Doctor Name</b></html>", "<html><b style=\"color:blue\">Avg Review</b></html>"
            }
        ));
        jScrollPane3.setViewportView(topperformersTable);
        if (topperformersTable.getColumnModel().getColumnCount() > 0) {
            topperformersTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            topperformersTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        }

        webAccordion1.add(jScrollPane3);

        jScrollPane2.setViewportView(webAccordion1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable appointmentStatsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable topperformersTable;
    private com.alee.extended.panel.WebAccordion webAccordion1;
    // End of variables declaration//GEN-END:variables
}
