package com.ekstraklasa.football.repo;

import com.ekstraklasa.football.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
