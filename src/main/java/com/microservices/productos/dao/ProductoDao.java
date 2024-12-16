package com.microservices.productos.dao;

import org.springframework.data.repository.CrudRepository;

import com.microservices.productos.models.Product;

public interface ProductoDao extends CrudRepository<Product, Long> {

}
