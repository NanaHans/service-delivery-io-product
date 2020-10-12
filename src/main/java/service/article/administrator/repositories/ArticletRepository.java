package service.article.administrator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import service.article.administrator.entities.Article;

public interface ArticletRepository extends JpaRepository<Article, Long> {

	public List<Article> findByName(String name);
}
