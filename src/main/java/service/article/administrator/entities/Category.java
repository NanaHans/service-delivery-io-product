package service.article.administrator.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	private long id;

	@Column(nullable = false, length = 100)
	private String name;

	// bi-directional many-to-many association to Article
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "category_article", joinColumns = {
			@JoinColumn(name = "category_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "article_id", nullable = false) })
	private List<Article> articles = new ArrayList<Article>();

	// bi-directional many-to-many association to Supermarket
	@ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Supermarket> getSupermarkets() {
		return this.supermarkets;
	}

	public void setSupermarkets(List<Supermarket> supermarkets) {
		this.supermarkets = supermarkets;
	}

}