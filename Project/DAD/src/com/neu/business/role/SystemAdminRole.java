/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.role;

import com.neu.business.DB4OUtil.DB4OUtil;
import com.neu.business.EcoSystem;
import com.neu.business.enterprise.Enterprise;
import com.neu.business.organization.Organization;
import com.neu.business.userAccount.UserAccount;
import com.neu.userinterface.systemAdminWorkArea.SysAdminWorkAreaPanel;
import javax.swing.JPanel;
//import userinterface.SystemAdminWorkArea.SystemAdminWorkAreaJPanel;

/**
 *
 * @author Rajat
 */
public class SystemAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, DB4OUtil dB4OUtil) {
         return new SysAdminWorkAreaPanel(business,userProcessContainer,dB4OUtil);
    }
    
}
