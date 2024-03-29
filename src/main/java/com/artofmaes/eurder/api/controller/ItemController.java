package com.artofmaes.eurder.api.controller;

import com.artofmaes.eurder.api.dto.item.CreateItemDto;
import com.artofmaes.eurder.api.dto.item.ItemDto;
import com.artofmaes.eurder.api.dto.item.UpdateItemDto;
import com.artofmaes.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //POST
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestHeader(value = "adminId") int adminId, @RequestBody CreateItemDto createItemDto){
        logger.info("Trying to create an item...");
        return itemService.createNewItem(createItemDto, adminId);
    }

    //GET
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems(@RequestHeader(value = "adminId") int adminId){
        logger.info("Trying to gather all items by request of user " + adminId);
        return itemService.getAllItems(adminId);
    }

    //PUT

    @PutMapping(path = "/{itemId}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@RequestHeader(value = "adminId") int adminId, @PathVariable int itemId , @RequestBody UpdateItemDto updateItemDto){
        logger.info("Trying to update item with name " + itemId);
        return itemService.updateItem(updateItemDto, itemId ,adminId);
    }
}
