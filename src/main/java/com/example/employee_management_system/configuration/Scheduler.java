package com.example.employee_management_system.configuration;

import com.example.employee_management_system.service.TokenLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final TokenLogService tokenLogService;

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleTask() {
        tokenLogService.deleteTokenLog();
    }
}
