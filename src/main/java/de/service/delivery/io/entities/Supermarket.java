package de.service.delivery.io.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the supermarket database table.
 * 
 */
@Entity
@Table(name = "supermarket")
@NamedQuery(name = "Supermarket.findAll", query = "SELECT s FROM Supermarket s")
public class Supermarket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private long id;

	@Column(nullable = false, length = 100)
	private String name;

	// bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(name = "supermarket_category", joinColumns = {
			@JoinColumn(name = "supermarket_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ategory_id", nullable = false) })
	private List<Category> categories = new ArrayList<Category>();

	public Supermarket() {
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

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}