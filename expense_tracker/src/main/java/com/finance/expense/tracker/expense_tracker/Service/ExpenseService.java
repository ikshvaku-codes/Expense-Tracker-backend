package com.finance.expense.tracker.expense_tracker.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finance.expense.tracker.expense_tracker.DTO.ExpenseDTO;
import com.finance.expense.tracker.expense_tracker.entity.ExpenseEntity;

@Service
public interface ExpenseService {
    List<ExpenseEntity> getAllExpenses();

    ExpenseEntity getExpenseById(Long id);

    ExpenseDTO addExpense(ExpenseDTO expense);

    void updateExpense(Long id, ExpenseEntity expense);

    void deleteExpense(Long id);
}
