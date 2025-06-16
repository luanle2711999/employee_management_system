package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.entity.TokenLog;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.repository.TokenLogRepository;
import com.example.employee_management_system.service.TokenLogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenLogServiceImpl implements TokenLogService {
    TokenLogRepository tokenLogRepository;

    public TokenLog createTokenLog(TokenLog tokenLog) {
        return tokenLogRepository.save(tokenLog);
    }

    public TokenLog updateTokenLog(TokenLog tokenLog) {
        return tokenLogRepository.save(tokenLog);
    }

    public Boolean isTokenLogValid(String tokenLogId) {
        TokenLog existingTokenLog = tokenLogRepository.findById(tokenLogId)
                .orElse(null);
        if (existingTokenLog == null) {
            return false;
        }
        return existingTokenLog.getIsActive();
    }

    public void deleteTokenLog() {
        List<TokenLog> tokenLogs = tokenLogRepository.findTokenLogsByIsActive(false);
        List<String> tokenLogIds = tokenLogs.stream()
                .map(TokenLog::getTokenId)
                .toList();
        tokenLogRepository.deleteAllById(tokenLogIds);
    }
}
