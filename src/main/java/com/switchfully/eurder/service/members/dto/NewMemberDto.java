package com.switchfully.eurder.service.members.dto;

public class NewMemberDto {
    private String firstName;
    private String LastName;
    private String emailAddress;
    private String city;
    private String postalCode;
    private String streetName;
    private String houseNumber;
    private String additionalInfo;
    private String phoneNumber;
    private String password;

    public NewMemberDto(String firstName, String lastName, String emailAddress, String city, String postalCode, String streetName, String houseNumber, String additionalInfo, String phoneNumber, String password) {
        this.firstName = firstName;
        LastName = lastName;
        this.emailAddress = emailAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.additionalInfo = additionalInfo;
        this.phoneNumber = phoneNumber;
        this.password = password;
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

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
