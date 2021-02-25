package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Create schedule. Get corresponding pet entities from the pet ids and also employee entities from employee ids to be save into the schedule
     *
     * @param scheduleEntity to be saved
     * @param petIds         list of pet ids
     * @param employeeIds    list of employee ids
     * @return saved schedule
     */
    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity, List<Long> petIds, List<Long> employeeIds) {
        List<PetEntity> petEntities = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            petIds.forEach(petId -> {
                Optional<PetEntity> petEntityOptional = petRepository.findById(petId);
                petEntityOptional.ifPresent(petEntities::add);
            });
        }
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        if (employeeIds != null && !employeeIds.isEmpty()) {
            employeeIds.forEach(employeeId -> {
                Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(employeeId);
                employeeEntityOptional.ifPresent(employeeEntities::add);
            });
        }


        scheduleEntity.setPets(petEntities);
        scheduleEntity.setEmployees(employeeEntities);

        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getScheduleForPet(long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<ScheduleEntity> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<ScheduleEntity> getScheduleForCustomer(long customerId) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerId);

        CustomerEntity customerEntity = customerEntityOptional.orElseThrow(EntityNotFoundException::new);
        List<PetEntity> petEntityList = customerEntity.getPets();
        List<ScheduleEntity> scheduleEntities = new ArrayList<>();

        petEntityList.forEach(petEntity -> {
            scheduleEntities.addAll(scheduleRepository.findAllByPetsId(petEntity.getId()));
        });
        return scheduleEntities;
    }
}
