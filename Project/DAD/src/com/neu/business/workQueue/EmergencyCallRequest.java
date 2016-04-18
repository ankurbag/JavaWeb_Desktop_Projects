/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.workQueue;

/**
 *
 * @author raunak
 */
public class EmergencyCallRequest extends WorkRequest{
    
    private String testResult;
    
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Override
    public String toString() {
        return super.getSender().getUsername(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
