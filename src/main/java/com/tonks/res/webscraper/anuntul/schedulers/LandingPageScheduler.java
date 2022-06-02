package com.tonks.res.webscraper.anuntul.schedulers;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;
import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;
import com.tonks.res.webscraper.anuntul.scrapers.LandingPageScraper;
import com.tonks.res.webscraper.anuntul.service.LandingPageService;
import com.tonks.res.webscraper.anuntul.service.SearchPageService;

@Controller
@EnableAsync
public class LandingPageScheduler {

	private static final Logger logger = LoggerFactory.getLogger(LandingPageScheduler.class);

	@Value("${anuntul.sp.batch_size}")
	private Integer offersBatchSize;

	@Value("${jobs.landing.enabled}")
	private boolean jobEnabled;

	@Autowired
	private LandingPageService landingPageService;

	@Autowired
	private SearchPageService offerPageService;

	@Autowired
	private LandingPageScraper landingPageScraper;

	@Async
	@Scheduled(fixedRateString = "${anuntul.lp.start_rate}", initialDelayString = "${anuntul.lp.initial_delay}")
	public void scrapLandingPages() {

		if (jobEnabled) {
			Date start = new Date(System.currentTimeMillis());
			logger.info("Start ANUNTUL.RO LANDING PAGE SCHEDULER scrap task at " + start);
			Date searchDate = DateUtils.addDays(start, -30);

			Long itemsAtStart = landingPageService.countAll();
			Page<SearchPage> pagedItems;
			int pageNb = 0, totalInserts = 0;
			do {
				Set<LandingPage> itemsToInsert = new HashSet<>();
				logger.info("Page = " + pageNb);

				pagedItems = offerPageService.findOffersAfterDate(searchDate, PageRequest.of(pageNb, offersBatchSize));
				logger.info("Search for " + pagedItems.getSize() + " items.");

				pagedItems.getContent().parallelStream().forEach(link -> {
					try {
						itemsToInsert.add(landingPageScraper.generateLandingPageInfo(link, start));
					} catch (IOException e) {
						logger.error("Could not get from URL " + link.getUrl(), e);
					}
				});

				landingPageService.insertBulk(itemsToInsert);

				totalInserts += itemsToInsert.size();
				pageNb++;
				logger.info("Found " + itemsToInsert.size() + " items to insert into DB. Total: " + totalInserts);
			} while (!pagedItems.isEmpty());

			Long itemsAtEnd = landingPageService.countAll();
			this.logFinishMessage(start, totalInserts, itemsAtEnd - itemsAtStart);
		} else {
			logger.info("ANUNTUL.RO LANDING PAGE SCHEDULER scrap is disabled");
		}
	}

	private void logFinishMessage(Date now, int totalInserts, long totalUpdates) {
		Date end = new Date(System.currentTimeMillis());
		long millis = end.getTime() - now.getTime();

		logger.info("Stop ANUNTUL.RO LANDING PAGE SCHEDULER scrap task at " + end);
		logger.info("Process took " + millis / 60000 + " minutes and " + (millis % 60000) / 1000 + " seconds");
		logger.info("Process handled " + totalInserts + " offers!");
		logger.info("Process updated " + totalUpdates + " offers!");
	}
}
