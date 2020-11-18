package service.article.administrator.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private long id;

	@Column(nullable = false, length = 100)
	private String name;

	// bi-directional many-to-many association to Category
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "supermarket_category", joinColumns = {
			@JoinColumn(name = "supermarket_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "category_id", nullable = false) })
	private List<Category> categories = new ArrayList<Category>();

	// bi-directional many-to-one association to SupermarketArticle
	@OneToMany(mappedBy = "supermarketArticlePK.supermarket", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<SupermarketArticle> supermarketArticles = new ArrayList<SupermarketArticle>();

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

	public List<SupermarketArticle> getSupermarketArticles() {
		return this.supermarketArticles;
	}

	public void setSupermarketArticles(List<SupermarketArticle> supermarketArticles) {
		this.supermarketArticles = supermarketArticles;
	}
}