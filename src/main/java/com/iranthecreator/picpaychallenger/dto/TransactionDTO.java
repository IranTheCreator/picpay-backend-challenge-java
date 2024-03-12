package com.iranthecreator.picpaychallenger.dto;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal value) {
}
