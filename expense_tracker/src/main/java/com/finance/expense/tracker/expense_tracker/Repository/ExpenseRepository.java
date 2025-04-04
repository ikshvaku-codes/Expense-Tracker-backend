package com.finance.expense.tracker.expense_tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.finance.expense.tracker.expense_tracker.entity.ExpenseEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find expenses by category or date range


}
