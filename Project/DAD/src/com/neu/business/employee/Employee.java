/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.employee;

import java.util.ArrayList;
import java.util.HashMap;

public class Employee {

    private String name;
    private int id;
    private static int count = 1;
    private String emailId;
    private String mobileNum;
    private String address;
    private boolean availability;
    private boolean status;
    private String speciality;
    private ArrayList<Integer> reviewCnt;
    private String reviewComments;
    private float avgReviews;
    
    public float getAvgReviews() {
        return avgReviews;
    }

    public void calculateAvgReviews() {
        int numOfElements = reviewCnt.size();
        int sum =0;
        for(int i=0;i< numOfElements;i++){
            sum = sum + reviewCnt.get(i);
        }
        if(numOfElements > 0)
            this.avgReviews = sum / numOfElements;
        else
            this.avgReviews = 0;
    }
    
    
    public ArrayList<Integer> getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(ArrayList<Integer> reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    
    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    private HashMap<String, ArrayList> schedule;

    public HashMap<String, ArrayList> getSchedule() {
        return schedule;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSchedule(HashMap<String, ArrayList> schedule) {
        this.schedule = schedule;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Employee() {
        id = count;
        schedule = new HashMap<>();
        reviewCnt = new ArrayList<>();
        count++;

    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
