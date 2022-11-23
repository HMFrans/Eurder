package com.switchfully.eurder.service.customers.dto;

import com.switchfully.eurder.domain.customers.Address;

public class ReturnCustomerDto {
    private Integer id;
    private String firstName;
    private String LastName;
    private String emailAddress;
    private Address address;
    private String phoneNumber;

    public ReturnCustomerDto(Integer id, String firstName, String lastName, String emailAddress, Address address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
