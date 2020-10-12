package de.service.delivery.io.entities;

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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@Table(name = "article")
@NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private long id;

	@Column(nullable = false, length = 200)
	private String name;

	// bi-directional many-to-many association to Category
	@ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Category> categories = new ArrayList<Category>();

	// bi-directional many-to-one association to SupermarketArticle
	@OneToMany(mappedBy = "supermarketArticlePK.article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SupermarketArticle> supermarketArticles = new ArrayList<SupermarketArticle>();

	public Article() {
	}

	public long getId() {
		return this.id;
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