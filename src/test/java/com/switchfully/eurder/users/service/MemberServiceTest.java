package com.switchfully.eurder.users.service;

import com.switchfully.eurder.users.domain.Member;
import com.switchfully.eurder.users.domain.MemberRepository;
import com.switchfully.eurder.users.domain.NewMemberDto;
import com.switchfully.eurder.users.domain.ReturnMemberDto;
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
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob", "Squarepants", "spongebob@hotmail.com", "Bikini Bottom", "1000", "waterstreet", "1", "", "555-58");
        ReturnMemberDto newReturnMemberDto = memberService.addNewMember(newMemberDto);

        Assertions.assertTrue(memberRepository.getMembers().stream().anyMatch(member -> newReturnMemberDto.getId().equals(member.getId())));
    }
}