package com.finance.expense.tracker.expense_tracker.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.expense.tracker.expense_tracker.Repository.ExpenseRepository;
import com.finance.expense.tracker.expense_tracker.Service.ExpenseService;
import com.finance.expense.tracker.expense_tracker.entity.ExpenseEntity;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<ExpenseEntity> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public ExpenseEntity getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public void addExpense(ExpenseEntity expense) {
        expenseRepository.save(expense);
    }

    public void updateExpense(Long id, ExpenseEntity expense) {
        if (expenseRepository.existsById(id)) {
            expense.setId(id);
            expenseRepository.save(expense);
        }
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }


}
