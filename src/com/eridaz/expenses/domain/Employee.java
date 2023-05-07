package com.eridaz.expenses.domain;
import com.eridaz.expenses.domain.Department;

import java.util.*;


public class Employee implements Comparable<Employee>{
    private int id;
    private String title;
    private String firstName;
    private String sureName;
    private String jobTitle;
    private Department department;
//    private ArrayList<ExpenseClaim> claims = new ArrayList<>();
    private Map<Integer,ExpenseClaim> claims = new HashMap<>();

    public Employee(){}

    public Employee(int id, String title, String firstName, String sureName, String jobTitle, Department department) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.sureName = sureName;
        this.jobTitle = jobTitle;
        this.department = department;
    }
    public Employee(int id, String jobTitle){
        this.id = id;
        this.jobTitle = jobTitle;
    }
    public String getMailingName(){
        return title + " " + firstName + " " + sureName;
    }
    public String getMailingName(boolean firstInitialOnly){
        if(firstInitialOnly){
            return title + " " + firstName.substring(0,1) + " " + sureName;
        }else{
            return title + " " + sureName;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if(firstName.length()<2){
            System.out.println("Error - first name must be at least 2 characters.");
        }else{
            this.firstName = firstName;
        }
    }
    public String getSureName() {
        return sureName;
    }
    public void setSureName(String sureName) {
        this.sureName = sureName;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Map<Integer,ExpenseClaim> getClaims() {
        return claims;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(title, employee.title) && Objects.equals(firstName, employee.firstName) && Objects.equals(sureName, employee.sureName) && Objects.equals(jobTitle, employee.jobTitle) && department == employee.department && Objects.equals(claims, employee.claims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, firstName, sureName, jobTitle, department, claims);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sureName='" + sureName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.sureName.compareTo(o.getSureName());
    }


}
