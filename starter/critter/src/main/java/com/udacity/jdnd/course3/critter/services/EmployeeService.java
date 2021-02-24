package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save Employee records
     *
     * @param employeeEntity form
     * @return saved employee details
     */
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    /**
     * Get single employee details
     *
     * @param id of the employee
     * @return employee details or empty employee details
     */
    public EmployeeEntity getEmployee(long id) {
        return employeeRepository.findById(id).orElse(new EmployeeEntity());
    }

    /**
     * Set the available days for an employee
     * @param dayOfWeek the employee is available
     * @param employeeId to identify the employee
     */
    public void setAvailability(Set<DayOfWeek> dayOfWeek, long employeeId) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDaysAvailable(dayOfWeek);
        employeeEntity.setId(employeeId);
        employeeRepository.save(employeeEntity);

    }

    public List<EmployeeEntity> findAllEmployee(){
        return employeeRepository.findAll();
    }


}
