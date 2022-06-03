package com.tonks.res.webscraper.anuntul;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tonks.res.webscraper.anuntul.persistence.entities.LandingPage;
import com.tonks.res.webscraper.anuntul.persistence.entities.SearchPage;
import com.tonks.res.webscraper.anuntul.scrapers.LandingPageScraper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnuntulLandingPageScraperTest {

	@Autowired
	private LandingPageScraper scraper;
	
	@Test
	public void testGenerateLandingPageInfo() throws IOException {
		String link = "https://www.anuntul.ro/anunt-vanzare-garsoniera-rahova-ansamblu-nou-incalzire-r00rd7#";
		SearchPage offerPage = new SearchPage(link, new java.util.Date());
		
		LandingPage page = scraper.generateLandingPageInfo(offerPage, offerPage.getDateAdded());
		
		assertNotNull(page);
	}

}
