package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);

    public EmployeeEntity getEmployee(long id);

    public void setAvailability(Set<DayOfWeek> dayOfWeek, long employeeId);

    public List<EmployeeEntity> findAllEmployee();


    public List<EmployeeEntity> findEmployeeByAvailability(EmployeeRequestDTO employeeRequestDTO);


}
