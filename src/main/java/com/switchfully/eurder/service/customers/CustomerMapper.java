package com.switchfully.eurder.service.customers;

import com.switchfully.eurder.domain.customers.Address;
import com.switchfully.eurder.domain.customers.Customer;
import com.switchfully.eurder.service.customers.dto.NewMemberDto;
import com.switchfully.eurder.service.customers.dto.ReturnMemberDto;


public class CustomerMapper {
    public Customer DtoToMember(NewMemberDto newMemberDto) {
        return new Customer(newMemberDto.getPassword(),
                newMemberDto.getFirstName(),
                newMemberDto.getLastName(),
                newMemberDto.getEmailAddress(),
                newMemberDto.getPhoneNumber(),
                new Address(
                        newMemberDto.getCity(),
                        newMemberDto.getPostalCode(),
                        newMemberDto.getStreetName(),
                        newMemberDto.getHouseNumber(),
                        newMemberDto.getAdditionalInfo()
                ));
    }

    public ReturnMemberDto memberToDto(Customer customer) {
        return new ReturnMemberDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getAddress(), customer.getPhoneNumber());
    }
}
