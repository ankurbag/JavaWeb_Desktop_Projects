/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.business.employee;

import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class EmployeeDirectory {

    private ArrayList<Employee> employeeList;

    public EmployeeDirectory() {
        employeeList = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee createEmployee(String name) {
        Employee employee = new Employee();
        employee.setName(name);
        employeeList.add(employee);
        return employee;
    }

    public ArrayList<Employee> searchEmployee(String speciality) {
        ArrayList<Employee> tempList = new ArrayList<Employee>();
        tempList.clear();

        for (Employee e : employeeList) {
            if (null != e.getSpeciality()) {
                if (e.getSpeciality().toUpperCase().contains(speciality.toUpperCase())) {
                    tempList.add(e);
                }
            }
        }
        if (speciality.equalsIgnoreCase("Specialities")) {
            tempList = employeeList;
        }
        return tempList;
    }

    public ArrayList<Employee> searchEmployee(String speciality, boolean availability, String address) {
        ArrayList<Employee> tempList = new ArrayList<Employee>();
        tempList.clear();
        for (Employee e : employeeList) {
            if (e.getSpeciality().contains(speciality)) {
                if (e.getAddress().contains(address)) {
                    tempList.add(e);
                }
            } else {
                tempList = employeeList;
            }
        }
        return tempList;
    }
    
    public void removeEmployee(Employee e){
        this.employeeList.remove(e);
    }
}
