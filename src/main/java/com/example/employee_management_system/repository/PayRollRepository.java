package com.example.employee_management_system.repository;

import com.example.employee_management_system.entity.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRollRepository extends JpaRepository<PayRoll,String> {
}
