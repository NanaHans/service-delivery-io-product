package service.article.administrator.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.article.administrator.enums.Supermarket;
import service.article.administrator.repositories.SupermarketRepository;
import service.article.administrator.services.impl.HTMLParserImpl;

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
			service.article.administrator.entities.Supermarket supermarket = new service.article.administrator.entities.Supermarket();
			supermarket.setId(supermarketEnum.getId());
			supermarket.setName(supermarketEnum.getName());
			supermarketRepo.save(supermarket);
		}
	}
}
