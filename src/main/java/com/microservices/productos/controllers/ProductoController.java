package com.microservices.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.microservices.productos.models.ProductDTOIn;
import com.microservices.productos.models.ProductDTOOut;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservices.productos.models.Product;
import com.microservices.productos.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductoController {

	private final ProductService productService;
	private final ModelMapper modelMapper;
	private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

	public ProductoController(ProductService productService, ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}

	@GetMapping()
	public List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
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
			logger.info("Producto encontrado");
			Product product = productService.findById(id).orElseThrow();
			return ResponseEntity.ok(product);
		}
		logger.info("No se pudo cargar el producto");
		return ResponseEntity.notFound().build();		
	}

	@PostMapping()
	public ResponseEntity<ProductDTOOut> save(@RequestBody ProductDTOIn productDTOIn) {
		// Usamos un DTO para desacoplar la API pública de la entidad persistente y evitar exponer información innecesaria o sensible
		Product product = modelMapper.map(productDTOIn, Product.class);
		ProductDTOOut productDTOOut = modelMapper.map(productService.save(product), ProductDTOOut.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDTOOut);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (productService.findById(id).isPresent()) {
			productService.deleteById(id);
			logger.info("Producto eliminado");
			return ResponseEntity.ok().build();
		}
		logger.info("No se pudo eliminar el producto");
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTOOut> update(@PathVariable Long id, @RequestBody ProductDTOIn productDTOIn)  {
		return productService.findById(id).map(productDB -> {
			modelMapper.map(productDTOIn, productDB);
			productDB.setId(id);
			Product productUpdated = productService.save(productDB);
			return ResponseEntity.ok(modelMapper.map(productUpdated, ProductDTOOut.class));
		}).orElse(ResponseEntity.notFound().build());
	}
}
