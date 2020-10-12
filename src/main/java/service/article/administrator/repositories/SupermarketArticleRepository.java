package service.article.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import service.article.administrator.entities.SupermarketArticle;

public interface SupermarketArticleRepository extends JpaRepository<SupermarketArticle, Long> {

}
