package com.switchfully.eurder.service.customers;

import com.switchfully.eurder.domain.customers.AddressRepository;
import com.switchfully.eurder.domain.customers.Customer;
import com.switchfully.eurder.domain.customers.CustomerRepository;
import com.switchfully.eurder.security.Role;
import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
import com.switchfully.eurder.service.customers.dto.ReturnCustomerDto;
import com.switchfully.eurder.service.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final Validator validator;
    private CustomerRepository customerRepository;
    private AddressRepository  addressRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.validator = new Validator();
        this.customerMapper = new CustomerMapper();
    }

    public ReturnCustomerDto addNewCustomer(NewCustomerDto newCustomerDto) {
        validator.checkRequiredFieldsForNewMember(newCustomerDto);
        assertEmailIsUnique(newCustomerDto);
        //TODO check if the address is not already in the db
        Customer newCustomer = customerMapper.DtoToMember(newCustomerDto);
        customerRepository.save(newCustomer);
        return customerMapper.memberToDto(newCustomer);
    }

    private void assertEmailIsUnique(NewCustomerDto newCustomerDto) {
        if (customerRepository.existsByEmailAddress(newCustomerDto.getEmailAddress())) {
            throw new IllegalArgumentException("this email is already in use");
        }
    }

    public void makeAdmin(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("no user by that id"));
        customer.setRole(Role.ADMIN);
    }

    public List<ReturnCustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> customerMapper.memberToDto(customer))
                .toList();

    }

    public ReturnCustomerDto getCustomer(Integer memberId) {
        return customerMapper.memberToDto(customerRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("No customer found by this id")));
    }

    public Customer getCustomerByEmail(String emailAddress) {
        return customerRepository.findCustomerByEmailAddress(emailAddress);
    }
}
