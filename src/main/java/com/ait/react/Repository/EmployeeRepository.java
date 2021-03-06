package com.ait.react.Repository;

import com.ait.react.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String string);

    List<Employee> findByNameLike(String string);

    Optional<Employee> findByEmail(String email);
}
