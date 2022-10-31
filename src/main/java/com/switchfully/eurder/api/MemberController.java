package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.members.NewMemberDto;
import com.switchfully.eurder.domain.members.ReturnMemberDto;
import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.service.members.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    private MemberService memberService;
    private SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    public MemberController(MemberService memberService, SecurityService securityService) {
        this.memberService = memberService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnMemberDto createNewMember(@RequestBody NewMemberDto newMemberDto) {
        logger.info("Member added to database");
        return memberService.addNewMember(newMemberDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReturnMemberDto> getAllMembers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_MEMBERS);
        logger.info("Getting all members");
        return memberService.getAllMembers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnMemberDto getMember(@RequestHeader String authorization, @PathVariable String memberId) {
        securityService.validateAuthorization(authorization, Feature.GET_MEMBER);
        logger.info("Getting member: " + memberId);
        return memberService.getMember(memberId);
    }


}
