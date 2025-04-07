package com.finance.expense.tracker.expense_tracker.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.expense.tracker.expense_tracker.DTO.APIResponse;
import com.finance.expense.tracker.expense_tracker.DTO.TransactionDTO;
import com.finance.expense.tracker.expense_tracker.Exceptions.TransactionNotFoundException;
import com.finance.expense.tracker.expense_tracker.Service.TransactionService;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<APIResponse<List<TransactionDTO>>> getAllTransactions() {
        APIResponse<List<TransactionDTO>> response;
        try {
            List<TransactionDTO> transactionDTOs = transactionService.getAllTransactions();
            response = APIResponse.<List<TransactionDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Transactions fetched successfully")
                .data(transactionDTOs)
                .metadata(Map.of("timestamp", LocalDateTime.now(), "count", transactionDTOs.size())) // Example metadata
                .build();
            return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        } catch(TransactionNotFoundException e){
            response = APIResponse.<List<TransactionDTO>>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("No transactions found: " + e.getMessage())
                .data(null)
                .metadata(Map.of("timestamp", LocalDateTime.now()))
                .build();
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        } catch (Exception e) {
            response = APIResponse.<List<TransactionDTO>>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error occurred while fetching transactions: " + e.getMessage())
                .data(null)
                .metadata(Map.of("timestamp", LocalDateTime.now()))
                .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        }
    }

    @PostMapping
    public ResponseEntity<APIResponse<TransactionDTO>> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        APIResponse<TransactionDTO> response;
        try  {
            transactionDTO = transactionService.createTransaction(transactionDTO);
            response = APIResponse.<TransactionDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Transactions fetched successfully")
                .data(transactionDTO)
                .metadata(Map.of("timestamp", LocalDateTime.now(), "request_id", transactionDTO.id())) // Example metadata
                .build();
            return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

        } catch (Exception e) {
            response = APIResponse.<TransactionDTO>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error occurred while fetching transactions: " + e.getMessage())
                .data(null)
                .metadata(Map.of("timestamp", LocalDateTime.now()))
                .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        }
       
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<TransactionDTO>> deleteTransaction(@PathVariable long id) {
        APIResponse<TransactionDTO> response;
        try  {
            TransactionDTO transactionDTO = transactionService.deleteTransactionById(id);
            response = APIResponse.<TransactionDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Transactions fetched successfully")
                .data(transactionDTO)
                .metadata(Map.of("timestamp", LocalDateTime.now(), "request_id", transactionDTO.id())) 
                .build();
            return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

        } catch(TransactionNotFoundException e){
            response = APIResponse.<TransactionDTO>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("No transactions found: " + e.getMessage())
                .data(null)
                .metadata(Map.of("timestamp", LocalDateTime.now()))
                .build();
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        } catch (Exception e) {
            response = APIResponse.<TransactionDTO>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error occurred while fetching transactions: " + e.getMessage())
                .data(null)
                .metadata(Map.of("timestamp", LocalDateTime.now()))
                .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
        }
    }
}
