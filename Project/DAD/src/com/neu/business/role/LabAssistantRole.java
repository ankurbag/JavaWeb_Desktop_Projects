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
//import UserInterface.LabAssistantRole.LabAssistantWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class LabAssistantRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, DB4OUtil dB4OUtil) {
        //return new LabAssistantWorkAreaJPanel(userProcessContainer, account, organization, business);
        return null;
    }
    
}
