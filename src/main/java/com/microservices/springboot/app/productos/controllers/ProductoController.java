package com.microservices.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.springboot.app.productos.models.entity.Product;
import com.microservices.springboot.app.productos.models.services.ProductService;

@RestController
public class ProductoController {
	
//	@Autowired
//	private Environment environment;
	
	@Value("${server.port}")
	private Integer serverPort;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> findAll(){
		return productService.findAll().stream().map(product->{
//			product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			product.setPort(serverPort);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/products/{id}")
	public Product findById(@PathVariable Long id) {
		Product product = productService.findById(id);
//		product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		product.setPort(serverPort);
		
//		Sim error
//		boolean ok = false;
//		if (!ok) {
//			throw new RuntimeException("No se pudo cargar el producto!");
//		}
		
//		Sim Timeout
//		try {
//			System.out.println("Waiting...");
//			Thread.sleep(2000L);			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		return product;		
	}
}
