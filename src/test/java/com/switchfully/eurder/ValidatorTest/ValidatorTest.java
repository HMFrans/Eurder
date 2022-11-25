package com.switchfully.eurder.ValidatorTest;

import com.switchfully.eurder.service.items.dto.AddItemDto;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.service.customers.dto.NewCustomerDto;

import com.switchfully.eurder.service.customers.CustomerService;
import com.switchfully.eurder.service.orders.OrderValidator;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatorTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    ItemService itemService;

    @Autowired
    OrderValidator orderValidator;

    @Test
    void givenNoFirstName_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }
    @Test
    void givenNoLastName_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoemailAddress_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "Squarepants",
                "",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoCity_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "",
                "1000",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoPostalCode_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "",
                "waterstreet",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoStreetName_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "",
                "1",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoHouseNumber_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "",
                "",
                "555-58",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoPhoneNumber_ExceptionIsThrown() {
        NewCustomerDto newCustomerDto = new NewCustomerDto("Spongebob",
                "Squarepants",
                "spongebob@hotmail.com",
                "Bikini Bottom",
                "1000",
                "waterstreet",
                "1",
                "",
                "",
                "password");
        assertThrows(IllegalArgumentException.class, () -> customerService.addNewCustomer(newCustomerDto));
    }

    @Test
    void givenNoName_ExceptionIsThrown() {
        AddItemDto addItemDto = new AddItemDto("",
                "crushed grain",
                new BigDecimal("15.00"),
                2);
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(addItemDto));
    }
    @Test
    void givenNoDescription_ExceptionIsThrown() {
        AddItemDto addItemDto = new AddItemDto("flour",
                "",
                new BigDecimal("15.00"),
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

    @Test
    void givenItemNotInDatabase_throwException() {
        List<OrderItemDto> nonExistentItem = List.of(new OrderItemDto("sprouts", 5));
        assertThrows(IllegalArgumentException.class, () -> orderValidator.validateOrderItemDtoList(nonExistentItem));
    }


}