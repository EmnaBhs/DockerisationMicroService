package com.targa.labs.myboutique.productservice.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.targa.labs.myboutique.commons.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.targa.labs.myboutique.commons.utils.Web.API;
import com.targa.labs.myboutique.productservice.service.ProductService;


@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/products")
public class ProductResource {


    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }
}
