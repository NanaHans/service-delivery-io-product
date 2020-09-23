package de.service.delivery.io.services.impl;

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

import de.service.delivery.io.entities.Product;
import de.service.delivery.io.entities.Supermarket;
import de.service.delivery.io.entities.SupermarketProduct;
import de.service.delivery.io.entities.SupermarketProductPK;
import de.service.delivery.io.enums.Category;
import de.service.delivery.io.repositories.CategoryRepository;
import de.service.delivery.io.repositories.ProductRepository;
import de.service.delivery.io.repositories.SupermarketProductPriceRepository;
import de.service.delivery.io.repositories.SupermarketRepository;
import de.service.delivery.io.services.HTMLParserService;

@Service
@Transactional
public class HTMLParserImpl implements HTMLParserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HTMLParserImpl.class);

	@Autowired
	private SupermarketRepository supermarketRepo;
	@Autowired
	private CategoryRepository categoryRepos;
	@Autowired
	private ProductRepository productRepos;
	@Autowired
	private SupermarketProductPriceRepository supermarketProductPriceRepos;

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
				int supermarketId = 1;
				Supermarket supermarket = supermarketRepo.findById(new Long(supermarketId)).get();
				de.service.delivery.io.entities.Category categoryEntity = null;
				if (categoryRepos.findById(category.getId()).isPresent()) {
					categoryEntity = categoryRepos.findById(category.getId()).get();
				}
				Product product = null;
				if (!productRepos.findByName(productName).isEmpty()) {
					product = productRepos.findByName(productName).stream().findFirst().get();
				}

				for (int j = 0; j < tdList.size(); j++) {
					Elements priseGramm = tdList.get(j).getElementsByClass("small");
					String[] priceGramSplited = priseGramm.text().split("/");

					String price = "0";
					String unit = "";
					extractPriceGram(category, th, priceGramSplited, supermarket);

					if (categoryEntity == null) {
						categoryEntity = new de.service.delivery.io.entities.Category();
						categoryEntity.setName(category.getName());
						categoryEntity.setId(category.getId());
					}

					if (product == null) {
						product = new Product();
						product.setName(productName);
						productRepos.save(product);
					}

					categoryEntity.getProducts().add(product);
					categoryEntity.getSupermarkets().add(supermarket);

//					product.getCategories().add(categoryEntity);
//					supermarket.getCategories().add(categoryEntity);

					SupermarketProductPK supermarketProductPK = new SupermarketProductPK();
					supermarketProductPK.setProduct(product);
					supermarketProductPK.setSupermarket(supermarket);

					SupermarketProduct supermarketProduct = new SupermarketProduct();
					supermarketProduct.setId(supermarketProductPK);
					supermarketProduct.setPrice(new BigDecimal(price));
					supermarketProduct.setUnit(unit);

					categoryEntity = categoryRepos.save(categoryEntity);

					supermarketProduct = supermarketProductPriceRepos.save(supermarketProduct);
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