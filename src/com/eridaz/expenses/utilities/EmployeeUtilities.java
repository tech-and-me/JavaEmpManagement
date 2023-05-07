package com.eridaz.expenses.utilities;

import com.eridaz.expenses.domain.Employee;
import com.eridaz.expenses.domain.Employees;
import com.eridaz.expenses.exceptions.InvalidEmployeeIdException;
import com.eridaz.expenses.exceptions.NameTooShortException;

public class EmployeeUtilities {
    public boolean employeeExists(Employees employees, Employee employee){
        return employees.findBySurname(employee.getSureName()) != null;
    }
    public Integer validateEmployeeId(String inputId) throws InvalidEmployeeIdException{
        try {
            Integer id = Integer.valueOf(inputId);
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeIdException();
        }
    }

    public void validateEmployeeName(String firstName, String surname) throws NameTooShortException {
        Integer length = firstName.length() + surname.length();
        if(length < 6){
            throw new NameTooShortException();
        }
    }


}
