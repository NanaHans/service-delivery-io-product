package de.service.delivery.io.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the supermarket_article database table.
 * 
 */
@Entity
@Table(name = "supermarket_article")
@NamedQuery(name = "SupermarketArticle.findAll", query = "SELECT s FROM SupermarketArticle s")
public class SupermarketArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SupermarketArticlePK id;

	@Column(precision = 10)
	private BigDecimal price;

	@Column(length = 50)
	private String unit;

	// bi-directional many-to-one association to Article
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id", nullable = false, insertable = false, updatable = false)
	private Article article;

	// bi-directional many-to-one association to Supermarket
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supermarket_id", nullable = false, insertable = false, updatable = false)
	private Supermarket supermarket;

	public SupermarketArticle() {
	}

	public SupermarketArticlePK getId() {
		return this.id;
	}

	public void setId(SupermarketArticlePK id) {
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

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Supermarket getSupermarket() {
		return this.supermarket;
	}

	public void setSupermarket(Supermarket supermarket) {
		this.supermarket = supermarket;
	}

}