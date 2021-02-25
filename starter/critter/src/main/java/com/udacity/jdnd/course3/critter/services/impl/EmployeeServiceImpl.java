package com.udacity.jdnd.course3.critter.services.impl;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save Employee records
     *
     * @param employeeEntity form
     * @return saved employee details
     */
    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    /**
     * Get single employee details
     *
     * @param id of the employee
     * @return employee details or empty employee details
     */
    @Override
    public EmployeeEntity getEmployee(long id) {
        return employeeRepository.findById(id).orElse(new EmployeeEntity());
    }

    /**
     * Set the available days for an employee
     *
     * @param dayOfWeek  the employee is available
     * @param employeeId to identify the employee
     */
    @Override
    public void setAvailability(Set<DayOfWeek> dayOfWeek, long employeeId) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDaysAvailable(dayOfWeek);
        employeeEntity.setId(employeeId);
        employeeRepository.save(employeeEntity);

    }

    @Override
    public List<EmployeeEntity> findAllEmployee() {
        return employeeRepository.findAll();
    }


    //convert localDate to days
    //find by days and skill
    @Override
    public List<EmployeeEntity> findEmployeeByAvailability(EmployeeRequestDTO employeeRequestDTO) {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAllBySkillsInAndDaysAvailableContains(employeeRequestDTO.getSkills(), employeeRequestDTO.getDate().getDayOfWeek());
        List<EmployeeEntity> employeeThatMeetRequirement = new ArrayList<>();
        employeeEntities.forEach(employeeEntity -> {
            if (employeeEntity.getSkills().containsAll(employeeRequestDTO.getSkills()))
                employeeThatMeetRequirement.add(employeeEntity);
        });
        return employeeThatMeetRequirement;

    }


}
