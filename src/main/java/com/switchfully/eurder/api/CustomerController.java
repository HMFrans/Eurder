package com.switchfully.eurder.api;

import com.switchfully.eurder.service.customers.dto.NewCustomerDto;
import com.switchfully.eurder.service.customers.dto.ReturnCustomerDto;
import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.service.customers.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    public CustomerController(CustomerService customerService, SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnCustomerDto createNewCustomer(@RequestBody NewCustomerDto newCustomerDto) {
        logger.info("Adding customer to database");
        return customerService.addNewCustomer(newCustomerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReturnCustomerDto> getAllCustomers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_MEMBERS);
        logger.info("Getting all customers");
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{CustomerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnCustomerDto getCustomer(@RequestHeader String authorization, @PathVariable Integer CustomerId) {
        securityService.validateAuthorization(authorization, Feature.GET_MEMBER);
        logger.info("Getting customer: " + CustomerId);
        return customerService.getCustomer(CustomerId);
    }

}
