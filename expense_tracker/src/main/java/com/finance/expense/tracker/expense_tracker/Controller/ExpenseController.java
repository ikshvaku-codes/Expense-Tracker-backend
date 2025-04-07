package com.finance.expense.tracker.expense_tracker.Controller;
import com.finance.expense.tracker.expense_tracker.DTO.APIResponse;
import com.finance.expense.tracker.expense_tracker.DTO.ExpenseDTO;
import com.finance.expense.tracker.expense_tracker.Service.ExpenseService;
import com.finance.expense.tracker.expense_tracker.entity.ExpenseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*") 
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
    public ResponseEntity<APIResponse<ExpenseDTO>> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        APIResponse<ExpenseDTO> response;

        try{
            ExpenseDTO expense = expenseService.addExpense(expenseDTO);
            response = APIResponse.<ExpenseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Expense added successfully")
                .data(expense)
                .metadata(Map.of("timestamp", LocalDateTime.now(), "requestId", expense.id())) // Example metadata
                .build();
            return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

        } catch (Exception e) {
            response = APIResponse.<ExpenseDTO>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error occurred while adding expense: " + e.getMessage())
                .data(expenseDTO)
                .metadata(Map.of("timestamp", LocalDateTime.now(), "errorType", e.getClass().getSimpleName())) 
                .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        }
        
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
