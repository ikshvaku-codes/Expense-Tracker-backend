package com.finance.expense.tracker.expense_tracker.DTO;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ExpenseDTO(
    String description,
    Double amount,
    LocalDate date,
    String category,
    long id,
    Boolean isRecurring
)  {

}
