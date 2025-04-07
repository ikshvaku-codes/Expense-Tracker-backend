package com.finance.expense.tracker.expense_tracker.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.expense.tracker.expense_tracker.DTO.ExpenseDTO;
import com.finance.expense.tracker.expense_tracker.Repository.ExpenseRepository;
import com.finance.expense.tracker.expense_tracker.Service.ExpenseService;
import com.finance.expense.tracker.expense_tracker.entity.ExpenseEntity;
import java.time.LocalDate;
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

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {

        ExpenseEntity expense = ExpenseEntity.builder()
                .amount(expenseDTO.amount())
                .category(expenseDTO.category())
                .date(expenseDTO.date() == null ? LocalDate.now() : expenseDTO.date())
                .description(expenseDTO.description())
                .isRecurring(expenseDTO.isRecurring())
                .build();
        expense = expenseRepository.save(expense);
        expenseDTO = null;
        ExpenseDTO expenseReturnDTO = ExpenseDTO.builder()
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .date(expense.getDate())
                .description(expense.getDescription())
                .isRecurring(expense.getIsRecurring())
                .id(expense.getId())
                .build();
        return expenseReturnDTO;
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
