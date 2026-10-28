package com.phokaia.events;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionCompleted(
    
    String transactionId,
    String fromAccountCode,
    String toAccountCode,
    BigDecimal amount,
    Instant completedAt
) 
{}

