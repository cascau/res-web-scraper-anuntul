package com.tonks.res.webscraper.anuntul.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;
import com.tonks.res.webscraper.anuntul.persistence.repository.LandingPageRepository;

@Service
public class LandingPageService {

	@Autowired
	private LandingPageRepository repository;

	@Transactional
	public Iterable<LandingPage> insertBulk(Set<LandingPage> pages) {
		return repository.saveAll(pages);
	}

	@Transactional
	public List<LandingPage> getBulk() {
		Iterable<LandingPage> all = repository.findAll();
		return StreamSupport.stream(all.spliterator(), true).collect(Collectors.toList());
	}

	@Transactional
	public List<LandingPage> getBulk(Pageable pageable) {
		Iterable<LandingPage> all = repository.findAll(pageable);
		return StreamSupport.stream(all.spliterator(), true).collect(Collectors.toList());
	}

	@Transactional
	public List<LandingPage> findAfterDate(Date dateAdded, Pageable pageable) {
		Iterable<LandingPage> all = repository.findByDateAddedAfter(dateAdded, pageable);
		return StreamSupport.stream(all.spliterator(), true).collect(Collectors.toList());
	}
	
	@Transactional
	public Long countAll() {
		return repository.count();
	}
}
