package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.repository.ProductRepo;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("getProducts")
    public List<ProductDto> getProducts(){
        return productService.getAll();
    }
    @PostMapping("addProduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
    @PutMapping("updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
    @DeleteMapping("deleteProduct")
    public String deleteMapping(@RequestBody ProductDto product){
        return productService.deleteProduct(product);

    }
    @GetMapping("product/{productId}")
    public ProductDto getProductByProductId(@PathVariable Integer productId){
        return productService.getProductByProductId(productId);
    }
}
