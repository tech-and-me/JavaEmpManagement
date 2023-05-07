package com.eridaz.expenses.management;

import com.eridaz.expenses.domain.Employee;
import com.eridaz.expenses.domain.ExpenseClaim;
import com.eridaz.expenses.domain.StaffEmployee;

import java.util.ArrayList;
import java.util.List;

public class RegularIExpenseManagementProcess implements IExpenseManagementProcess {
    List<ExpenseClaim> claims = new ArrayList<>();

    @Override
    public int registerExpenseClaim(ExpenseClaim claim) {
        claims.add(claim);
        return claims.size()-1; // This represent Id of the claim
    }

    @Override
    public boolean approveClaim(int id, Employee employee) {
        ExpenseClaim claim = claims.get(id);
        if(claim.getTotalAmount() < 100){
            return true;
        }
        if(employee instanceof StaffEmployee){
            return true;
        }
        return false;
    }
}
