package de.service.delivery.io.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.service.delivery.io.enums.Supermarket;
import de.service.delivery.io.repositories.SupermarketRepository;
import de.service.delivery.io.services.impl.HTMLParserImpl;

@Controller
public class productController {

	@Autowired
	private HTMLParserImpl htmlParserImpl;
	@Autowired
	private SupermarketRepository supermarketRepo;

	@PostConstruct
	private void run() {
//		createSupermarkets();
		htmlParserImpl.parseHtmlFromUrl("https://www.discounter-preisvergleich.de/preisvergleich.php");
	}

	private void createSupermarkets() {
		for (Supermarket supermarketEnum : Supermarket.values()) {
			de.service.delivery.io.entities.Supermarket supermarket = new de.service.delivery.io.entities.Supermarket();
			supermarket.setId(supermarketEnum.getId());
			supermarket.setName(supermarketEnum.getName());
			supermarketRepo.save(supermarket);
		}
	}
}
