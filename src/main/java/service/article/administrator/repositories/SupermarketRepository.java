package service.article.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import service.article.administrator.entities.Supermarket;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

}
