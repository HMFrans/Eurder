package com.switchfully.eurder.users.domain;

import java.util.UUID;

public class Member {
    private String id;
    private String firstName;
    private String LastName;
    private String emailAddress;
    private Address address;
    private String phoneNumber;

    public Member(String firstName, String lastName, String emailAddress, String phoneNumber, Address address) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.LastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
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
