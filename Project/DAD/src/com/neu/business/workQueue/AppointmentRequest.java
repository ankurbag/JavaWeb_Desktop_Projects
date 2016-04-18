/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.workQueue;

/**
 *
 * @author raunak
 */
public class AppointmentRequest extends WorkRequest{
    
    private String appointmentSlot;
    private String prescription;

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    
    
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    private String cost;
    
    
    public String getAppointmentSlot() {
        return appointmentSlot;
    }

    public void setAppointmentTimeSlot(String testResult) {
        this.appointmentSlot = testResult;
    }

    @Override
    public String toString() {
        return appointmentSlot; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
