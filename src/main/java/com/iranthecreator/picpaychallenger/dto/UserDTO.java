package com.iranthecreator.picpaychallenger.dto;

import com.iranthecreator.picpaychallenger.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO (String firstName, String lastName, String document, BigDecimal balance, String email, UserType userType, String password) {
}
