/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.userinterface.patientRole;

/**
 *
 * @author Ankur
 */
import com.call.*;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.util.*;
import com.twilio.sdk.*;
import com.twilio.sdk.resource.factory.*;
import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.resource.list.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class CallMessageUtility {

    // Find your Account Sid and Token at twilio.com/user/account 
    public static final String ACCOUNT_SID = "ACd01db9e91af6e60b97d074e78aa8cb04";
    public static final String AUTH_TOKEN = "8d6ebd077973354d610825dca861fd4c";

    public static void makeCallSendMessage(String recipient) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build a filter for the CallList
        try{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Url", "http://demo.twilio.com/docs/voice.xml"));
        params.add(new BasicNameValuePair("To", "+16172598868"));
        params.add(new BasicNameValuePair("From", "+18572608316"));
        params.add(new BasicNameValuePair("Method", "GET"));
        params.add(new BasicNameValuePair("StatusCallback", "https://www.myapp.com/events"));
        params.add(new BasicNameValuePair("StatusCallbackMethod", "POST"));
        params.add(new BasicNameValuePair("StatusCallbackEvent", "initiated"));
        params.add(new BasicNameValuePair("StatusCallbackEvent", "ringing"));
        params.add(new BasicNameValuePair("StatusCallbackEvent", "answered"));
        params.add(new BasicNameValuePair("StatusCallbackEvent", "completed"));
        Map map = new HashMap();
        map.put("Url", "http://demo.twilio.com/docs/voice.xml");
        map.put("To", "+16172598868");//6172598868 //6175833752 //8578002942 //8578003523 //6177128475
        map.put("From", "+12015523144"); //8572608316
        map.put("Method", "GET");
        map.put("Url", "http://demo.twilio.com/docs/voice.xml");
        map.put("StatusCallback", "https://www.myapp.com/events");
//        map.put("StatusCallbackMethod", "POST");
//        map.put("StatusCallbackMethod", "initiated");
//        map.put("StatusCallbackMethod", "ringing");
//        map.put("StatusCallbackMethod", "answered");
//        map.put("StatusCallbackMethod", "completed");
        /**
         * CALL *
         */
        CallFactory callFactory = client.getAccount().getCallFactory();
        Call call = callFactory.create(map);
        System.out.println("Call Session Id:" + call.getSid());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : Network seems to be disconnected", "Network Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void sendMessage(String recipient, String messageText) {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        /**
         * SEND TEXT MESSAGE *
         */
        Map msgParams = new HashMap();
        msgParams.put("To", recipient);
        msgParams.put("From", "+12015523144");
        msgParams.put("Body", messageText);
        msgParams.put("MediaUrl", "http://farm2.static.flickr.com/1075/1404618563_3ed9a44a3a.jpg");

        try {
            SmsFactory messageFactory = client.getAccount().getSmsFactory();
            Sms message = null;
            try {
                message = messageFactory.create(msgParams);
            } catch (TwilioRestException ex) {
                JOptionPane.showMessageDialog(null, "Error : Number not registered", "Twilio Error", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(message.getSid());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : Network seems to be disconnected", "Network Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws TwilioRestException {

    }
}
