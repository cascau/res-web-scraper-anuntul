package com.tonks.res.webscraper.anuntul.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;

@Service
@Scope("prototype")
public class SearchPageScraper {

	private static final Logger logger = LoggerFactory.getLogger(SearchPageScraper.class);

	public SearchPage generatePageLinkForOffer(Element item, Date now) {
		Node targetTag = this.searchLinkTag(item);
		if (targetTag != null && targetTag.attr("href") != null) {
			String pageLink = targetTag.attr("href");
			return new SearchPage(pageLink, now);
		}
		return new SearchPage("", now);
	}

	public Node searchLinkTag(Element searchItem) {
		Elements ahref = searchItem.select("a");
		Element target = ahref.stream()
			.filter(elem -> elem.hasAttr("href") && !StringUtils.isBlank(elem.attr("href")))
			.findFirst()
			.orElse(null);
		return target;
	}

	public List<Element> extractElements(String URL) throws IOException {
		Document document = Jsoup.connect(URL).get();
		Elements ahrefs = document.select("div[class=\"clearfix line-btw anunt-row i-cb i-pr anunt-w  \"]");
		Elements promoted = document
				.select("div[class=\"clearfix line-btw anunt-row i-cb i-pr anunt-w listare-evidentiata \"]");

		List<Element> allHrefs = Stream.concat(Stream.of(ahrefs), Stream.of(promoted))
				.flatMap(List::stream)
				.collect(Collectors.toList());

		return allHrefs;
	}

	public List<Element> findOffers(String URL) {
		List<Element> tables = new ArrayList<>();
		try {
			logger.info("Scraping " + URL);
			tables.addAll(this.extractElements(URL));
			logger.debug("Found " + tables.size() + " elements.");
		} catch (IOException e) {
			logger.error("Error for '" + URL + "': " + e.getMessage());
		}
		return tables;
	}
}
