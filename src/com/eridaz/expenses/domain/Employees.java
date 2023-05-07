package com.eridaz.expenses.domain;

import com.eridaz.expenses.domain.Employee;
import com.eridaz.expenses.exceptions.EmployeeNotFoundException;

import java.util.*;

public class Employees {
//    private HashSet<Employee> employees = new HashSet<>();
    private Map<Integer,Employee> employees = new HashMap<>();

    public void addEmployee(Employee employee){
//        employees.add(employee);  // use this for HashSet or ArrayList
        employees.put(employee.getId(),employee);  // use this for HashMap
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<Employee>(employees.values());
    }
    public void printEmployees(){
        List<Employee> employeeList = new ArrayList<>(employees.values());
        Collections.sort(employeeList);
        for(Employee e : employeeList){
                System.out.println(e);
            for (ExpenseClaim claim:e.getClaims().values()) {
                System.out.println(claim);
                claim.printExpenseItems();
                System.out.println("Total value of claim " + claim.getTotalAmount());
            }
        }
    }

    public Employee findBySurname(String surname){
        for(Employee e : employees.values()){
            if(e.getSureName().equals(surname)) {
                return e;
            }
        }
        return null;
    }

    public Employee findById(int id){
       return employees.get(id);
    }

    public boolean employeeExists(int id){
      return employees.containsKey(id);
    }

    public void addExpenseClaim(ExpenseClaim claim) throws EmployeeNotFoundException{
        int employeeId = claim.getEmployeeId();
        if(!employeeExists(employeeId)){
            throw new EmployeeNotFoundException();
        }
        for(Employee e: employees.values()){
            if(e.getId() == employeeId){
                e.getClaims().put(claim.getId(),claim);
                System.out.println("Claim for employee ID: " + employeeId + " has been added successfully.");
                System.out.println("Here below is the list of all the claims : ");
                for (ExpenseClaim c: e.getClaims().values()
                     ) {
                    System.out.println(c);
                }
            }
        }
    }
}
