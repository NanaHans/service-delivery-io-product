package de.service.delivery.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.service.delivery.io.entities.SupermarketArticle;

public interface SupermarketArticleRepository extends JpaRepository<SupermarketArticle, Long> {

}
