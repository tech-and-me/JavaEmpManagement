package com.eridaz.expenses.ui;

import com.eridaz.expenses.domain.*;
import com.eridaz.expenses.exceptions.InvalidEmployeeIdException;
import com.eridaz.expenses.exceptions.NameTooShortException;
import com.eridaz.expenses.utilities.EmployeeUtilities;

import java.time.LocalDate;
import java.util.Scanner;

public class UIFunctions {
    public Employee registerNewEmployee() {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();

        boolean idIsValid = false;
        while(!idIsValid){
            System.out.println("Enter the id");
            String inputId = scanner.nextLine();
            try {
                Integer id = employeeUtilities.validateEmployeeId(inputId);
                employee.setId(id);
                idIsValid = true;
            } catch (InvalidEmployeeIdException e) {
                System.out.println(" The id entered was invalid - try again.");
            }
        }

        System.out.println("Enter the title");
        String title = scanner.nextLine();
        employee.setTitle(title);

        boolean nameIsValid = false;
        while(!nameIsValid){
            System.out.println("Enter the firstname");
            String firstName = scanner.nextLine();
            employee.setFirstName(firstName);
            System.out.println("Enter the surname");
            String surname = scanner.nextLine();
            employee.setSureName(surname);
            try {
                employeeUtilities.validateEmployeeName(firstName,surname);
                nameIsValid = true;
            } catch (NameTooShortException e) {
                System.out.println("The name you entered was not valid.");
            }
        }

        System.out.println("Enter the job title");
        String jobTitle = scanner.nextLine();
        employee.setJobTitle(jobTitle);

        boolean departmentIsValid = false;
        while(!departmentIsValid){
            System.out.println("Enter department : ");
            String department = scanner.nextLine();
            try {
                Department d = Department.valueOf(department.toUpperCase());
                employee.setDepartment(d);
                departmentIsValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter the correct department. try again.");
            }
        }

        System.out.println("Is this a member f staff ? Y/N ");
        String isAStaffMember = scanner.nextLine();
        if(isAStaffMember.toUpperCase().equals("Y")){
            StaffEmployee staffEmployee = new StaffEmployee(employee);

            System.out.println("Enter the username");
            String username = scanner.nextLine();
            staffEmployee.setUsername(username);

            System.out.println("Enter the password");
            String password = scanner.nextLine();
            staffEmployee.setPassword(password);

            return staffEmployee;
        }else{
            return employee;
        }

    }
    public ExpenseClaim registerNewExpenseClaim(){
        Scanner scanner = new Scanner(System.in);
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();

        System.out.println("Enter the claim Id");
        int claimId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the employee Id");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        LocalDate dateOfClaim = LocalDate.now();

        ExpenseClaim claim = new ExpenseClaim(claimId, employeeId,dateOfClaim);

        String moreItems = "Y";
        while (moreItems.equals("Y")){
            System.out.println("Enter the expense item ID");
            int eiId = scanner.nextInt();
            scanner.nextLine();

            boolean expenseTypeIsValid = false;
            ExpenseType eiType = null;

            while(!expenseTypeIsValid){
                System.out.println("Enter the expenses type");
                String expenseType = scanner.nextLine();
                try{
                    eiType = ExpenseType.valueOf(expenseType.toUpperCase()); // convert String to enum
                    expenseTypeIsValid = true;
                }catch(IllegalArgumentException e){
                    System.out.println("The expense type you entered was not valid - try again.");
                }
            }
            System.out.println("Enter the description");
            String description = scanner.nextLine();

            System.out.println("Enter the amount of expense item");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            ExpenseItem ei = new ExpenseItem(eiId, claimId, eiType, description, amount);
            claim.addExpensesItem(ei);

            System.out.println("Any more expense item to add ? Y/N : ");
            moreItems = scanner.nextLine().toUpperCase();

        }
        return claim;
    }
}
