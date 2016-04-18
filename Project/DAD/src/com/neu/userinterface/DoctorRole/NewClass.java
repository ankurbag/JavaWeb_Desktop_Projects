/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.DoctorRole;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ankur
 */
public class NewClass {
    public static void main(String[] args) throws ParseException {
        String time ="Fri Dec 11, 2015 - 7-8pm";
        System.out.println(time.substring(0, time.indexOf('-')).trim());
        DateFormat df = new SimpleDateFormat("EEE MMM dd, yyyy");
        Date dt = new Date();
        Date nd = df.parse(time);
        System.out.println(dt.after(nd));
    }
}
