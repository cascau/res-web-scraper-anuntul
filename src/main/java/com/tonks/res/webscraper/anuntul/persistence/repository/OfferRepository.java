package com.tonks.res.webscraper.anuntul.persistence.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tonks.res.webscraper.anuntul.persistence.entities.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>, PagingAndSortingRepository<Offer, Long> {


	Collection<Offer> findByAdvertDateAddedAfter(Date dateAdded);
	
	Page<Offer> findByAdvertDateAddedAfter(Date dateAdded, Pageable pageable);
}
