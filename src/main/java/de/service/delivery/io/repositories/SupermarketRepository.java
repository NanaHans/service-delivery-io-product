package de.service.delivery.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.service.delivery.io.entities.Supermarket;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

}