package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getScheduleForPet(long petId) {
        return scheduleRepository.findScheduleForPet(petId);
    }

    public List<ScheduleEntity> getScheduleForEmployee(long petId) {
        return scheduleRepository.findScheduleForEmployee(petId);
    }

    public List<ScheduleEntity> getScheduleForCustomer(long petId) {
        return scheduleRepository.findScheduleForCustomer(petId);
    }
}
