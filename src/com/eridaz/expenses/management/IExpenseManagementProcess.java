package com.eridaz.expenses.management;

import com.eridaz.expenses.domain.Employee;
import com.eridaz.expenses.domain.ExpenseClaim;

public interface IExpenseManagementProcess {
    public int registerExpenseClaim(ExpenseClaim claim);
    public boolean approveClaim(int id, Employee employee);
}
