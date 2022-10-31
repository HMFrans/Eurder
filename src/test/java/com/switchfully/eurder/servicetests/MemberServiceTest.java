package com.switchfully.eurder.servicetests;

import com.switchfully.eurder.domain.members.MemberRepository;
import com.switchfully.eurder.domain.members.NewMemberDto;
import com.switchfully.eurder.domain.members.ReturnMemberDto;
import com.switchfully.eurder.service.members.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void givenNewMemberDto_MemberGetsAddedToRepository() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob", "Squarepants", "spongebob@hotmail.com", "Bikini Bottom", "1000", "waterstreet", "1", "", "555-58", "password");
        ReturnMemberDto newReturnMemberDto = memberService.addNewMember(newMemberDto);

        Assertions.assertTrue(memberRepository.getMembers().stream().anyMatch(member -> newReturnMemberDto.getId().equals(member.getId())));
    }
}