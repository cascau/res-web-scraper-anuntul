package com.tonks.res.webscraper.anuntul.persistence.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;


@Repository
public interface LandingPageRepository extends PagingAndSortingRepository<LandingPage, String> {

	Collection<LandingPage> findByDateAddedAfter(Date dateAdded);
	
	Page<LandingPage> findByDateAddedAfter(Date dateAdded, Pageable pageable);
}
