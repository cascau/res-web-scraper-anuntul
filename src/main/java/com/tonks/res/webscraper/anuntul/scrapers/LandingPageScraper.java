package com.tonks.res.webscraper.anuntul.scrapers;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;
import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;
import com.tonks.res.webscraper.anuntul.to.BreadcrumbTO;
import com.tonks.res.webscraper.anuntul.to.GeoLocationTO;
import com.tonks.res.webscraper.anuntul.to.LocationTO;
import com.tonks.res.webscraper.anuntul.to.TitlePriceCurrencyTO;
import com.tonks.res.webscraper.anuntul.util.DomParser;

@Component
public class LandingPageScraper {

	private static final Logger logger = LoggerFactory.getLogger(LandingPageScraper.class);

	@Autowired
	private DomParser domParser;
	
	public LandingPage generateLandingPageInfo(SearchPage link, Date date) throws IOException {

		LandingPage page = new LandingPage();
		Document doc = Jsoup.connect(link.getUrl()).get();
		logger.info("Parsing " + link.getUrl());

		page.setDateAdded(date);
		page.setUrl(link.getUrl());

		BreadcrumbTO breadcrumb = domParser.extractBreadcrumb(doc);
		TitlePriceCurrencyTO titlePriceCurrency = domParser.extractTitlePriceCurrency(doc);
		LocationTO location = domParser.extractLocation(doc);
		Boolean available = domParser.extractAvailable(doc);
		String pictures = domParser.extractPictures(doc);
		GeoLocationTO geoLocation = domParser.extractGeolocation(doc);
		String description = domParser.extractDescription(doc);
		
		page.setAvailable(available);
		page.setCity(location.getCity());
		page.setCounty(location.getCounty());
		page.setCurrency(titlePriceCurrency.getCurrency());
		page.setDescription(description);
		page.setLatitude(geoLocation.getLatitude());
		page.setLongitude(geoLocation.getLongitude());
		page.setNeighborhood(location.getNeighborhood());
		page.setOfferType(breadcrumb.getOfferType());
		page.setPartitioning(null);
		page.setPictures(pictures);
		page.setPrice(titlePriceCurrency.getPrice());
		page.setPropertyType(breadcrumb.getPropertyType());
		page.setRoomsNumber(breadcrumb.getRoomsNumber());
		page.setTitle(titlePriceCurrency.getTitle());
		page.setUseableSurface(null);
		page.setYearBuilt(null);
 
		logger.info("Finished parsing " + link.getUrl());
		return page;
	}
}
