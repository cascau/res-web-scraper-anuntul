package com.tonks.res.webscraper.anuntul.service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;
import com.tonks.res.webscraper.anuntul.persistence.repository.SearchPageRepository;

@Service
public class SearchPageService {

	@Autowired
	private SearchPageRepository repository;
	
	@Transactional
	public Iterable<SearchPage> insertBulk(Set<SearchPage> linksToPages) {
		return repository.saveAll(linksToPages);
	}

	@Transactional
	public Iterable<SearchPage> getBulk() {
		return repository.findAll();
	}

	@Transactional
	public Collection<SearchPage> findOfferPages(SearchPage page) {
		return repository.findByUrlAndSource(page.getUrl(), page.getSource());
	}
	
	@Transactional
	public Collection<SearchPage> findOffersAfterDate(Date date) {
		return repository.findByDateAddedAfter(date);
	}

	@Transactional
	public Page<SearchPage> getBulk(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional
	public Page<SearchPage> findOfferPages(SearchPage page, Pageable pageable) {
		return repository.findByUrlAndSource(page.getUrl(), page.getSource(), pageable);
	}

	@Transactional
	public Page<SearchPage> findOffersAfterDate(Date date, Pageable pageable) {
		return repository.findByUrlContainingAndDateAddedAfter("storia.ro", date, pageable);
	}
	
	@Transactional
	public Long countAll() {
		return repository.count();
	}
}
