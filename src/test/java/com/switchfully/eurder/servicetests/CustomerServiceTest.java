package com.switchfully.eurder.servicetests;

import com.switchfully.eurder.domain.members.*;
import com.switchfully.eurder.service.customers.CustomerService;
import com.switchfully.eurder.service.customers.dto.NewMemberDto;
import com.switchfully.eurder.service.customers.dto.ReturnMemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void givenNewMemberDto_MemberGetsAddedToRepository() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob", "Squarepants", "spongebob@hotmail.com", "Bikini Bottom", "1000", "waterstreet", "1", "", "555-58", "password");
        ReturnMemberDto newReturnMemberDto = customerService.addNewMember(newMemberDto);

        Assertions.assertTrue(memberRepository.getMembers().stream().anyMatch(member -> newReturnMemberDto.getId().equals(member.getId())));
    }
}