package service.article.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import service.article.administrator.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
