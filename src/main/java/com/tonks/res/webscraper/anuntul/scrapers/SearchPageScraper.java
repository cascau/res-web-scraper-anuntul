package com.tonks.res.webscraper.anuntul.scrapers;

import java.io.IOException;
import java.util.Date;

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

	public Node searchLinkTag(Node searchItem) {
		return searchItem;
	}

	public Elements extractElements(String URL) throws IOException {
		Document document = Jsoup.connect(URL).get();
		Elements ahrefs = document.select("div[class=\"clearfix line-btw anunt-row i-cb i-pr anunt-w  \"]");
		return ahrefs;
	}

	public Elements findOffers(String URL) {
		Elements tables = new Elements();
		try {
			logger.info("Scraping " + URL);
			tables = this.extractElements(URL);
			logger.debug("Found " + tables.size() + " elements.");
		} catch (IOException e) {
			logger.error("Error for '" + URL + "': " + e.getMessage());
		}
		return tables;
	}
}
