package com.switchfully.eurder.api;

import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.service.customers.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final CustomerService customerService;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(CustomerService customerService, SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void makeMemberAdmin(@RequestHeader String authorization, @PathVariable Integer userId) {
        securityService.validateAuthorization(authorization, Feature.MAKE_ADMIN);
        customerService.makeAdmin(userId);
        logger.info("Member " + userId + "was upgraded to admin");
    }


}
