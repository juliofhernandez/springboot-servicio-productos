package com.microservices.springboot.app.productos.models.services;

import java.util.List;
import com.microservices.springboot.app.productos.models.entity.Product;

public interface ProductService {
	public List<Product> findAll();
	public Product findById(Long id);
}
