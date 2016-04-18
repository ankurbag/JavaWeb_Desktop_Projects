/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business;

import com.neu.business.network.Network;
import com.neu.business.organization.Organization;
import com.neu.business.role.Role;
import com.neu.business.role.SystemAdminRole;
import java.util.ArrayList;

/**
 *
 * @author Rajat
 */
public class EcoSystem extends Organization {

    private static EcoSystem business;

    private ArrayList<Network> networkList;

    public static EcoSystem getInstance() {

        if (business == null) {

            business = new EcoSystem();
        }
        return business;

    }
    
    private EcoSystem(){
    
        super(null);
        networkList = new ArrayList<>();   
    
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }
    
    public Network createNetwork(){
    
        Network network = new Network();
        networkList.add(network);
        return  network;
    
    
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SystemAdminRole());
        return  roleList;
    }

}
