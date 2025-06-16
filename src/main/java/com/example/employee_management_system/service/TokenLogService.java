package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.TokenLog;

public interface TokenLogService {
    TokenLog createTokenLog(TokenLog tokenLog);

    TokenLog updateTokenLog(TokenLog tokenLog);

    Boolean isTokenLogValid(String tokenLogId);

    void deleteTokenLog();
}
