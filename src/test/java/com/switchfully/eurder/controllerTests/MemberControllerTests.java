package com.switchfully.eurder.controllerTests;

import com.switchfully.eurder.api.MemberController;
import com.switchfully.eurder.service.members.MemberService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTests {
    @InjectMocks
    private MemberController memberController;
    @Mock
    private MemberService memberService;

//    @Test
//    void addMemberCallsMemberService() {
//        NewMemberDto newMemberDto = new NewMemberDto("Spongebob", "Squarepants", "spongebob@hotmail.com", "Bikini Bottom", "1000", "waterstreet", "1", "", "555-58", "password");
//        memberController.createNewMember(newMemberDto);
//
//        Mockito.verify(memberController).createNewMember(newMemberDto);
//    }

}
