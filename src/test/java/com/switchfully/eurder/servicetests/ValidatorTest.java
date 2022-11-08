package com.switchfully.eurder.servicetests;

import com.switchfully.eurder.domain.items.AddItemDto;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.domain.members.NewMemberDto;

import com.switchfully.eurder.service.members.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatorTest {
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;

    @Test
    void givenNoFirstName_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }
    @Test
    void givenNoLastName_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoemailAddress_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoCity_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoPostalCode_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoStreetName_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoHouseNumber_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoPhoneNumber_ExceptionIsThrown() {
        NewMemberDto newMemberDto = new NewMemberDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "",
                "password");
        assertThrows(IllegalArgumentException.class, () -> memberService.addNewMember(newMemberDto));
    }

    @Test
    void givenNoName_ExceptionIsThrown() {
        AddItemDto addItemDto = new AddItemDto("",
                "crushed grain",
                new BigDecimal(15.00),
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(addItemDto));
    }
    @Test
    void givenNoDescription_ExceptionIsThrown() {
        AddItemDto addItemDto = new AddItemDto("flour",
                "",
                new BigDecimal(15.00),
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(addItemDto));
    }
    @Test
    void givenNoPrice_ExceptionIsThrown() {
        AddItemDto addItemDto = new AddItemDto("flour",
                "crushed grain",
                null,
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(addItemDto));
    }




}