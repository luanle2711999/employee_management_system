package com.example.employee_management_system.repository;

import com.example.employee_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT COUNT(u) > 0 FROM User u JOIN u.roles r WHERE r.name = :roleName")
    boolean existsByRoleName(@Param("roleName") String roleName);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    void deleteById(String id);
}
