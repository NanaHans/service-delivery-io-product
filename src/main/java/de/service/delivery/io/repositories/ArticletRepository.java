package de.service.delivery.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.service.delivery.io.entities.Article;

public interface ArticletRepository extends JpaRepository<Article, Long> {

	public List<Article> findByName(String name);
}
