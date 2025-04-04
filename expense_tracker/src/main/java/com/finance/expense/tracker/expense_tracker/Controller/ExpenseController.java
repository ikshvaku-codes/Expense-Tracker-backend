package com.finance.expense.tracker.expense_tracker.Controller;
import com.finance.expense.tracker.expense_tracker.Service.ExpenseService;
import com.finance.expense.tracker.expense_tracker.entity.ExpenseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<ExpenseEntity> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseEntity> getExpenseById(@PathVariable Long id) {
        ExpenseEntity expense = expenseService.getExpenseById(id);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ExpenseEntity> addExpense(@RequestBody ExpenseEntity expense) {
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable Long id, @RequestBody ExpenseEntity expense) {
        if (expenseService.getExpenseById(id) != null) {
            expenseService.updateExpense(id, expense);
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (expenseService.getExpenseById(id) != null) {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
