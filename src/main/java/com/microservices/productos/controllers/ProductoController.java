package com.microservices.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.productos.models.Product;
import com.microservices.productos.services.ProductService;

@RestController
public class ProductoController {

	final private ProductService productService;

	public ProductoController(ProductService productService, Environment environment) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) throws InterruptedException {
		if (productService.findById(id).isPresent()) {
			//Simulate error
			if (id == 10L) {
				throw new RuntimeException("No se pudo cargar el producto!");
			}

			//Simulate Timeout
			if (id == 7L) {
				TimeUnit.SECONDS.sleep(2L);
			}

			//Simulate SlowCall
			if (id == 6L) {
				TimeUnit.SECONDS.sleep(1L);
			}

			//Comportamiento normal
			Product product = productService.findById(id).orElseThrow();
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.notFound().build();		
	}
}
