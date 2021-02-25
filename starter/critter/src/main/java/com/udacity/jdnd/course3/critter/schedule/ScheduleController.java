package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.utils.Commons;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final Commons commons;

    public ScheduleController(ScheduleService scheduleService, Commons commons) {
        this.scheduleService = scheduleService;
        this.commons = commons;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleService.createSchedule(((ScheduleEntity) commons.convertObjectToObject(scheduleDTO, new ScheduleEntity())), scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds());
        return convertScheduleEntityToDTO(scheduleEntity);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertScheduleEntityListToDTOList(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertScheduleEntityListToDTOList(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertScheduleEntityListToDTOList(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return convertScheduleEntityListToDTOList(scheduleService.getScheduleForCustomer(customerId));
    }

    public List<ScheduleDTO> convertScheduleEntityListToDTOList(List<ScheduleEntity> scheduleEntities) {
        return scheduleEntities.stream().map(this::convertScheduleEntityToDTO).collect(Collectors.toList());
    }

    public ScheduleDTO convertScheduleEntityToDTO(ScheduleEntity scheduleEntity) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        BeanUtils.copyProperties(scheduleEntity, scheduleDTO);

        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();
        if (scheduleEntity.getEmployees() != null && !scheduleEntity.getEmployees().isEmpty()) {
            scheduleEntity.getEmployees().forEach(employeeEntity -> {
                employeeIds.add(employeeEntity.getId());
            });
        }
        if (scheduleEntity.getPets() != null && !scheduleEntity.getPets().isEmpty()) {
            scheduleEntity.getPets().forEach(petEntity -> {
                petIds.add(petEntity.getId());
            });
        }

        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }
}
