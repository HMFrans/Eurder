package com.switchfully.eurder.service.customers;

import com.switchfully.eurder.domain.customers.Address;
import com.switchfully.eurder.domain.customers.Customer;
import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
import com.switchfully.eurder.service.customers.dto.ReturnCustomerDto;


public class CustomerMapper {
    public Customer DtoToCustomer(NewCustomerDto newCustomerDto) {
        return new Customer(newCustomerDto.getPassword(),
                newCustomerDto.getFirstName(),
                newCustomerDto.getLastName(),
                newCustomerDto.getEmailAddress(),
                newCustomerDto.getPhoneNumber(),
                new Address(
                        newCustomerDto.getCity(),
                        newCustomerDto.getPostalCode(),
                        newCustomerDto.getStreetName(),
                        newCustomerDto.getHouseNumber(),
                        newCustomerDto.getAdditionalInfo()
                ));
    }

    public ReturnCustomerDto customerToDto(Customer customer) {
        return new ReturnCustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getAddress(), customer.getPhoneNumber());
    }
}
