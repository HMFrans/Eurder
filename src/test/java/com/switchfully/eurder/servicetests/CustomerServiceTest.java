//package com.switchfully.eurder.servicetests;
//
//import com.switchfully.eurder.domain.customers.CustomerRepository;
//import com.switchfully.eurder.service.customers.CustomerService;
//import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
//import com.switchfully.eurder.service.customers.dto.ReturnCustomerDto;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@AutoConfigureTestDatabase
//class CustomerServiceTest {
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    CustomerRepository customerRepository;
//
//    @Test
//    void givenNewMemberDto_MemberGetsAddedToRepository() {
//        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob", "Squarepants", "spongebob@hotmail.com", "Bikini Bottom", "1000", "waterstreet", "1", "", "555-58", "password");
//        ReturnCustomerDto newReturnCustomerDto = customerService.addNewCustomer(newCustomerDto);
//
//        Assertions.assertTrue(memberRepository.getMembers().stream().anyMatch(member -> newReturnCustomerDto.getId().equals(member.getId())));
//    }
//}