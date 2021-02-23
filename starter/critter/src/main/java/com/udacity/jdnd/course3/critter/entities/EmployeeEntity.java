package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "employee_skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "employee_days_available")
    private Set<DayOfWeek> daysAvailable;

    @ManyToOne
    @JoinColumn(nullable = false, name = "employee_id")
    private ScheduleEntity schedule;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}