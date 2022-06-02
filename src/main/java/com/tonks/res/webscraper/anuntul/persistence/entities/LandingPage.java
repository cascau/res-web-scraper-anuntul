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

	// start seller area
	@Column(name = "seller_name")
	protected String sellerName;

	@Column(name = "seller_type")
	protected String sellerType;

	@Column(name = "seller_phone")
	protected String sellerPhone;

	// start breadcrumb area
	@Column(name = "offerType")
	protected String offerType; // vanzare/inchiriat

	@Column(name = "county")
	protected String county;

	@Column(name = "city")
	protected String city;

	@Column(name = "neighborhood")
	protected String neighborhood;

	@Column(name = "street")
	protected String street;

	// start main area
	@Column(name = "title")
	protected String title;

	@Column(name = "price")
	protected Double price;

	@Column(name = "currency", length = 3)
	protected String currency;

	@Column(name = "useable_surface")
	protected Double useableSurface; // Suprafata utila

	@Column(name = "nb_of_rooms")
	protected Integer roomsNumber; // Numarul de camere

	@Column(name = "condition")
	protected String condition; // Stare

	@Column(name = "floor_number")
	protected Integer floorNumber; // Etaj

	@Column(name = "total_floors")
	protected Integer totalFloors; // Etaje in total

	@Column(name = "building_type")
	protected String buildingType; // Tip cladire

	@Column(name = "property_type")
	protected String propertyType; // Tip proprietate

	@Column(name = "partitioning")
	protected String partitioning; // Compartimentare

	@Column(name = "year_built")
	protected Integer yearBuilt; // Anul constructiei

	// start description area
	@Column(name = "description", length = 15000)
	protected String description; // Descrierea generala

	@Column(name = "negociable")
	protected String negociable; // Tip oferta

	@Column(name = "pictures", length = 16000)
	protected String pictures;

	@Column(name = "latitude")
	protected Double latitude;

	@Column(name = "longitude")
	protected Double longitude;

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

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTitle() {
		return title;
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

	public Integer getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(Integer roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(Integer totalFloors) {
		this.totalFloors = totalFloors;
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

	public String getNegociable() {
		return negociable;
	}

	public void setNegociable(String negociable) {
		this.negociable = negociable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, condition, county, currency, dateAdded, description, floorNumber, latitude, longitude,
				negociable, neighborhood, offerType, partitioning, pictures, price, propertyType, roomsNumber,
				sellerName, sellerPhone, sellerType, street, title, totalFloors, url, useableSurface, yearBuilt);
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