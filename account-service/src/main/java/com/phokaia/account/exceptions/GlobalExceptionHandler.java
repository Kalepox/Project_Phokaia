package com.phokaia.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.Version;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404 Not Found
    @ExceptionHandler({CustomerNotFoundException.class, AccountNotFoundException.class})
    public ProblemDetail handleNotFound(RuntimeException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource Not Found");
        return problem;
    }

    //409 Already Exists
    @ExceptionHandler({AccountAlreadyExistsException.class})
    public ProblemDetail handleAlreadyExists(RuntimeException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Resource Already Exists");
        return problem;
    }

    //422 Transaction Rejected
    @ExceptionHandler({InsufficientFundsException.class , CurrencyMismatchException.class})
    public ProblemDetail handleRejected(RuntimeException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());
        problem.setTitle("Transaction Rejected");
        return problem;
    }

    //400 Invlaid Req
    @ExceptionHandler({IllegalArgumentException.class})
    public ProblemDetail invalidHandle(RuntimeException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Invaild Request");
        return problem;
    }

    
    @ExceptionHandler({ObjectOptimisticLockingFailureException.class})
    public ProblemDetail invalidVersion(RuntimeException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Account was modified by another request");
        problem.setTitle("Concurrent Update");
        return problem;
    }

}
