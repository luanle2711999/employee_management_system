package com.example.employee_management_system.repository;

import com.example.employee_management_system.entity.TokenLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenLogRepository extends JpaRepository<TokenLog, String> {
    List<TokenLog> findTokenLogsByIsActive(Boolean isActice);
}
