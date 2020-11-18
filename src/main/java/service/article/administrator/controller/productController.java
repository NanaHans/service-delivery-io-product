package service.article.administrator.controller;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.article.administrator.entities.Article;
import service.article.administrator.entities.Category;
import service.article.administrator.entities.Supermarket;
import service.article.administrator.entities.SupermarketArticle;
import service.article.administrator.entities.SupermarketArticlePK;
import service.article.administrator.enums.Categories;
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

	@PostConstruct
	private void run() {
		createSupermarkets();
//		htmlParserImpl.parseHtmlFromUrl("https://www.discounter-preisvergleich.de/preisvergleich.php");
	}

	private void createSupermarkets() {

		Category categoryEntity = new Category();
		categoryEntity.setId(Categories.BROT_UND_BROETCHEN.getId());
		categoryEntity.setName(Categories.BROT_UND_BROETCHEN.getName());

		Article article = new Article();

		categoryEntity.getArticles().add(article);
		Supermarket supermarket = supermarketRepo.findById(new Long(1)).get();
		categoryEntity.getSupermarkets().add(supermarket);

//		product.getCategories().add(categoryEntity);
//		supermarket.getCategories().add(categoryEntity);

		SupermarketArticlePK supermarketArticletPK = new SupermarketArticlePK();
		supermarketArticletPK.setArticle(article);
		supermarketArticletPK.setSupermarket(supermarket);

		SupermarketArticle supermarketArticle = new SupermarketArticle();
		supermarketArticle.setId(supermarketArticletPK);
		BigDecimal b = BigDecimal.valueOf(Double.parseDouble("3.5"));
		supermarketArticle.setPrice(b);
		supermarketArticle.setUnit("€");

		article.getCategories().add(categoryEntity);
		article.getSupermarketArticles().add(supermarketArticle);
		articleRepos.save(article);

//		articleRepos.save(article);

//		categoryEntity = categoryRepos.save(categoryEntity);
//
//		supermarketArticle = supermarketArticleRepo.save(supermarketArticle);
	}
}
