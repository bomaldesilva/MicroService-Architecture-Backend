package com.example.inventory.service;

import com.example.inventory.dto.InventoryDto;
import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    ModelMapper modelMapper;


    public List<InventoryDto> getAll() {
        List<Inventory> inventoryList=inventoryRepo.findAll();
        return modelMapper.map(inventoryList,new TypeToken<List<InventoryDto>>(){}.getType());
    }

    public InventoryDto addInventory(InventoryDto newInventory) {
        inventoryRepo.save(modelMapper.map(newInventory,Inventory.class));
        return newInventory;
    }

    public String deleteInventory(InventoryDto deleteInventory) {
        inventoryRepo.delete(modelMapper.map(deleteInventory,Inventory.class));
        return "deleted";
    }

    public InventoryDto getItemById(Integer itemId) {
        Inventory inventory=inventoryRepo.getItemById(itemId);
        return modelMapper.map(inventory,InventoryDto.class);
    }
}
