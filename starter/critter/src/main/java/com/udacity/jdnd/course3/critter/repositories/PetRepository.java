package com.udacity.jdnd.course3.critter.repositories;


import com.udacity.jdnd.course3.critter.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    PetEntity findByOwnerIdEquals(long ownerId);

    List<PetEntity> findAllByOwnerId(long ownerId);
}
