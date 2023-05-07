package com.eridaz.expenses;

import com.eridaz.expenses.domain.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setTitle("Miss");
        emp1.setFirstName("Phary");
        emp1.setSureName(("Phal"));

        System.out.println(emp1.getMailingName());
        System.out.println(emp1.getMailingName(true));
        System.out.println(emp1.getMailingName(false));

        Employee emp2 = new Employee(2,"Manager");
        emp2.setTitle("Dr");
        emp2.setFirstName("Dora");
        emp2.setSureName("Yunaree");

        Employee emp3 = new Employee(2,"Manager");
        emp3.setTitle("Dr");
        emp3.setFirstName("Dora");
        emp3.setSureName("Yunaree");
        emp3.setDepartment(Department.FINANCE);


        // Comparing objects
        System.out.println("Compare emp2 with emp3 : " + emp2.equals(emp3));
        System.out.println("Compare emp2 with emp3 : " + emp2.equals(emp3));

        Employees employees = new Employees();
        employees.addEmployee(emp1);
        employees.addEmployee(emp2);

        employees.printEmployees();

        Employee foundEmployee = employees.findBySurname("Phal");
        System.out.println("Found " + foundEmployee.getMailingName());
        Employee foundEmployee2 = employees.findBySurname("Yunam");
        System.out.println("Didnt find " + (foundEmployee2 == null));

        ExpenseClaim expenseClaim = new ExpenseClaim(24,642, LocalDate.now());
        System.out.println(expenseClaim.getEmployeeId());
        expenseClaim.setPaid(true);
        System.out.println(expenseClaim.getPaid());
        expenseClaim.setApproved(true);
        expenseClaim.setPaid(true);
        System.out.println(expenseClaim.getPaid());

        ExpenseItem expenseItem = new ExpenseItem(24,102,ExpenseType.ACCOMODATION,"The Grand Hotel",69.99);
        System.out.println(expenseItem.getDescription());

        System.out.println(emp1.toString());
        System.out.println(emp3.toString());
        employees.printEmployees();

    }
}
