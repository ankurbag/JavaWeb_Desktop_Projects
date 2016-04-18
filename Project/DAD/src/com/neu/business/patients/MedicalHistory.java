/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.patients;

import java.util.ArrayList;

/**
 *
 * @author Ankur
 */
public class MedicalHistory {
    
    private ArrayList<MedicalReportEntity> medicalEntityLists;
    
    public MedicalHistory(){
        this.medicalEntityLists = new ArrayList<MedicalReportEntity>();
    }
    
    public MedicalReportEntity addMedicalEntity(){
        MedicalReportEntity medicalEntity = new MedicalReportEntity();
        medicalEntityLists.add(medicalEntity);
        return medicalEntity;
    }
    
    public void deleteVitalSign(MedicalReportEntity medicalEntity){
        medicalEntityLists.remove(medicalEntity);
    }

    public ArrayList<MedicalReportEntity> getVitalSignList() {
        return medicalEntityLists;
    }

    @Override
    public String toString() {
        return "VitalSignHistory"; 
    }
    
    
}
