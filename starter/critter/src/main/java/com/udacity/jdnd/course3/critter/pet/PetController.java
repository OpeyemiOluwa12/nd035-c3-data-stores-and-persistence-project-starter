package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entities.PetEntity;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.utils.Commons;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    private final Commons commons;

    public PetController(PetService petService, Commons commons) {
        this.petService = petService;
        this.commons = commons;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEntity petEntity = petService.savePet(((PetEntity) commons.convertObjectToObject(petDTO, new PetEntity())));
        return ((PetDTO) commons.convertObjectToObject(petEntity, new PetDTO()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return ((PetDTO) commons.convertObjectToObject(petService.getPetEntity(petId), new PetDTO()));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return convertEmployeeEntityListToEmployeeDTOList(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetEntity> petEntities = petService.getPetsByOwner(ownerId);
        return convertEmployeeEntityListToEmployeeDTOList(petEntities);
    }

    private List<PetDTO> convertEmployeeEntityListToEmployeeDTOList(List<PetEntity> petEntityList) {
        return petEntityList.stream().map(petEntity -> ((PetDTO) commons.convertObjectToObject(petEntity, new PetDTO()))).collect(Collectors.toList());
    }
}
