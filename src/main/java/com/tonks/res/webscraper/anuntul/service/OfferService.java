package com.tonks.res.webscraper.anuntul.service;

import java.sql.Date;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonks.res.webscraper.anuntul.persistence.entities.Advert;
import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;
import com.tonks.res.webscraper.anuntul.persistence.entities.Offer;
import com.tonks.res.webscraper.anuntul.persistence.entities.Property;
import com.tonks.res.webscraper.anuntul.persistence.entities.Seller;
import com.tonks.res.webscraper.anuntul.persistence.repository.OfferRepository;

@Service
public class OfferService {

	@Autowired
	private OfferRepository repository;

	public Offer landingPageToOffer(final LandingPage page) {
		Offer offer = new Offer();

		offer.setUrl(page.getUrl());

		Advert advert = new Advert();
		offer.setAdvert(advert);
		advert.setOffer(offer);
		advert.setCurrency(page.getCurrency());
		advert.setDateAdded(page.getDateAdded());
		advert.setDateSold(null);
		advert.setDescription(page.getDescription());
		advert.setPictures(page.getPictures());
		advert.setPrice(page.getPrice());
		advert.setTitle(page.getTitle());
		advert.setType(page.getOfferType());

		Property property = new Property();
		offer.setProperty(property);
		property.setOffer(offer);
		property.setCity(page.getCity());
		property.setCondition(null);
		property.setCounty(page.getCounty());
		property.setFloorNumber(null);
		property.setLatitude(page.getLatitude());
		property.setLongitude(page.getLongitude());
		property.setNeighborhood(page.getNeighborhood());
		property.setPartitioning(page.getPartitioning());
		property.setRoomsNumber(null);
		property.setTotalFloors(null);
		property.setType(page.getPropertyType());
		property.setUseableSurface(page.getUseableSurface());
		property.setYearBuilt(page.getYearBuilt());

		Seller seller = new Seller();
		offer.setSeller(seller);
		seller.setOffer(offer);
		seller.setName(null);
		seller.setPhone(null);
		seller.setType(null);

		return offer;
	}

	@Transactional
	public Iterable<Offer> insertBulk(Set<Offer> offers) {
		return repository.saveAll(offers);
	}

	@Transactional
	public Iterable<Offer> getBulk() {
		return repository.findAll();
	}

	@Transactional
	public Collection<Offer> findAfterDate(Date dateAdded) {
		return repository.findByAdvertDateAddedAfter(dateAdded);
	}

	@Transactional
	public Page<Offer> findAfterDate(Date dateAdded, Pageable pageable) {
		return repository.findByAdvertDateAddedAfter(dateAdded, pageable);
	}

	@Transactional
	public Long countAll() {
		return repository.count();
	}
}
