package com.eridaz.expenses;

import com.eridaz.expenses.domain.Employee;
import com.eridaz.expenses.domain.Employees;
import com.eridaz.expenses.domain.ExpenseClaim;
import com.eridaz.expenses.exceptions.EmployeeNotFoundException;
import com.eridaz.expenses.management.IExpenseManagementProcess;
import com.eridaz.expenses.management.ExpressIExpenseManagementProcess;
import com.eridaz.expenses.management.RegularIExpenseManagementProcess;
import com.eridaz.expenses.ui.UIFunctions;
import com.eridaz.expenses.utilities.ExpenseAnalysisImpl;
import com.eridaz.expenses.utilities.IExpenseAnalysis;
import com.eridaz.expenses.utilities.ExpenseAnalysisTempImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class EMS_Main_UI {
    public static void main(String[] args) {
        Employees employees = new Employees();
        Scanner scanner = new Scanner(System.in);
        UIFunctions uiFunctions = new UIFunctions();

        IExpenseManagementProcess expressEMP = new ExpressIExpenseManagementProcess();
        IExpenseManagementProcess regularEMP = new RegularIExpenseManagementProcess();

        boolean readyToExit = false;

        while(!readyToExit){
            System.out.println("-------------------------");
            System.out.println("Expense Management System");
            System.out.println("-------------------------");
            System.out.println("e - register new employee");
            System.out.println("c - register new claim");
            System.out.println("p - print all employees");
            System.out.println("r1 - outstanding expense claim");
            System.out.println("r2 - paid expenses claim");
            System.out.println("r3 - expenses claim above a specified amount");
            System.out.println("x - exit");

            String option = scanner.nextLine();

//            IExpenseAnalysis expenseAnalysis = new ExpenseAnalysisTempImpl();
            IExpenseAnalysis expenseAnalysis = new ExpenseAnalysisImpl(employees);

            switch(option){
                case "e": // register new employee
                    Employee e = uiFunctions.registerNewEmployee();
                    employees.addEmployee(e);
                    break;
                case "c": // register new claim
                    ExpenseClaim claim = uiFunctions.registerNewExpenseClaim();
                    try {
                        employees.addExpenseClaim(claim);
                        //add claim to express queue
                        expressEMP.registerExpenseClaim(claim);
                        //add claim to regular claim queue and return reference ID for approval
                        int id = regularEMP.registerExpenseClaim(claim);
                        System.out.println("The claim has been registered with ID " + id);
                    } catch (EmployeeNotFoundException ex) {
                        System.out.println("There was no employee with ID " + claim.getEmployeeId());
                    }
                    break;
                case "p": // print all employees
                    employees.printEmployees();
                    break;
                case "a": // approve claim
                    //get ID of the claim
                    System.out.println("Enter the claim Id");
                    int claimId = scanner.nextInt();
                    scanner.nextLine();

                    //get the employee ID
                    System.out.println("Enter the employee Id");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();

                    //find the employee
                    Employee foundEmployee = employees.findById(employeeId);

                    //call the relevant method
                    System.out.println("Enter r for regular, or e for express");
                    String claimType = scanner.nextLine();

                    IExpenseManagementProcess requestProcess;
                    requestProcess = claimType.equals("r") ? regularEMP : expressEMP;

                    boolean result = requestProcess.approveClaim(claimId,foundEmployee);
                    System.out.println("The result was " + result);

                    break;
                case "r1":
                    expenseAnalysis.printOutstandingClaims();
                    break;
                case "r2":
                    System.out.println("Enter date from ");
                    String dateFrom = scanner.nextLine();

                    System.out.println("Enter date to ");
                    String dateTo = scanner.nextLine();

                    expenseAnalysis.printPaidClaims(LocalDate.parse(dateFrom),LocalDate.parse(dateTo));
                    break;
                case "r3":
                    System.out.println("Enter min amount of expenses to display : ");
                    Double minAmount = scanner.nextDouble();
                    scanner.nextLine();

                    expenseAnalysis.printClaimsOverAmount(minAmount);
                    break;
                case "x": // exit
                    readyToExit = true;
                    break;

                default:
                    System.out.println("Option not valid - try again.");
            }
        }
    }
}
