package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDto;
import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("getInventories")
    public List<InventoryDto> getInventories(){
        return inventoryService.getAll();
    }
    @PostMapping("addInventory")
    public InventoryDto addInventory(@RequestBody InventoryDto newInventory){
        return inventoryService.addInventory(newInventory);
    }
    @PutMapping("updateInventory")
    public InventoryDto updateInventory(@RequestBody InventoryDto newInventory){
        return inventoryService.addInventory(newInventory);
    }
    @DeleteMapping("deleteInventory")
    public String deleteInventory(@RequestBody InventoryDto deleteInventory){
        return inventoryService.deleteInventory(deleteInventory);
    }
    @GetMapping("/item/{itemId}")
    public InventoryDto getItemById(@PathVariable Integer itemId) {
        return inventoryService.getItemById(itemId);
    }

}
