package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.items.addItemDto;
import com.switchfully.eurder.domain.members.NewMemberDto;
import com.switchfully.eurder.domain.orders.OrderItemDto;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.service.exceptions.NoItemInListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class Validator {

    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public void checkRequiredFieldsForNewMember(NewMemberDto newMemberDto) {
        if (newMemberDto.getFirstName() == null || newMemberDto.getFirstName().equals("")) {
            logger.error("No firstname");
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newMemberDto.getLastName() == null || newMemberDto.getLastName().equals("")) {
            logger.error("No lastname");
            throw new IllegalArgumentException("Please enter a last name.");
        }
        if (newMemberDto.getEmailAddress() == null || newMemberDto.getEmailAddress().equals("")) {
            logger.error("No emailAddress");
            throw new IllegalArgumentException("Please enter an email address.");
        }
        if (newMemberDto.getCity() == null || newMemberDto.getCity().equals("")) {
            logger.error("No City");
            throw new IllegalArgumentException("Please enter a city.");
        }
        if (newMemberDto.getPostalCode() == null || newMemberDto.getPostalCode().equals("")) {
            logger.error("No Postal Code");
            throw new IllegalArgumentException("Please enter a first name.");
        }
        if (newMemberDto.getStreetName() == null || newMemberDto.getStreetName().equals("")) {
            logger.error("No Street");
            throw new IllegalArgumentException("Please enter a street.");
        }
        if (newMemberDto.getHouseNumber() == null || newMemberDto.getHouseNumber().equals("")) {
            logger.error("No House number");
            throw new IllegalArgumentException("Please enter a house number.");
        }
        if (newMemberDto.getPhoneNumber() == null || newMemberDto.getPhoneNumber().equals("")) {
            logger.error("No phone number");
            throw new IllegalArgumentException("Please enter a phone number.");
        }
    }

    public void checkRequiredFieldsForNewItem(addItemDto addItemDto) {
        if (addItemDto.getName() == null || addItemDto.getName().equals("")) {
            logger.error("No item name");
            throw new IllegalArgumentException("Please enter a name.");
        }
        if (addItemDto.getDescription() == null || addItemDto.getDescription().equals("")) {
            logger.error("No description");
            throw new IllegalArgumentException("Please enter a description.");
        }
        if (addItemDto.getPrice() == null) {
            logger.error("No price");
            throw new IllegalArgumentException("Please enter a price.");
        }
    }

    public void checkItemListOnNewOrder(List<OrderItemDto> orderItemDtoList) {
        if (orderItemDtoList.size() == 0) {
            logger.error("No items to add to order");
            throw new NoItemInListException();
        }

    }
}
