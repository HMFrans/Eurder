package com.switchfully.eurder.service.customers;

import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void checkRequiredFieldsForNewCustomer(NewCustomerDto newCustomerDto) {
        if (newCustomerDto.getFirstName() == null || newCustomerDto.getFirstName().equals("")) {
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newCustomerDto.getLastName() == null || newCustomerDto.getLastName().equals("")) {
            throw new IllegalArgumentException("Please enter a last name.");
        }
        if (newCustomerDto.getEmailAddress() == null || newCustomerDto.getEmailAddress().equals("")) {
            throw new IllegalArgumentException("Please enter an email address.");
        }
        if (newCustomerDto.getCity() == null || newCustomerDto.getCity().equals("")) {
            throw new IllegalArgumentException("Please enter a city.");
        }
        if (newCustomerDto.getPostalCode() == null || newCustomerDto.getPostalCode().equals("")) {
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newCustomerDto.getStreetName() == null || newCustomerDto.getStreetName().equals("")) {
            throw new IllegalArgumentException("Please enter a street.");
        }
        if (newCustomerDto.getHouseNumber() == null || newCustomerDto.getHouseNumber().equals("")) {
            throw new IllegalArgumentException("Please enter a house number.");
        }
        if (newCustomerDto.getPhoneNumber() == null || newCustomerDto.getPhoneNumber().equals("")) {
            throw new IllegalArgumentException("Please enter a phone number.");
        }
    }
}
