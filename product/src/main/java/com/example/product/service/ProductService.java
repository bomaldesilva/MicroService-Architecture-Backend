package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ModelMapper modelMapper;

    public List<ProductDto> getAll() {
        List<Product>productList = productRepo.findAll();
        return modelMapper.map(productList,new TypeToken<List<ProductDto>>(){}.getType());
    }

    public ProductDto addProduct(ProductDto productDto) {
        productRepo.save(modelMapper.map(productDto,Product.class));
        return productDto;
    }

    public String deleteProduct(ProductDto product) {
        productRepo.delete(modelMapper.map(product,Product.class));
        return "deleted";
    }

    public ProductDto getProductByProductId(Integer productId) {
        Product product=productRepo.getProductByProductId(productId);
        return modelMapper.map(product,ProductDto.class);
    }
}
