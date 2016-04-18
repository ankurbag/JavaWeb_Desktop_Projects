/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.role;

import com.neu.business.DB4OUtil.DB4OUtil;
import com.neu.business.EcoSystem;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.organization.DoctorOrganization;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
import com.neu.userinterface.DoctorRole.DoctorWorkAreaPanel;
//import UserInterface.DoctorRole.DoctorWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class DoctorRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, DB4OUtil dB4OUtil) {
        
        return new DoctorWorkAreaPanel(userProcessContainer, account, enterprise,(DoctorOrganization)organization, dB4OUtil, business);
    }
    
    
}
