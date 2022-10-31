package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.items.addItemDto;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.service.members.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private SecurityService securityService;
    private ItemService itemService;

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    public ItemController(SecurityService securityService, ItemService itemService) {
        this.securityService = securityService;
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public addItemDto addItem(@RequestHeader String authorization, @RequestBody addItemDto addItemDto) {
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        logger.info("Added " + addItemDto.getName() + " to the list");
        return itemService.addItem(addItemDto);
    }
}
