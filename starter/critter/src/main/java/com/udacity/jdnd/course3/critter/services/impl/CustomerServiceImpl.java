package com.udacity.jdnd.course3.critter.services.impl;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    /**
     * Save customers details
     *
     * @param customerEntity form
     * @param petIds
     * @return saved customers details
     */
    @Override
    public CustomerEntity saveCustomer(CustomerEntity customerEntity, List<Long> petIds) {
        List<PetEntity> petsToAssignedToCustomer = new ArrayList<>();
        if (petIds != null && petIds.size() > 0) {
            petIds.forEach(petId -> {
                Optional<PetEntity> retrievedPet = petRepository.findById(petId);
                if (retrievedPet.isPresent() && retrievedPet.get().getCustomer() == null) {
                    retrievedPet.get().setCustomer(customerEntity);
                    petsToAssignedToCustomer.add(retrievedPet.get());
                }
            });
        }
        customerEntity.setPets(petsToAssignedToCustomer);
        return customerRepository.save(customerEntity);
    }

    /**
     * @return the list of all customers
     */
    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * get the owner by pet
     *
     * @param petId of the pet
     * @return customer
     */
    @Override
    public CustomerEntity getOwnerByPet(long petId) {
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(EntityNotFoundException::new);
        return petEntity.getCustomer();
    }

    @Override
    public CustomerEntity getCustomer(long customerId) {
        return customerRepository.getOne(customerId);
    }
}
