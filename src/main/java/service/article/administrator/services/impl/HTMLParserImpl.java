package service.article.administrator.services.impl;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.article.administrator.entities.Article;
import service.article.administrator.entities.Supermarket;
import service.article.administrator.entities.SupermarketArticle;
import service.article.administrator.entities.SupermarketArticlePK;
import service.article.administrator.enums.Category;
import service.article.administrator.repositories.ArticletRepository;
import service.article.administrator.repositories.CategoryRepository;
import service.article.administrator.repositories.SupermarketArticleRepository;
import service.article.administrator.repositories.SupermarketRepository;
import service.article.administrator.services.HTMLParserService;

@Service
@Transactional
public class HTMLParserImpl implements HTMLParserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HTMLParserImpl.class);

	@Autowired
	private SupermarketRepository supermarketRepo;
	@Autowired
	private CategoryRepository categoryRepos;
	@Autowired
	private ArticletRepository articleRepos;
	@Autowired
	private SupermarketArticleRepository supermarketArticleRepo;

	@Override
	public void parseHtmlFromUrl(String url) {
		Document htmlpage = null;
		try {
			htmlpage = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("#### html-page form URL => {} could no be parsed.", url);
		}
		String unescapedString = StringEscapeUtils.unescapeHtml4(htmlpage.toString());
		Document doc = Jsoup.parse(unescapedString);

		for (Category category : Category.values()) {
			Element categoryElement = doc.getElementById(Long.toString(category.getId()));
			Elements tableElements = categoryElement.nextElementSiblings();
			for (Element element : tableElements) {
				Elements tables = element.getElementsByClass("table-responsive");
				if (!tables.isEmpty()) {
					Element table = tables.first();
					parseHTMLTable(category, table);
					break;
				}
			}
		}

	}

	private void parseHTMLTable(Category category, Element table) {
		Elements trList = table.select("tr");
		for (int i = 0; i < trList.size(); i++) {
			if (i > 0) {
				Element tr = trList.get(i);
				Elements thList = tr.select("th");
				thList.stream().findFirst().get().getElementsByTag("small").first().remove();
				thList.stream().findFirst().get().getElementsByTag("br").first().remove();
				Element th = thList.stream().findFirst().get();
				String productName = th.text();

				Elements tdList = tr.select("td");

				service.article.administrator.entities.Category categoryEntity = null;
				if (categoryRepos.findById(category.getId()).isPresent()) {
					categoryEntity = categoryRepos.findById(category.getId()).get();
				}
				Article article = null;
				if (!articleRepos.findByName(productName).isEmpty()) {
					article = articleRepos.findByName(productName).stream().findFirst().get();
				}

				String price = "0";
				String unit = "";
				int supermarketId = 1;
				for (int j = 0; j < tdList.size(); j++) {
					Supermarket supermarket = supermarketRepo.findById(new Long(supermarketId)).get();
					Elements priseGramm = tdList.get(j).getElementsByClass("small");
					String[] priceGramSplited = priseGramm.text().split("/");
					if (priceGramSplited.length > 1) {
						price = priceGramSplited[0];
						price = price.replace("€", "");
						unit = priceGramSplited[1];
					}

					extractPriceGram(category, th, priceGramSplited, supermarket);

					if (categoryEntity == null) {
						categoryEntity = new service.article.administrator.entities.Category();
						categoryEntity.setName(category.getName());
						categoryEntity.setId(category.getId());
						categoryEntity = categoryRepos.save(categoryEntity);
					}

					if (article == null) {
						article = new Article();
						article.setName(productName);
						article = articleRepos.save(article);
					}

					categoryEntity.getArticles().add(article);
					categoryEntity.getSupermarkets().add(supermarket);

//					product.getCategories().add(categoryEntity);
//					supermarket.getCategories().add(categoryEntity);

					SupermarketArticlePK supermarketArticletPK = new SupermarketArticlePK();
					supermarketArticletPK.setArticle(article);
					supermarketArticletPK.setSupermarket(supermarket);

					SupermarketArticle supermarketArticle = new SupermarketArticle();
					supermarketArticle.setId(supermarketArticletPK);
					BigDecimal b = BigDecimal.valueOf(Double.parseDouble(price));
					supermarketArticle.setPrice(b);
					supermarketArticle.setUnit(unit);

					article.getCategories().add(categoryEntity);
					article.getSupermarketArticles().add(supermarketArticle);

//					articleRepos.save(article);

//					categoryEntity = categoryRepos.save(categoryEntity);
//
					supermarketArticle = supermarketArticleRepo.save(supermarketArticle);
					supermarketId++;
				}

			}
		}

	}

	private void extractPriceGram(Category category, Element th, String[] priceGramSplited, Supermarket supermarket) {
		String price;
		String gramm;
		if (priceGramSplited.length > 1) {
			price = priceGramSplited[0].replace("€", "");
			gramm = priceGramSplited[1];

			LOGGER.info("### date from row => " + supermarket.getName() + " ## " + category.getName() + " ## "
					+ th.text() + " ## " + price + "/" + gramm);
		} else {
			LOGGER.info("### date from row => " + supermarket.getName() + " ## " + category.getName() + " ## "
					+ th.text() + "  No price/gramm");
		}
	}

}
