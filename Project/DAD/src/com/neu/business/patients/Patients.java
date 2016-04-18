/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.patients;

import com.neu.business.employee.*;

/**
 *
 * @author raunak
 */
public class Patients {

    private String name;
    private int id;
    private static int count = 1;
    private String emailId;
    private String mobileNum;
    private String address;
    private MedicalHistory vitalSignHistory;
    private int age;
    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MedicalHistory getVitalSignHistory() {
        return vitalSignHistory;
    }

    public void setVitalSignHistory(MedicalHistory vitalSignHistory) {
        this.vitalSignHistory = vitalSignHistory;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }
    private Schedule schedule;

    public Patients() {
        id = count;
        count++;
        schedule = new Schedule();
        vitalSignHistory = new MedicalHistory();
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
