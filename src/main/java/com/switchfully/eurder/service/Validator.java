package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.items.addItemDto;
import com.switchfully.eurder.domain.members.NewMemberDto;

public class Validator {

    public void checkRequiredFieldsForNewMember(NewMemberDto newMemberDto) {
        if (newMemberDto.getFirstName() == null || newMemberDto.getFirstName().equals("")) {
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newMemberDto.getLastName() == null || newMemberDto.getLastName().equals("")) {
            throw new IllegalArgumentException("Please enter a last name.");
        }
        if (newMemberDto.getEmailAddress() == null || newMemberDto.getEmailAddress().equals("")) {
            throw new IllegalArgumentException("Please enter an email address.");
        }
        if (newMemberDto.getCity() == null || newMemberDto.getCity().equals("")) {
            throw new IllegalArgumentException("Please enter a city.");
        }
        if (newMemberDto.getPostalCode() == null || newMemberDto.getPostalCode().equals("")) {
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newMemberDto.getStreetName() == null || newMemberDto.getStreetName().equals("")) {
            throw new IllegalArgumentException("Please enter a street.");
        }
        if (newMemberDto.getHouseNumber() == null || newMemberDto.getHouseNumber().equals("")) {
            throw new IllegalArgumentException("Please enter a house number.");
        }
        if (newMemberDto.getPhoneNumber() == null || newMemberDto.getPhoneNumber().equals("")) {
            throw new IllegalArgumentException("Please enter a phone number.");
        }
    }

    public void checkRequiredFieldsForNewItem(addItemDto addItemDto) {
        if (addItemDto.getName() == null || addItemDto.getName().equals("")) {
            throw new IllegalArgumentException("Please enter a name.");
        }
        if (addItemDto.getDescription() == null || addItemDto.getDescription().equals("")) {
            throw new IllegalArgumentException("Please enter a description.");
        }
        if (addItemDto.getPrice() == null || addItemDto.getPrice().equals("")) {
            throw new IllegalArgumentException("Please enter a price.");
        }
    }
}
