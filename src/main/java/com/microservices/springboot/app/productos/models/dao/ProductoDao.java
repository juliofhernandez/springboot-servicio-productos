package com.microservices.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.microservices.springboot.app.productos.models.entity.Product;

public interface ProductoDao extends CrudRepository<Product, Long> {

}
