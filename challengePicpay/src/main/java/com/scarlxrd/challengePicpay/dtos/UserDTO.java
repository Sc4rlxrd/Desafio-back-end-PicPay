package com.scarlxrd.challengePicpay.dtos;

import com.scarlxrd.challengePicpay.domain.user.UserTyper;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserTyper userTyper) {
}