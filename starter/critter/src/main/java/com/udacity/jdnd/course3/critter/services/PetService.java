package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * save pet details
     * @param petEntity form
     * @return saved pet entity
     */
    public PetEntity savePet(PetEntity petEntity){
       return this.petRepository.save(petEntity);
    }

    /**
     * Get pet entity for a pet id
     * @param petId to be fetched
     * @return one pet details
     */
    public PetEntity getPetEntity(long petId){
        return petRepository.getOne(petId);
    }

    /**
     * @return list of pets
     */
    public List<PetEntity> getPets(){
        return petRepository.findAll();
    }

    /**
     *
     * @param ownerId
     * @return all pets by ownerId
     */
    public List<PetEntity> getPetsByOwner(long ownerId){
        return petRepository.findAllByOwnerId(ownerId);
    }
}
