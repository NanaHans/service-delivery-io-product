package de.service.delivery.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.service.delivery.io.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByName(String name);
}
