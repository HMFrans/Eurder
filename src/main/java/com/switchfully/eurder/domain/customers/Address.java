package com.switchfully.eurder.domain.customers;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq",allocationSize = 1)
    private Integer id;
    @Column(name = "city")
    private String city;
    @Column(name = "postalcode")
    private String postalCode;
    @Column(name = "street_name")
    private String StreetName;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "additional_info")
    private String additionalInfo;

    public Address() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode) && Objects.equals(StreetName, address.StreetName) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(additionalInfo, address.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postalCode, StreetName, houseNumber, additionalInfo);
    }
}
