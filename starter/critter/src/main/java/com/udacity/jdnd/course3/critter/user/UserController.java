package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
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

    public UserController(EmployeeService employeeService, CustomerService customerService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerService.saveCustomer((CustomerEntity) convertObjectToObject(customerDTO, new CustomerEntity()));
        return ((CustomerDTO) convertObjectToObject(customerEntity, new CustomerDTO()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return convertCustomerEntityListToCustomerDTOList(customerService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        CustomerEntity customerEntity = customerService.getOwnerByPet(petId);
        return ((CustomerDTO) convertObjectToObject(customerEntity, new CustomerDTO()));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeService.saveEmployee((EmployeeEntity) convertObjectToObject(employeeDTO, new EmployeeEntity()));
        return ((EmployeeDTO) convertObjectToObject(employeeEntity, new EmployeeDTO()));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeEntity employeeEntity = employeeService.getEmployee(employeeId);
        return ((EmployeeDTO) convertObjectToObject(employeeEntity, new EmployeeDTO()));
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
        return customerEntityList.stream().map(customerEntity -> ((CustomerDTO) convertObjectToObject(customerEntity, new CustomerDTO()))).collect(Collectors.toList());
    }


    private List<EmployeeDTO> convertEmployeeEntityListToEmployeeDTOList(List<EmployeeEntity> employeeEntityList) {
        return employeeEntityList.stream().map(customerEntity -> ((EmployeeDTO) convertObjectToObject(employeeEntityList, new EmployeeDTO()))).collect(Collectors.toList());
    }

    private Object convertObjectToObject(Object entryClass, Object destinationClass) {
        BeanUtils.copyProperties(entryClass, destinationClass);
        return destinationClass;
    }

}
