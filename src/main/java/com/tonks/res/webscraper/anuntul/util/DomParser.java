package com.tonks.res.webscraper.anuntul.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DomParser {

	private static final Logger logger = LoggerFactory.getLogger(DomParser.class);
//
//	public SellerDetailsTO extractSellerDetails(Document doc) {
//		SellerDetailsTO detailsTO = new SellerDetailsTO();
////		try {
////			Elements elementsByTag = doc.getElementsByTag("aside");
////			Elements elementsByClass = doc.getElementsByClass("css-1dihcof.ezb2r8u9");
////			Elements select = elementsByTag.select("div.css-1dihcof");
////			int x = 1;
////		} catch (Exception e) {
////			logger.error("Error getting [extractSellerDetails]", e);
////		}
//		
//		return detailsTO;
//	}
//
//	public String extractTitle(Document doc) {
//		String result = "";
//		try {
//			Elements elem = doc.select("h1[data-cy=\"adPageAdTitle\"]");
//			if (elem.first() != null) {
//				Node node = elem.first().childNode(0);
//				result = node.outerHtml();
//			}
//		} catch (Exception e) {
//			logger.error("Error getting [extractTitle]", e);
//		}
//		return result;
//	}
//
//	public String extractDescription(Document doc) {
//		StringBuilder sb = new StringBuilder();
//		try {
//			Elements select = doc.select("div[data-cy=\"adPageAdDescription\"]");
//			if (select.first() != null) {
//				List<Node> nodes = select.first().childNodes();
//				nodes.forEach(node -> {
//					sb.append(node.outerHtml());
//				});
//			}
//		} catch (Exception e) {
//			logger.error("Error getting [extractDescription]", e);
//		}
//		return sb.toString();
//	}
//
//	public BreadcrumbTO extractBreadcrumb(Document doc) {
//		BreadcrumbTO to = new BreadcrumbTO();
//		try {
//			Elements breadcrumb = doc.select("a.css-1in5nid.e1je57sb6");
//			if (breadcrumb.first() != null) {
//				String words = breadcrumb.first().childNode(0).outerHtml();
//				String offerType = words.substring(words.lastIndexOf(' ')).trim();
//				to.setOfferType(offerType);
//				String county = breadcrumb.get(1).childNode(0).outerHtml();
//				to.setCounty(county);
//				String city = breadcrumb.get(2).childNode(0).outerHtml();
//				to.setCity(city);
//				if (breadcrumb.size() > 3) {
//					String neighborhood = breadcrumb.get(3).childNode(0).outerHtml();
//					to.setNeighborhood(neighborhood);
//				}
//				if (breadcrumb.size() > 4) {
//					String street = breadcrumb.get(4).childNode(0).outerHtml();
//					to.setStreet(street);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("Error extracting breadcrumb", e);
//		}
//		return to;
//	}
//
//	public PriceWithCurrencyTO extractPriceAndCurrency(Document doc) {
//		PriceWithCurrencyTO to = new PriceWithCurrencyTO();
//		try {
//			Elements elem = doc.select("strong[data-cy=\"adPageHeaderPrice\"]");
//			if (elem.first() != null) {
//				Node node = elem.first().childNode(0);
//				String val = node.outerHtml();
//				String priceAttr = val.trim().substring(0, val.lastIndexOf(' '));
//				String currencyAttr = val.substring(val.lastIndexOf(' ') + 1);
//				Double price = Double.parseDouble(priceAttr.replaceAll(" ", ""));
//				to.setPrice(price);
//				String currency;
//				switch (currencyAttr) {
//				case "€":
//					currency = "EUR";
//					break;
//				case "$":
//					currency = "USD";
//					break;
//				default:
//					currency = "RON";
//				}
//				to.setCurrency(currency);
//			}
//		} catch (Exception e) {
//			logger.error("Error extracting breadcrumb", e);
//		}
//		return to;
//	}
//
//	public GeneralInfoTO extractGeneralInfo(Document doc) {
//
//		GeneralInfoTO info = new GeneralInfoTO();
//		try {
//			Elements elements = doc.select("div[data-testid=\"ad.top-information.table\"]");
//			if (elements.first() != null) {
//				List<Node> nodes = elements.first().childNode(3).childNodes().stream()
//						.filter(t -> t.hasAttr("aria-label")).collect(Collectors.toList());
//
//				nodes.stream().forEach(node -> {
//					String attr = node.attr("aria-label").toLowerCase();
//					Node childNode = null;
//					if (node.childNodes().size() >= 3 && node.childNode(2) != null
//							&& node.childNode(2).childNode(0) != null
//							&& !node.childNode(2).childNode(0).childNodes().isEmpty()) {
//						childNode = node.childNode(2).childNode(0).childNode(0);
//					}
//					if (node.childNodes().size() >= 5 && node.childNode(4) != null
//							&& node.childNode(4).childNode(1) != null
//							&& !node.childNode(4).childNode(1).childNodes().isEmpty()) {
//						childNode = node.childNode(4).childNode(1).childNode(0);
//					}
//					switch (attr) {
//
//					case "suprafata construita":
//						generalDom.handleGeneralSuprafataConstruita(info, childNode);
//						break;
//
//					case "suprafata teren (m²)":
//						generalDom.handleGeneralSuprafataTeren(info, childNode);
//						break;
//
//					case "suprafata utila":
//						generalDom.handleGeneralSuprafataUtila(info, childNode);
//						break;
//
//					case "numarul de camere":
//						generalDom.handleGeneralNumarulDeCamere(info, childNode);
//						break;
//
//					case "numarul de bai":
//						generalDom.handleGeneralNumarulDeBai(info, childNode);
//						break;
//
//					case "stare":
//						generalDom.handleGeneralStare(info, childNode);
//						break;
//
//					case "orientare":
//						generalDom.handleGeneralOrientare(info, childNode);
//						break;
//
//					case "etaj":
//						generalDom.handleGenericEtaj(info, childNode);
//						break;
//
//					case "facilitati exterioare":
//						generalDom.handleGenericFacilitatiExterioare(info, childNode);
//						break;
//
//					case "tip proprietate":
//						generalDom.handleGeneralTipProprietate(info, childNode);
//						break;
//
//					case "compartimentare":
//						generalDom.handleGenericCompartimentare(info, childNode);
//						break;
//
//					case "anul constructiei":
//						generalDom.handleGeneralAnulConstructiei(info, childNode);
//						break;
//
//					case "garaj/loc de parcare":
//						generalDom.handleGeneralParcare(info, node);
//						break;
//
//					case "vizionare la distanta":
//						break;
//
//					default:
//						logger.debug("ignoring property [" + attr + "]");
//						break;
//					}
//				});
//			}
//		} catch (Exception e) {
//			logger.error("Error getting [extractGeneralInfo]", e);
//		}
//		return info;
//	}
//
//	public ParticularitiesTO extractParticularities(Document doc) {
//		ParticularitiesTO result = new ParticularitiesTO();
//		try {
//			Elements elem = doc.select("div[data-testid=\"ad.additional-information.table\"]");
//			if (elem.first() != null) {
//				List<Node> nodes = elem.first().childNode(2).childNodes().stream().filter(t -> t.hasAttr("aria-label"))
//						.collect(Collectors.toList());
//
//				nodes.stream().forEach(node -> {
//					String attr = node.attr("aria-label").toLowerCase();
//					switch (attr) {
//
//					case "anul constructiei":
//						particsDom.handleParticsAnulConstructiei(result, node);
//						break;
//
//					case "numarul de bai":
//						particsDom.handleParticsNumarulDeBai(result, node);
//						break;
//
//					case "tip locuinta":
//						particsDom.handleParticsTipLocuinta(result, node);
//						break;
//
//					case "tip vanzator":
//						particsDom.handleParticsTipVanzator(result, node);
//						break;
//
//					case "compartimentare":
//						particsDom.handleParticsCompartimentare(result, node);
//						break;
//
//					case "infrastructura":
//						particsDom.handleParticsInfrastructura(result, node);
//						break;
//
//					case "comoditati":
//						particsDom.handleParticsComoditati(result, node);
//						break;
//
//					case "caracteristici":
//						particsDom.handleParticsCaracteristici(result, node);
//						break;
//
//					case "imprejurimi":
//						particsDom.handleParticsImprejurimi(result, node);
//						break;
//
//					case "siguranta":
//						particsDom.handleParticsSiguranta(result, node);
//						break;
//
//					case "lift":
//						particsDom.handleParticsLift(result, node);
//						break;
//
//					case "tip oferta":
//						particsDom.handleParticsTipOferta(result, node);
//						break;
//
//					default:
//						logger.debug("ignoring property [" + attr + "]");
//						break;
//					}
//				});
//			}
//		} catch (Exception e) {
//			logger.error("Error getting [extractParticularities]", e);
//		}
//		return result;
//	}
//
//	public GpsPositionTO extractGpsPosition(Document doc) {
//		GpsPositionTO gps = new GpsPositionTO();
//		try {
//			Elements elem = doc.select("script[id=\"__NEXT_DATA__\"]");
//			Node first = elem.first().childNode(0);
//			String data = first.attr("data");
//			
//			String start = "\"location\":{\"coordinates\":";
//			String latitude = "\"latitude\"";
//			String longitude = "\"longitude\"";
//			
//			if (data.contains(start)) {
//				
//				data = data.substring(data.indexOf(start) + start.length());
//				if (data.contains(latitude)) {
//					
//					data = data.substring(data.indexOf(latitude) + latitude.length());
//					String lat = data.substring(0, data.indexOf("\""));
//					lat = lat.replaceAll("[^0-9-.]", "");
//					gps.setLatitude(Double.parseDouble(lat));
//					if (data.contains(longitude)) {
//						
//						data = data.substring(data.indexOf(longitude) + longitude.length());
//						String lng = data.substring(0, data.indexOf("\""));
//						lng = lng.replaceAll("[^0-9-.]", "");
//						gps.setLongiture(Double.parseDouble(lng));
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.error("Error getting [extractGpsPosition]", e);
//		}
//		return gps;
//	}
//	
//	public String extractPictures(Document doc) {
//		try {
//			Elements elem = doc.select("[class]");
//			if (!elem.isEmpty()) {
//				Elements imgSrcs = elem.select("img");
//				String picturesAsArray = imgSrcs.stream()
//						.filter(node -> !org.apache.commons.lang3.StringUtils.isBlank(node.attr("src")))
//						.map(node -> node.attr("src"))
//						.collect(Collectors.joining(","));
//
//				return picturesAsArray;
//			}
//		} catch (Exception e) {
//			logger.error("Error getting [extractPictures]", e);
//			return "";
//		}
//		return "";
//	}
}
