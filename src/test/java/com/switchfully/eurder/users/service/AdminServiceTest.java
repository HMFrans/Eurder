package com.switchfully.eurder.users.service;

import com.switchfully.eurder.security.Role;
import com.switchfully.eurder.users.domain.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void givenMemberId_UpgradesToAdmin() {
        memberService.makeAdmin("member");

        Assertions.assertEquals(Role.ADMIN, memberRepository.getMember("member").getRole());
    }

}