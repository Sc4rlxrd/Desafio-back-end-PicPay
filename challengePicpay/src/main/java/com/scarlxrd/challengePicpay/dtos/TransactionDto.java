package com.scarlxrd.challengePicpay.dtos;

import java.math.BigDecimal;

public record TransactionDto(BigDecimal value, Long senderId, Long receiverId) {
}
