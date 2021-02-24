package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * save pet details
     *
     * @param petEntity form
     * @return saved pet entity
     */
    public PetEntity savePet(PetEntity petEntity) {
        petEntity.setCustomer(customerRepository.getOne(petEntity.getOwnerId()));
        PetEntity petEntity1 = petRepository.save(petEntity);

        CustomerEntity customerEntity = customerRepository.getOne(petEntity.getOwnerId());
        customerEntity.getPets().add(petEntity1);
        customerRepository.save(customerEntity);
        return petEntity1;
    }

    /**
     * Get pet entity for a pet id
     *
     * @param petId to be fetched
     * @return one pet details
     */
    public PetEntity getPetEntity(long petId) {
        return petRepository.getOne(petId);
    }

    /**
     * @return list of pets
     */
    public List<PetEntity> getPets() {
        return petRepository.findAll();
    }

    /**
     * @param ownerId
     * @return all pets by ownerId
     */
    public List<PetEntity> getPetsByOwner(long ownerId) {
        return petRepository.findAllByOwnerIdEquals(ownerId);
    }

    public PetEntity getPetByCustomerId(long customerId) {
        return petRepository.findByCustomer_IdEquals(customerId);
    }
}
