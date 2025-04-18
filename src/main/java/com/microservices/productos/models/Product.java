package com.microservices.productos.models;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	@Serial
	private static final long serialVersionUID = 1495116288763971390L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(nullable = false)
	private Double price;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date createAt;

	@Transient
	private Integer port;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		if (price != null) {
			this.price = price;
		} else {
			this.price = 0.0; // Establecer precio por defecto si no se proporciona
		}
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
