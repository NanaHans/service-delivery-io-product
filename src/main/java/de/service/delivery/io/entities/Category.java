package de.service.delivery.io.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private long id;

	@Column(nullable = false, length = 100)
	private String name;

	// bi-directional many-to-many association to Product
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "category_product", joinColumns = {
			@JoinColumn(name = "category_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "product_id", nullable = false) })
	private List<Product> products = new ArrayList<Product>();

	// bi-directional many-to-many association to Supermarket
	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private List<Supermarket> supermarkets = new ArrayList<Supermarket>();

	public Category() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Supermarket> getSupermarkets() {
		return supermarkets;
	}

	public void setSupermarkets(List<Supermarket> supermarkets) {
		this.supermarkets = supermarkets;
	}

}