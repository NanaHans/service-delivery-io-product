package de.service.delivery.io.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the supermarket_article database table.
 * 
 */
@Embeddable
public class SupermarketArticlePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "supermarket_id")
	private Supermarket supermarket;

	@Column(name = "article_id")
	private Article article;

	public SupermarketArticlePK() {
	}

	public Supermarket getSupermarket() {
		return supermarket;
	}

	public void setSupermarket(Supermarket supermarket) {
		this.supermarket = supermarket;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((supermarket == null) ? 0 : supermarket.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupermarketArticlePK other = (SupermarketArticlePK) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (supermarket == null) {
			if (other.supermarket != null)
				return false;
		} else if (!supermarket.equals(other.supermarket))
			return false;
		return true;
	}

}