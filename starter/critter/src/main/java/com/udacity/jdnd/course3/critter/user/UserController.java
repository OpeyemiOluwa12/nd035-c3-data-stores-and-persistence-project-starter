package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.utils.Commons;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final EmployeeService employeeService;

    private final CustomerService customerService;

    private final Commons commons;

    public UserController(EmployeeService employeeService, CustomerService customerService, Commons commons) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.commons = commons;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerService.saveCustomer((CustomerEntity) commons.convertObjectToObject(customerDTO, new CustomerEntity()), customerDTO.getPetIds());
        return ((CustomerDTO) commons.convertObjectToObject(customerEntity, new CustomerDTO()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return convertCustomerEntityListToCustomerDTOList(customerService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        CustomerEntity customerEntity = customerService.getOwnerByPet(petId);
        return convertCustomerEntityToCustomerDTO(customerEntity);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeService.saveEmployee((EmployeeEntity) commons.convertObjectToObject(employeeDTO, new EmployeeEntity()));
        return ((EmployeeDTO) commons.convertObjectToObject(employeeEntity, new EmployeeDTO()));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeEntity employeeEntity = employeeService.getEmployee(employeeId);
        return ((EmployeeDTO) commons.convertObjectToObject(employeeEntity, new EmployeeDTO()));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return convertEmployeeEntityListToEmployeeDTOList(employeeService.findAllEmployee());
    }

    private List<CustomerDTO> convertCustomerEntityListToCustomerDTOList(List<CustomerEntity> customerEntityList) {
        return customerEntityList.stream().map(this::convertCustomerEntityToCustomerDTO).collect(Collectors.toList());
    }

    private CustomerDTO convertCustomerEntityToCustomerDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity, customerDTO);

        List<Long> petIds = new ArrayList<>();

        if (customerEntity.getPets() != null) {
            customerEntity.getPets().forEach(petEntity -> {
                petIds.add(petEntity.getId());
            });
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }


    private List<EmployeeDTO> convertEmployeeEntityListToEmployeeDTOList(List<EmployeeEntity> employeeEntityList) {
        return employeeEntityList.stream().map(employeeEntity -> ((EmployeeDTO) commons.convertObjectToObject(employeeEntity, new EmployeeDTO()))).collect(Collectors.toList());
    }


}
