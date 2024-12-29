package com.microservices.productos.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.productos.dao.ProductoDao;
import com.microservices.productos.models.Product;

@Service
public class ProductServiceImpl implements ProductService {

	//La mejor práctica es usar inyección por constructor con dependencias marcadas como final
	final private ProductoDao productoDao;
	final private Environment environment;

	public ProductServiceImpl(ProductoDao productoDao, Environment environment) {
		this.productoDao = productoDao;
		this.environment = environment;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return ((List<Product>) productoDao.findAll()).stream().map(product -> {
			product.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
			return product;
		}).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(Long id) {
		return productoDao.findById(id).map(product -> {
			product.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
            return product;
        });
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return productoDao.save(product);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
	}
}
