package com.finance.expense.tracker.expense_tracker.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finance.expense.tracker.expense_tracker.DTO.TransactionDTO;
import com.finance.expense.tracker.expense_tracker.Exceptions.TransactionNotFoundException;

@Service
public interface TransactionService {
    public List<TransactionDTO> getAllTransactions() throws TransactionNotFoundException;
    public TransactionDTO createTransaction(TransactionDTO TransactionDTO);
    public TransactionDTO deleteTransactionById(long id) throws TransactionNotFoundException;
    public TransactionDTO updateTransactionById(long id, TransactionDTO transactionDTO) throws TransactionNotFoundException;
    public HashMap<String, List<TransactionDTO>> getTransactionsByMonth() throws TransactionNotFoundException;
}
