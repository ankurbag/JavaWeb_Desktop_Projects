/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.role;

import com.neu.business.DB4OUtil.DB4OUtil;
import com.neu.business.EcoSystem;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public abstract class Role {
    
    public enum RoleType{
        
        Admin("Admin"),
        Doctor("Doctor"),
        LabAssistant("Lab Assistant"),
        Patient("Patient");
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(JPanel userProcessContainer, 
            UserAccount account, 
            Organization organization, 
            Enterprise enterprise, 
            EcoSystem business,DB4OUtil dB4OUtil);

    @Override
    public String toString() {
        return this.getClass().getName().toString();
    }
    
    
}