package com.tonks.res.webscraper.anuntul.schedulers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;
import com.tonks.res.webscraper.anuntul.persistence.entities.Offer;
import com.tonks.res.webscraper.anuntul.service.LandingPageService;
import com.tonks.res.webscraper.anuntul.service.OfferService;

@Controller
@EnableAsync
public class TransformerScheduler {

	private static final Logger logger = LoggerFactory.getLogger(TransformerScheduler.class);

	@Value("${jobs.dbtrans.enabled}")
	private boolean jobEnabled;
	
	@Value("${anuntul.tr.batch_size}")
	private Integer offersBatchSize;
	
	@Value("${anuntul.tr.start_rate}")
	private Long startRateInMillis;
	
	@Autowired
	private LandingPageService landingPageService;
	
	@Autowired
	private OfferService offerService;
	
	@Async
	@Scheduled(fixedRateString = "${anuntul.tr.start_rate}", initialDelayString = "${anuntul.tr.initial_delay}")
	public void transformData() {
		
		if (jobEnabled) {
			Date start = new Date(System.currentTimeMillis());
			logger.info("Start ANUNTUL.RO TRANSFORMER SCHEDULER task at " + start);
			
			Long itemsAtStart = offerService.countAll();
			Date searchDate = DateUtils.addDays(start, -30);
			
			List<LandingPage> pagedItems;
			int pageNb = 0, totalInserts = 0;
			do {
				Set<Offer> itemsToInsert = new HashSet<>();
				logger.info("Page = " + pageNb);
				
				pagedItems = landingPageService.findAfterDate(searchDate, PageRequest.of(pageNb, offersBatchSize));
				logger.info("Search for " + pagedItems.size() + " items.");
			
				pagedItems.parallelStream().forEach(link -> {
					itemsToInsert.add(offerService.landingPageToOffer(link));
				});
				
				offerService.insertBulk(itemsToInsert);
				
				totalInserts += itemsToInsert.size();
				pageNb++;
				logger.info("Found " + itemsToInsert.size() + " items to insert into DB. Total: " + totalInserts);
			} while (!pagedItems.isEmpty());

			Long itemsAtEnd = offerService.countAll();
			this.logFinishMessage(start, totalInserts, itemsAtEnd - itemsAtStart);
		} else {
			logger.info("ANUNTUL.RO TRANSFORMER SCHEDULER is disabled");
		}
	}
	
	private void logFinishMessage(Date now, int totalInserts, long totalUpdates) {
		Date end = new Date(System.currentTimeMillis());
		long millis = end.getTime() - now.getTime();

		logger.info("Stop ANUNTUL.RO TRANSFORMER SCHEDULER scrap task at " + end);
		logger.info("Process took " + millis / 60000 + " minutes and " + (millis % 60000) / 1000
				+ " seconds");
		logger.info("Process handled " + totalInserts + " offers!");
		logger.info("Process updated " + totalUpdates + " offers!");
	}
}
