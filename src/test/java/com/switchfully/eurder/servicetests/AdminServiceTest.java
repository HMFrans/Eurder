package com.switchfully.eurder.servicetests;

import com.switchfully.eurder.domain.customers.CustomerRepository;
import com.switchfully.eurder.security.Role;
import com.switchfully.eurder.service.customers.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
public class AdminServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void givenMemberId_UpgradesToAdmin() {
        customerService.makeAdmin(2);

        Assertions.assertEquals(Role.ADMIN, customerRepository.findById(2).orElseThrow().getRole());
    }

}
