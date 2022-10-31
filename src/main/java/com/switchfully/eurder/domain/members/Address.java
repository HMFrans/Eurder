package com.switchfully.eurder.domain.members;

public class Address {
    private String city;
    private String postalCode;
    private String StreetName;
    private String houseNumber;
    private String additionalInfo;

    public Address(String city, String postalCode, String streetName, String houseNumber, String additionalInfo) {
        this.city = city;
        this.postalCode = postalCode;
        StreetName = streetName;
        this.houseNumber = houseNumber;
        this.additionalInfo = additionalInfo;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreetName() {
        return StreetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
