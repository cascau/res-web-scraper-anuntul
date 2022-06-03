package com.tonks.res.webscraper.anuntul.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tonks.res.webscraper.anuntul.to.BreadcrumbTO;
import com.tonks.res.webscraper.anuntul.to.GeoLocationTO;
import com.tonks.res.webscraper.anuntul.to.LocationTO;
import com.tonks.res.webscraper.anuntul.to.TitlePriceCurrencyTO;

@Component
public class DomParser {

	private static final Logger logger = LoggerFactory.getLogger(DomParser.class);

	public TitlePriceCurrencyTO extractTitlePriceCurrency(Document doc) {
		TitlePriceCurrencyTO result = new TitlePriceCurrencyTO();
		Elements select;
		Node first;
		try {
			Elements elem = doc.select("div.titlu-anunt");
			if (elem.first() != null) {
				select = elem.select("div.price.i-fr");
				if (select.first() != null && select.first().childNode(0) != null) {
					first = select.first().childNode(0);
					String priceCurrency = first.outerHtml().trim();
					if (!StringUtils.isBlank(priceCurrency)) {
						String[] tokens = priceCurrency.split(" ");
						if (tokens.length > 1) {
							String priceStr = tokens[0].replace(".", "");
							priceStr = priceStr.replace(",", ".");
							try {
								Double dbl = Double.parseDouble(priceStr);
								result.setPrice(dbl);
							} catch (Exception e) {
								logger.error("Error getting [extractPrice]", e);
							}
							String currencyStr = tokens[1];
							if ("€".equals(currencyStr)) {
								currencyStr = "eur";
							} else if ("$".equals(currencyStr)) {
								currencyStr = "usd";
							}
							result.setCurrency(currencyStr.toLowerCase());
						}
					}
				}
				select = elem.select("h1");
				if (select.first() != null && select.first().childNode(0) != null) {
					first = select.first().childNode(0);
					String title = first.outerHtml().trim();
					result.setTitle(title);
				}
			}
		} catch (Exception e) {
			logger.error("Error getting [extractTitlePriceCurrency]", e);
		}
		return result;
	}

	public String extractDescription(Document doc) {
		StringBuilder sb = new StringBuilder();
		List<Node> nodes = new ArrayList<>();
		try {
			Elements select = doc.select("div.descriere-anunt");
			if (select.first() != null) {
				select.forEach(elem -> nodes.addAll(elem.childNodes()));
				
				nodes.forEach(node -> {
					sb.append(node.outerHtml());
				});
			}
		} catch (Exception e) {
			logger.error("Error getting [extractDescription]", e);
		}
		return sb.toString();
	}

	public BreadcrumbTO extractBreadcrumb(Document doc) {
		BreadcrumbTO to = new BreadcrumbTO();
		Elements select;
		String[] tokens;
		String str, words;
		try {
			Elements breadcrumb = doc.select("div.breadcrumbs.i-fl");
			if (breadcrumb.first() != null) {
				select = breadcrumb.select("a.link2");
				if (select.first() != null && select.first().childNode(0) != null) {
					words = select.first().childNode(0).outerHtml();
					if (!StringUtils.isBlank(words)) {
						tokens = words.split(" ");
						if (tokens.length > 1) {
							str = tokens[1];
							to.setOfferType(str);
						}
					}
				}

				select = breadcrumb.select("a.link3");
				if (select.first() != null && select.first().childNode(0) != null) {
					words = select.first().childNode(0).outerHtml();
					if (!StringUtils.isBlank(words)) {
						tokens = words.split(" ");

						switch (tokens.length) {
						case 1: { // Garsoniere
							to.setPropertyType(tokens[0]);
							to.setRoomsNumber(1);
							break;
						}
						case 2: { // Case, vile
							to.setPropertyType(words);
							break;
						}
						case 3: { // Apartamente X camere
							to.setPropertyType(tokens[0]);
							try {
								str = tokens[1].replaceAll("[^0-9]", "");
								to.setRoomsNumber(Integer.parseInt(str));
							} catch (Exception e) {
								logger.error("Error extracting breadcrumb rooms_number", e);
							}
							break;
						}
						}
					}

				}
			}
		} catch (Exception e) {
			logger.error("Error extracting breadcrumb", e);
		}
		return to;
	}

	public Boolean extractAvailable(Document doc) {
		try {
			Elements elem = doc.select("div.butt-right.butt-red");
			return elem == null || elem.first() == null;
		} catch (Exception e) {
			logger.error("Error extracting available", e);
		}
		return true;
	}

	public LocationTO extractLocation(Document doc) {
		LocationTO location = new LocationTO();
		try {
			Elements elem = doc.select("div.loc-data.float-right");
			if (elem.first() != null && elem.first().childNode(0) != null) {
				Node childNode = elem.first().childNode(0);
				String[] mainTokens = childNode.outerHtml().trim().split(",");
				if (mainTokens.length > 0) {
					String str = mainTokens[0];
					if (StringUtils.isNotBlank(str)) {
						String[] tokens = str.split(" ");
						if (tokens.length > 0) {
							String token = tokens[0];
							switch (token.toLowerCase()) {
							case "ilfov": {
								location.setCounty("Ilfov");
								return location;
							}
							case "sector": {
								location.setCity("Bucuresti");
								location.setCounty("Bucuresti-Ilfov");
								location.setNeighborhood(str);
								return location;
							}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error extracting location", e);
		}
		return location;
	}

	public String extractPictures(Document doc) {
		try {
			Elements elem = doc.select("a[data-gall=\"gallery-full\"]");
			if (!elem.isEmpty()) {
				String picturesAsArray = elem.stream()
						.filter(node -> !org.apache.commons.lang3.StringUtils.isBlank(node.attr("href")))
						.map(node -> node.attr("href")).collect(Collectors.joining(","));

				return picturesAsArray;
			}
		} catch (Exception e) {
			logger.error("Error getting [extractPictures]", e);
		}
		return "";
	}

	public GeoLocationTO extractGeolocation(Document doc) {
		GeoLocationTO location = new GeoLocationTO();
		try {
			Elements elem = doc.select("input[type=\"hidden\"]");
			if (!elem.isEmpty()) {
				String latStr = elem.stream().filter(el -> "lat".equals(el.attr("id"))).findFirst()
						.orElse(new Element("invalid")).attr("value");
				String lngStr = elem.stream().filter(el -> "lng".equals(el.attr("id"))).findFirst()
						.orElse(new Element("invalid")).attr("value");
				if (StringUtils.isNoneBlank(latStr, lngStr)) {
					location.setLatitude(Double.parseDouble(latStr));
					location.setLongitude(Double.parseDouble(lngStr));
				}
			}
		} catch (Exception e) {
			logger.error("Error getting [extractPictures]", e);
		}
		return location;
	}
}
