package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
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
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * get the owner by pet
     *
     * @param petId of the pet
     * @return customer
     */
    public CustomerEntity getOwnerByPet(long petId) {
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(EntityNotFoundException::new);
        return petEntity.getCustomer();
    }

    public CustomerEntity getCustomer(long customerId) {
        return customerRepository.getOne(customerId);
    }
}
