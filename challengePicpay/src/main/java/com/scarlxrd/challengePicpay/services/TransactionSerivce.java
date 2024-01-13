package com.scarlxrd.challengePicpay.services;

import com.scarlxrd.challengePicpay.repositories.TransctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionSerivce {
    @Autowired
    private UserService userService;
    @Autowired
    private TransctionRepository repository;
}
