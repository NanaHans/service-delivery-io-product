package de.service.delivery.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.service.delivery.io.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
