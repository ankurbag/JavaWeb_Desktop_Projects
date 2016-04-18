/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.userAccount;

import com.neu.business.employee.Employee;
import com.neu.business.patients.Patients;
import com.neu.business.role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }
    
    public UserAccount authenticateUser(String username, String password){
        for (UserAccount ua : userAccountList)
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)){
                return ua;
            }
        return null;
    }
    
    public UserAccount createUserAccount(String username, String password, Employee employee, Role role){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setEmployee(employee);
        userAccount.setRole(role);
        userAccountList.add(userAccount);
        return userAccount;
    }
    
    public UserAccount createUserAccount(String username, String password, Patients patients, Role role){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setPatients(patients);
        userAccount.setRole(role);
        userAccountList.add(userAccount);
        return userAccount;
    }
    
    public UserAccount searchUserAccount(Employee employee) {
        for (UserAccount ua : userAccountList) {
            if (ua.getEmployee() == employee) {
                return ua;
            }
        }
        return null;
    }
    public UserAccount searchUserAccount(Patients patient){
        for (UserAccount ua : userAccountList){
            if (ua.getPatients()== patient){
                return ua;
            }
        }
        
        return null;
    }
    public boolean checkIfUsernameIsUnique(String username){
        for (UserAccount ua : userAccountList){
            if (ua.getUsername().equals(username))
                return false;
        }
        return true;
    }
}
