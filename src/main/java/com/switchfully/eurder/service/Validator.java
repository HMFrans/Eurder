package com.switchfully.eurder.service;

import com.switchfully.eurder.service.items.dto.AddItemDto;
import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;

import java.util.List;

public class Validator {


    public void checkRequiredFieldsForNewMember(NewCustomerDto newCustomerDto) {
        if (newCustomerDto.getFirstName() == null || newCustomerDto.getFirstName().equals("")) {
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newCustomerDto.getLastName() == null || newCustomerDto.getLastName().equals("")) {
            throw new IllegalArgumentException("Please enter a last name.");
        }
        if (newCustomerDto.getEmailAddress() == null || newCustomerDto.getEmailAddress().equals("")) {
            throw new IllegalArgumentException("Please enter an email address.");
        }
        if (newCustomerDto.getCity() == null || newCustomerDto.getCity().equals("")) {
            throw new IllegalArgumentException("Please enter a city.");
        }
        if (newCustomerDto.getPostalCode() == null || newCustomerDto.getPostalCode().equals("")) {
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newCustomerDto.getStreetName() == null || newCustomerDto.getStreetName().equals("")) {
            throw new IllegalArgumentException("Please enter a street.");
        }
        if (newCustomerDto.getHouseNumber() == null || newCustomerDto.getHouseNumber().equals("")) {
            throw new IllegalArgumentException("Please enter a house number.");
        }
        if (newCustomerDto.getPhoneNumber() == null || newCustomerDto.getPhoneNumber().equals("")) {
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
