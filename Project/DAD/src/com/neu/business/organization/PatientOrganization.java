/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.organization;

import com.neu.business.role.DoctorRole;
import com.neu.business.role.PatientRole;
import com.neu.business.role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class PatientOrganization extends Organization{

    public PatientOrganization() {
        super(Organization.Type.Patient.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new PatientRole());
        return roles;
    }
     
}