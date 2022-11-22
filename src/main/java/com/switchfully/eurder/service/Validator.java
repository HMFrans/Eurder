package com.switchfully.eurder.service;

import com.switchfully.eurder.service.items.dto.AddItemDto;
import com.switchfully.eurder.service.members.dto.NewMemberDto;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;

import java.util.List;

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

    public void checkRequiredFieldsForNewItem(AddItemDto addItemDto) {
        if (addItemDto.getName() == null || addItemDto.getName().equals("")) {
            throw new IllegalArgumentException("Please enter a name.");
        }
        if (addItemDto.getDescription() == null || addItemDto.getDescription().equals("")) {
            throw new IllegalArgumentException("Please enter a description.");
        }
        if (addItemDto.getPrice() == null) {
            throw new IllegalArgumentException("Please enter a price.");
        }
    }

    public void checkItemListOnNewOrder(List<OrderItemDto> orderItemDtoList) {
        if (orderItemDtoList.size() == 0) {
            throw new IllegalArgumentException("No items to add to order");
        }

    }
}
