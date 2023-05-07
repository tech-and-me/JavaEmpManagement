package com.eridaz.expenses.utilities;

import java.time.LocalDate;

public interface IExpenseAnalysis {
    public void printOutstandingClaims();
    public void printPaidClaims(LocalDate from, LocalDate to);
    public void printClaimsOverAmount(Double Amount);
}
