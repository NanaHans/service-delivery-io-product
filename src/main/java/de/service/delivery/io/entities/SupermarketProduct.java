package de.service.delivery.io.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the supermarket_product database table.
 * 
 */
@Entity
@Table(name = "supermarket_product")
@NamedQuery(name = "SupermarketProduct.findAll", query = "SELECT s FROM SupermarketProduct s")
public class SupermarketProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SupermarketProductPK id;

	@Column(precision = 10)
	private BigDecimal price;

	@Column(length = 50)
	private String unit;

	public SupermarketProduct() {
	}

	public SupermarketProductPK getId() {
		return this.id;
	}

	public void setId(SupermarketProductPK id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}