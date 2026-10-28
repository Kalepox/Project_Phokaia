package com.phokaia.events;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionFailed(

    String transactionId,
    String fromAccountCode,
    String toAccountCode,
    BigDecimal amount,
    String reason,
    Instant failedAt
) 
{}


