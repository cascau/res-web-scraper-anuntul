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

@Component
public class LandingPageScraper {

	private static final Logger logger = LoggerFactory.getLogger(LandingPageScraper.class);

//	@Autowired
//	private DomParser domParser;
	
	public LandingPage generateLandingPageInfo(SearchPage link, Date date) throws IOException {

		LandingPage page = new LandingPage();
		Document doc = Jsoup.connect(link.getUrl()).get();
		logger.info("Parsing " + link.getUrl());

		page.setDateAdded(date);
		page.setUrl(link.getUrl());
		
//		SellerDetailsTO sellerDetails = domParser.extractSellerDetails(doc);
//		BreadcrumbTO breadcrumb = domParser.extractBreadcrumb(doc);
//		PriceWithCurrencyTO pwc = domParser.extractPriceAndCurrency(doc);
//		GeneralInfoTO generalInfo = domParser.extractGeneralInfo(doc);
//		ParticularitiesTO partics = domParser.extractParticularities(doc);
//
//		page.setSellerName(sellerDetails.getName());
//		page.setSellerType(sellerDetails.getType() != null ? sellerDetails.getType() : partics.getSellerType());
//		page.setSellerPhone(sellerDetails.getPhone());
//
//		page.setOfferType(breadcrumb.getOfferType());
//		page.setCounty(breadcrumb.getCounty());
//		page.setCity(breadcrumb.getCity());
//		page.setNeighborhood(breadcrumb.getNeighborhood());
//		page.setStreet(breadcrumb.getStreet());
//
//		page.setPictures(domParser.extractPictures(doc));
//		page.setTitle(domParser.extractTitle(doc));
//		page.setPrice(pwc.getPrice());
//		page.setCurrency(pwc.getCurrency());
//
//		page.setTotalSurface(generalInfo.getTotalSurface());
//		page.setUseableSurface(generalInfo.getUseableSurface());
//		page.setRoomsNumber(generalInfo.getRoomsNb());
//		page.setCondition(generalInfo.getCondition());
//		page.setFloorNumber(generalInfo.getFloorNb());
//		page.setTotalFloors(generalInfo.getTotalFloors());
//		page.setExteriorFeatures(generalInfo.getExteriorFeatures());
//		page.setPropertyType(
//				generalInfo.getPropertyType() != null ? generalInfo.getPropertyType() : partics.getPropertyType());
//		page.setPartitioning(
//				generalInfo.getPartitioning() != null ? generalInfo.getPartitioning() : partics.getPartitioning());
//		page.setYearBuilt(generalInfo.getYearBuilt() != null ? generalInfo.getYearBuilt() : partics.getYearBuilt());
//		page.setBathroomsNumber(
//				generalInfo.getBathroomsNb() != null ? generalInfo.getBathroomsNb() : partics.getBathroomsNumber());
//		page.setLandSurface(generalInfo.getLandSurface());
//		page.setParking(generalInfo.getParking());
//		page.setOrientation(generalInfo.getOrientation());
//
//		page.setDescription(domParser.extractDescription(doc));
//
//		page.setElevator(partics.getElevator());
//		page.setCommodities(partics.getCommodities());
//		page.setSafety(partics.getSafety());
//		page.setCharacteristics(partics.getCharacteristics());
//		page.setInfrastructure(partics.getInfrastructure());
//		page.setSurroundings(partics.getSurroundings());
//		page.setNegociable(partics.getNegociable());
 
		logger.info("Finished parsing " + link.getUrl());
		return page;
	}
}
