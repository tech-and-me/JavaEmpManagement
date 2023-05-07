package com.eridaz.expenses.utilities;

import java.time.LocalDate;

public class ExpenseAnalysisTempImpl implements IExpenseAnalysis {

    @Override
    public void printOutstandingClaims() {
        System.out.println("This feature is not currently available");
    }

    @Override
    public void printPaidClaims(LocalDate from, LocalDate to) {
        System.out.println("This feature is not currently available");

    }

    @Override
    public void printClaimsOverAmount(Double Amount) {
        System.out.println("This feature is not currently available");
    }
}
