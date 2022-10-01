package com.manish.springbootbackend.controller;


import com.manish.springbootbackend.exception.ResourceNotFoundException;
import com.manish.springbootbackend.model.Employee;
import com.manish.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

     @Autowired
     private EmployeeRepository employeeRepository;

     @GetMapping("/employees")
     public List<Employee> getAllEmployees() {
         return employeeRepository.findAll();
     }


     @PostMapping("/employees")
     public Employee createEmployee(@RequestBody Employee employee) {
         return employeeRepository.save(employee);
     }


     @GetMapping("/employees/{id}")
     public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with id" + id));
            return ResponseEntity.ok(employee);
     }

     @PutMapping("/employees/{id}")
     public ResponseEntity<Employee> updateEmployee(@PathVariable  Long id , @RequestBody Employee employeeDetails)
     {
         Employee employee = employeeRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with id" + id));

         employee.setFirstName(employeeDetails.getFirstName());
         employee.setLastName(employeeDetails.getLastName());
         employee.setEmailId(employeeDetails.getEmailId());

         Employee updatedEmployee = employeeRepository.save(employee);
         return ResponseEntity.ok(updatedEmployee);
     }

 }


