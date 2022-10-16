package com.microservices.springboot.app.productos.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.springboot.app.productos.models.dao.ProductoDao;
import com.microservices.springboot.app.productos.models.entity.Product;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		
		return (List<Product>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {	
		return productoDao.findById(id).orElse(null);
	}

}
