package com.switchfully.eurder.users.service;

import com.switchfully.eurder.items.domain.ItemDto;
import com.switchfully.eurder.items.service.ItemService;
import com.switchfully.eurder.users.domain.NewMemberDto;

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
        ItemDto itemDto = new ItemDto("",
                "crushed grain",
                new BigDecimal(15.00),
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(itemDto));
    }
    @Test
    void givenNoDescription_ExceptionIsThrown() {
        ItemDto itemDto = new ItemDto("flour",
                "",
                new BigDecimal(15.00),
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(itemDto));
    }
    @Test
    void givenNoPrice_ExceptionIsThrown() {
        ItemDto itemDto = new ItemDto("flour",
                "crushed grain",
                null,
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(itemDto));
    }




}