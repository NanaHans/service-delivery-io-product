package service.article.administrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.article.administrator.repositories.ArticletRepository;
import service.article.administrator.repositories.CategoryRepository;
import service.article.administrator.repositories.SupermarketArticleRepository;
import service.article.administrator.repositories.SupermarketRepository;
import service.article.administrator.services.impl.HTMLParserImpl;

@Controller
public class productController {

	@Autowired
	private HTMLParserImpl htmlParserImpl;
	@Autowired
	private SupermarketRepository supermarketRepo;
	@Autowired
	private CategoryRepository categoryRepos;
	@Autowired
	private ArticletRepository articleRepos;
	@Autowired
	private SupermarketArticleRepository supermarketArticleRepo;

}
