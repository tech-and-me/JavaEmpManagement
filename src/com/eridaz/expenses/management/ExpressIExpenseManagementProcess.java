package com.eridaz.expenses.management;

import com.eridaz.expenses.domain.Employee;
import com.eridaz.expenses.domain.ExpenseClaim;

public class ExpressIExpenseManagementProcess implements IExpenseManagementProcess {
    private ExpenseClaim claim;

    @Override
    public int registerExpenseClaim(ExpenseClaim claim) {
        this.claim = claim;
        return -1;
    }

    @Override
    public boolean approveClaim(int id, Employee employee) {
        return (claim.getTotalAmount()<50);
    }
}
