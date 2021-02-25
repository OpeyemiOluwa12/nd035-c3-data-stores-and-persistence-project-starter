package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;

import java.util.List;

public interface CustomerService {

    public CustomerEntity saveCustomer(CustomerEntity customerEntity, List<Long> petIds);

    public List<CustomerEntity> getAllCustomers();


    public CustomerEntity getOwnerByPet(long petId);

    public CustomerEntity getCustomer(long customerId);
}
