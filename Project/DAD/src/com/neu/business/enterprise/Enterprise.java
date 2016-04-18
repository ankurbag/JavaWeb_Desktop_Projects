/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.enterprise;

import com.neu.business.organization.Organization;
import com.neu.business.organization.OrganizationDirectory;

/**
 *
 * @author Rajat
 */
public abstract  class Enterprise extends Organization {

    private EnterpriseType enterpriseType;
    private OrganizationDirectory organizationDirectory;

    Enterprise(String name, EnterpriseType enterpriseType) {

        super(name);
        this.enterpriseType = enterpriseType;
        organizationDirectory = new OrganizationDirectory();

    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }   
    

    public enum EnterpriseType {

        Hospital("Hospital");

        private String value;

        private EnterpriseType(String value) {

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

}
