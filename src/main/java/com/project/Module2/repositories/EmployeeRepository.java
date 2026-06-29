package com.project.Module2.repositories;

import com.project.Module2.enitities.EmployeeEnitity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEnitity , Long> {
}
