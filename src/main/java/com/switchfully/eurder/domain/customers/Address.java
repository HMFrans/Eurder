package com.switchfully.eurder.domain.customers;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "city")
    private String city;
    @Column(name = "postalcode")
    private String postalCode;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "additional_info")
    private String additionalInfo;

    public Address() {
    }

    public Address(String city, String postalCode, String streetName, String houseNumber, String additionalInfo) {
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
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
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

}
