package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findAllByPetsId( long petId);

//    @Query("SELECT sch from ScheduleEntity sch INNER JOIN EmployeeEntity emp ON sch.id = emp.schedule.id where emp.id = :employeeId")
    List<ScheduleEntity> findAllByEmployeesId( long employeeId);

//    @Query("SELECT sch from ScheduleEntity sch INNER JOIN PetEntity pet ON sch.id = pet.schedule.id where pet.customer.id = :customerId")
//    List<ScheduleEntity> findScheduleForCustomer(@Param("customerId") long customerId);
}
