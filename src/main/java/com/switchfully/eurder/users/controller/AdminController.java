package com.switchfully.eurder.users.controller;

import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.users.domain.ReturnMemberDto;
import com.switchfully.eurder.users.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private MemberService memberService;
    private SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    public AdminController(MemberService memberService, SecurityService securityService) {
        this.memberService = memberService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(path = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void makeMemberAdmin(@RequestHeader String authorization, @PathVariable String userId) {
        securityService.validateAuthorization(authorization, Feature.MAKE_ADMIN);
        memberService.makeAdmin(userId);
        logger.info("Member " + userId + "was upgraded to admin");
    }
}
