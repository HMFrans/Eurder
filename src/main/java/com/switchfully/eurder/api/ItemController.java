package com.switchfully.eurder.api;

import com.switchfully.eurder.service.items.dto.AddItemDto;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.service.customers.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final SecurityService securityService;
    private final ItemService itemService;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public ItemController(SecurityService securityService, ItemService itemService) {
        this.securityService = securityService;
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddItemDto addItem(@RequestHeader String authorization, @RequestBody AddItemDto addItemDto) {
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        logger.info("Adding " + addItemDto.getName() + " to the list");
        return itemService.addItem(addItemDto);
    }
}
