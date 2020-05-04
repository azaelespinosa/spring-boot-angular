package com.chub.controller;

import com.chub.dto.ItemDto;
import com.chub.dto.ItemStockDto;
import com.chub.request.AddQuantityRequest;
import com.chub.request.CreateItemRequest;
import com.chub.request.WithdrawalQuantityRequest;
import com.chub.service.ItemService;
import com.chub.service.ItemStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
@Api(description = "Item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemStockService itemStockService;

    @GetMapping("/findall")
    @ApiOperation(value = "Find all items")
    public ResponseEntity<List<ItemDto>> findAllItems(){
        return ResponseEntity.ok().body(itemService.findAllItems());
    }

    @GetMapping("/stock/findall")
    @ApiOperation(value = "Find all items in stock")
    public ResponseEntity<List<ItemStockDto>> findAllItemStock(){
        return ResponseEntity.ok().body(itemStockService.findAllItemsStock());
    }

    @GetMapping("/{itemNo}")
    @ApiOperation(value = "Find item by itemNo")
    public ResponseEntity<ItemDto> findItemByItemNo(@PathVariable Long itemNo) {
        return ResponseEntity.ok().body(itemService.findItemByItemNo(itemNo));
    }

    @PostMapping
    @ApiOperation(value = "Create Item")
    public ResponseEntity<ItemDto> createItem(@RequestBody CreateItemRequest dto)  {
        return ResponseEntity.ok().body(itemService.createItem(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update all the Item")
    public ResponseEntity<ItemDto> updateItem(@RequestBody CreateItemRequest dto, @PathVariable Long id)  {
        return ResponseEntity.ok().body(itemService.updateItem(dto, id));
    }

    @PutMapping("/stock/withdrawal/{itemNo}")
    @ApiOperation(value = "Remove stock from item")
    public ResponseEntity<ItemStockDto> withdrawalQuantityByItemNo(@RequestBody WithdrawalQuantityRequest dto)  {
        return ResponseEntity.ok().body(itemStockService.withdrawalQuantityByItemNo(dto));
    }

    @PutMapping("/stock/add/{itemNo}")
    @ApiOperation(value = "Add stock to item")
    public ResponseEntity<ItemStockDto> addQuantityByItemNo(@RequestBody AddQuantityRequest dto) {
        return ResponseEntity.ok().body(itemStockService.addQuantityByItemNo(dto));
    }

    @DeleteMapping("/{itemNo}")
    @ApiOperation(value = "Delete item (soft)")
    public ResponseEntity deleteItem(@PathVariable Long itemNo) {
        itemService.deleteItem(itemNo);
        return ResponseEntity.ok().build();
    }

}
