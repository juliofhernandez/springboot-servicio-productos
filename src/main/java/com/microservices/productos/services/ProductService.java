package com.microservices.productos.services;

import java.util.List;
import java.util.Optional;

import com.microservices.productos.models.Product;

public interface ProductService {
	List<Product> findAll();
	Optional<Product> findById(Long id);
	Product save(Product product);
	void deleteById(Long id);
}
