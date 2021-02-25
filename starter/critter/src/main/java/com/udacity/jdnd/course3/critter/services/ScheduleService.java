package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;

import java.util.List;


public interface ScheduleService {

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity, List<Long> petIds, List<Long> employeeIds);

    public List<ScheduleEntity> getAllSchedules();

    public List<ScheduleEntity> getScheduleForPet(long petId);

    public List<ScheduleEntity> getScheduleForEmployee(long employeeId);

    public List<ScheduleEntity> getScheduleForCustomer(long customerId);
}
