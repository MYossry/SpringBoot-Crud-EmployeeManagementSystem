package com.example.crud.Service;

import com.example.crud.Model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    Page<Employee> getAllEmployeePaginated(int pageNo, int pageSize,String sortField, String sortDirection);
}
