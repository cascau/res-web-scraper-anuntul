package com.tonks.res.webscraper.anuntul.schedulers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;
import com.tonks.res.webscraper.anuntul.scrapers.SearchPageScraper;
import com.tonks.res.webscraper.anuntul.service.SearchPageService;

@EnableAsync
@Controller
public class SearchPageScheduler {

	private static final Logger logger = LoggerFactory.getLogger(SearchPageScheduler.class);

	private static final Set<String> SEARCH_URLS = new HashSet<>();
	public static final String ANUNTUL_SELL_1ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-vanzari/garsoniere/?page=";
	public static final String ANUNTUL_SELL_2ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-vanzari/apartamente-2-camere/?page=";
	public static final String ANUNTUL_SELL_3ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-vanzari/apartamente-3-camere/?page=";
	public static final String ANUNTUL_SELL_4ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-vanzari/apartamente-4-camere/?page=";
	public static final String ANUNTUL_SELL_HOUSE_URL = "https://www.anuntul.ro/anunturi-imobiliare-vanzari/case-vile/?page=";
	
	public static final String ANUNTUL_RENT_1ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-inchirieri/garsoniere/?page=";
	public static final String ANUNTUL_RENT_2ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-inchirieri/apartamente-2-camere/?page=";
	public static final String ANUNTUL_RENT_3ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-inchirieri/apartamente-3-camere/?page=";
	public static final String ANUNTUL_RENT_4ROOM_URL = "https://www.anuntul.ro/anunturi-imobiliare-inchirieri/apartamente-4-camere/?page=";
	public static final String ANUNTUL_RENT_HOUSE_URL = "https://www.anuntul.ro/anunturi-imobiliare-inchirieri/case-vile/?page=";
	
	@Value("${jobs.offer.enabled}")
	private boolean jobEnabled;

	@Value("${anuntul.sp.max_pages}")
	private int MAX_PAGES;

	static {
		SEARCH_URLS.add(ANUNTUL_SELL_1ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_SELL_2ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_SELL_3ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_SELL_4ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_SELL_HOUSE_URL);
		
		SEARCH_URLS.add(ANUNTUL_RENT_1ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_RENT_2ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_RENT_3ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_RENT_4ROOM_URL);
		SEARCH_URLS.add(ANUNTUL_RENT_HOUSE_URL);
	}

	@Autowired
	private SearchPageScraper scrapper;

	@Autowired
	private SearchPageService service;

	@Async
	@Scheduled(fixedRateString = "${anuntul.sp.start_rate}", initialDelayString = "${anuntul.sp.initial_delay}")
	public void scrapSearchPages() throws InterruptedException, ParseException {

		if (jobEnabled) {
			Date start = new Date(System.currentTimeMillis());
			logger.debug("Start ANUNTUL.RO SEARCH PAGE SCHEDULER task at " + start);

			Long itemsAtStart = service.countAll();
			Set<SearchPage> landingPages = new HashSet<>();
			Set<SearchPage> ignoredPages = new HashSet<>();

			IntStream.range(1, MAX_PAGES + 1).parallel().forEach(pageNb -> {

				List<Element> tables = new ArrayList<>();
				SEARCH_URLS.forEach(url -> {
					tables.addAll(scrapper.findOffers(url + pageNb));
				});
				tables.parallelStream().forEach(elem -> {
					SearchPage link = scrapper.generatePageLinkForOffer(elem, start);
					landingPages.add(link);
				});
			});
			landingPages.remove(new SearchPage("", start));

			landingPages.parallelStream().forEach(page -> {
				Iterable<SearchPage> existingPages = service.findOfferPages(page);
				if (existingPages.iterator().hasNext()) {
					ignoredPages.add(page);
				}
			});

			landingPages.removeAll(ignoredPages);
			service.insertBulk(landingPages);

			Long itemsAtEnd = service.countAll();
			this.logFinishMessage(start, landingPages.size(), itemsAtEnd - itemsAtStart);
		} else {
			logger.info("ANUNTUL.RO SEARCH PAGE SCHEDULER scrap is disabled");
		}
	}

	private void logFinishMessage(Date now, int totalInserts, long totalUpdates) {
		Date end = new Date(System.currentTimeMillis());
		long millis = end.getTime() - now.getTime();

		logger.info("Stop ANUNTUL.RO SEARCH PAGE SEARCH PAGE SCHEDULER scrap task at " + end);
		logger.info("Process took " + millis / 60000 + " minutes and " + (millis % 60000) / 1000 + " seconds");
		logger.info("Process handled " + totalInserts + " offers!");
		logger.info("Process updated " + totalUpdates + " offers!");
	}
}
