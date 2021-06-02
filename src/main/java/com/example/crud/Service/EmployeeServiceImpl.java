package com.example.crud.Service;

import com.example.crud.Model.Employee;
import com.example.crud.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }
    public void saveEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }
}
