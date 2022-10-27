package com.switchfully.eurder.users.controller;

import com.switchfully.eurder.users.domain.NewMemberDto;
import com.switchfully.eurder.users.domain.ReturnMemberDto;
import com.switchfully.eurder.users.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    private MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnMemberDto createNewMember(@RequestBody NewMemberDto newMemberDto) {
        logger.info("Member added to database");
        return memberService.addNewMember(newMemberDto);
    }

}
