package com.tonks.res.webscraper.anuntul.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "landing_page_anuntul")
@Data
public class LandingPage implements Cloneable, Serializable {

	private static final long serialVersionUID = 5676967194453719529L;

	@Id
	@Column(name = "url")
	protected String url;

	@Column(name = "date_added")
	protected Date dateAdded;

	// start breadcrumb area
	@Column(name = "offerType")
	protected String offerType; // vanzare/inchiriat

	@Column(name = "county")
	protected String county;

	@Column(name = "city")
	protected String city;

	@Column(name = "neighborhood")
	protected String neighborhood;

	// start main area
	@Column(name = "title")
	protected String title;

	@Column(name = "price")
	protected Double price;

	@Column(name = "currency", length = 3)
	protected String currency;

	@Column(name = "useable_surface")
	protected Double useableSurface; // Suprafata utila

	@Column(name = "property_type")
	protected String propertyType; // Tip proprietate

	@Column(name = "partitioning")
	protected String partitioning; // Compartimentare

	@Column(name = "nb_of_rooms")
	protected Integer roomsNumber;

	@Column(name = "year_built")
	protected Integer yearBuilt; // Anul constructiei

	// start description area
	@Column(name = "description", length = 15000)
	protected String description; // Descrierea generala

	@Column(name = "available")
	protected Boolean available = true;

	@Column(name = "pictures", length = 16000)
	protected String pictures;

	@Column(name = "latitude")
	protected Double latitude;

	@Column(name = "longitude")
	protected Double longitude;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getTitle() {
		return title;
	}

	public Integer getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(Integer roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getUseableSurface() {
		return useableSurface;
	}

	public void setUseableSurface(Double useableSurface) {
		this.useableSurface = useableSurface;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPartitioning() {
		return partitioning;
	}

	public void setPartitioning(String partitioning) {
		this.partitioning = partitioning;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(Integer yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(available, city, county, currency, dateAdded, description, latitude, longitude,
				neighborhood, offerType, partitioning, pictures, price, propertyType, roomsNumber, title, url,
				useableSurface, yearBuilt);
	}

	@Override
	public boolean equals(Object obj) {
		if (!Objects.isNull(obj) && obj instanceof LandingPage) {
			LandingPage that = (LandingPage) obj;
			return Objects.equals(this.url, that.url);
		}
		return false;
	}
}