package de.service.delivery.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.service.delivery.io.entities.SupermarketProduct;

public interface SupermarketProductPriceRepository extends JpaRepository<SupermarketProduct, Long> {

}
