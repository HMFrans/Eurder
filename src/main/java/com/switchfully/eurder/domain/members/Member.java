package com.switchfully.eurder.domain.members;

import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.Role;

import java.util.UUID;

public class Member {
    private String id;
    private String password;
    private String firstName;
    private String LastName;
    private String emailAddress;
    private Address address;
    private String phoneNumber;
    private Role role;

    public Member(String password, String firstName, String lastName, String emailAddress, String phoneNumber, Address address) {
        this.password = password;
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.LastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = Role.MEMBER;
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
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

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
