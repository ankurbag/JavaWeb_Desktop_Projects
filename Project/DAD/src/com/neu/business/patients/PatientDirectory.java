/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.patients;

import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class PatientDirectory {
    
    private ArrayList<Patients> patientList;

    public PatientDirectory() {
        patientList = new ArrayList<>();
    }

    public ArrayList<Patients> getEmployeeList() {
        return patientList;
    }
    
    public Patients createPatient(String name){
        Patients patient = new Patients();
        patient.setName(name);
        patientList.add(patient);
        return patient;
    }
}