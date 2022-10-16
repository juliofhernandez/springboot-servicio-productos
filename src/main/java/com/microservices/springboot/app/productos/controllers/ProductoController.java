package com.microservices.springboot.app.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.springboot.app.productos.models.entity.Product;
import com.microservices.springboot.app.productos.models.services.IProductService;

@RestController
public class ProductoController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/products")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product findById(@PathVariable Long id) {
		return productService.findById(id);		
	}
}
