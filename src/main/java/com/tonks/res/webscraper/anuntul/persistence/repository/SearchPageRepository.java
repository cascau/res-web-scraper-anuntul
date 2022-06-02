package com.tonks.res.webscraper.anuntul.persistence.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;

@Repository
public interface SearchPageRepository extends PagingAndSortingRepository<SearchPage, String> {

	Collection<SearchPage> findByUrlAndSource(String pageLink, String source);
	
	Collection<SearchPage> findByDateAddedAfter(Date dateAdded);
	
	Page<SearchPage> findByUrlAndSource(String pageLink, String source, Pageable pageable);
	
	Page<SearchPage> findByUrlContainingAndDateAddedAfter(String source, Date dateAdded, Pageable pageable);
}	