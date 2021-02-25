package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.PetEntity;

import java.util.List;

public interface PetService {

    public PetEntity savePet(PetEntity petEntity);

    public PetEntity getPetEntity(long petId);

    public List<PetEntity> getPets();

    public List<PetEntity> getPetsByOwner(long ownerId);

    public PetEntity getPetByCustomerId(long customerId);
}
