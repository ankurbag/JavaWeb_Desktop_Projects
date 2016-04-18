/*
 *Class Name : MedicalReportEntity 
 *Description : Patient's Vital SIgn Bean
 *Version History:
 *Version   Date        Author  Comments
 *-----------------------------------------------
 *0.1       09/21/2015  Ankur   Initial Draft
 */
package com.neu.business.patients;

/**
 *
 * @author Ankur
 */
public class MedicalReportEntity {

    private float respiratoryRate;
    private float heartRate;
    private float bloodPressure;
    private float weights;
    /**Dentist MedicalReportEntity **/
    private String radioGraph;
    private boolean bleedingGumsPresent;
    private float dentalDecay;
    /**Gynecologist/Obstetrician**/
    private boolean gynecProbFlag;
    /**Derma **/
    private float percentHydrationinSkin;
    private boolean skinProbFlag;
    
    /**Homeopath **/
    private float generalHealthTest;
    
    /**Ayurveda **/
    //private float generalHealthTest;
    
    private float orthoCalciumTest;
    private float enzymesTest;

    public String getRadioGraph() {
        return radioGraph;
    }

    public void setRadioGraph(String radioGraph) {
        this.radioGraph = radioGraph;
    }

    public boolean isBleedingGumsPresent() {
        return bleedingGumsPresent;
    }

    public void setBleedingGumsPresent(boolean bleedingGumsPresent) {
        this.bleedingGumsPresent = bleedingGumsPresent;
    }

    public float getDentalDecay() {
        return dentalDecay;
    }

    public void setDentalDecay(float dentalDecay) {
        this.dentalDecay = dentalDecay;
    }

    public boolean isGynecProbFlag() {
        return gynecProbFlag;
    }

    public void setGynecProbFlag(boolean gynecProbFlag) {
        this.gynecProbFlag = gynecProbFlag;
    }

    public float getPercentHydrationinSkin() {
        return percentHydrationinSkin;
    }

    public void setPercentHydrationinSkin(float percentHydrationinSkin) {
        this.percentHydrationinSkin = percentHydrationinSkin;
    }

    public boolean isSkinProbFlag() {
        return skinProbFlag;
    }

    public void setSkinProbFlag(boolean skinProbFlag) {
        this.skinProbFlag = skinProbFlag;
    }

    public float getGeneralHealthTest() {
        return generalHealthTest;
    }

    public void setGeneralHealthTest(float generalHealthTest) {
        this.generalHealthTest = generalHealthTest;
    }

    public float getOrthoCalciumTest() {
        return orthoCalciumTest;
    }

    public void setOrthoCalciumTest(float orthoCalciumTest) {
        this.orthoCalciumTest = orthoCalciumTest;
    }

    public float getEnzymesTest() {
        return enzymesTest;
    }

    public void setEnzymesTest(float enzymesTest) {
        this.enzymesTest = enzymesTest;
    }
    
    
    private String date;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(float respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(float heartRate) {
        this.heartRate = heartRate;
    }

    public float getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public float getWeights() {
        return weights;
    }

    public void setWeights(float weights) {
        this.weights = weights;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.date; //To change body of generated methods, choose Tools | Templates.
    }

}
