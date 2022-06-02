package com.tonks.res.webscraper.anuntul.persistence.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class Property implements Cloneable, Serializable {

	private static final long serialVersionUID = 8254933030772943075L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	@Column(name = "type")
	protected String type;

	@Column(name = "city")
	protected String city;

	@Column(name = "county")
	protected String county;

	@Column(name = "neighborhood")
	protected String neighborhood;

	@Column(name = "partitioning")
	protected String partitioning;

	@Column(name = "floor_number")
	protected Integer floorNumber;

	@Column(name = "total_floors")
	protected Integer totalFloors;

	@Column(name = "nb_of_rooms")
	protected Integer roomsNumber;

	@Column(name = "year_built")
	private Integer yearBuilt;

	@Column(name = "useable_surface")
	protected Double useableSurface;

	@Column(name = "condition")
	protected String condition; // stare proprietate

	@Column(name = "latitude")
	protected Double latitude;

	@Column(name = "longitude")
	protected Double longitude;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "offer_url", referencedColumnName = "url")
	protected Offer offer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPartitioning() {
		return partitioning;
	}

	public void setPartitioning(String partitioning) {
		this.partitioning = partitioning;
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

	public Integer getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(Integer roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(Integer year) {
		this.yearBuilt = year;
	}

	public Double getUseableSurface() {
		return useableSurface;
	}

	public void setUseableSurface(Double useableSurface) {
		this.useableSurface = useableSurface;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, condition, county, floorNumber, latitude, longitude, neighborhood, partitioning,
				roomsNumber, totalFloors, type, useableSurface, yearBuilt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		return Objects.equals(city, other.city) && Objects.equals(condition, other.condition)
				&& Objects.equals(county, other.county) && Objects.equals(floorNumber, other.floorNumber)
				&& Objects.equals(latitude, other.latitude) && Objects.equals(longitude, other.longitude)
				&& Objects.equals(neighborhood, other.neighborhood) && Objects.equals(partitioning, other.partitioning)
				&& Objects.equals(roomsNumber, other.roomsNumber) && Objects.equals(totalFloors, other.totalFloors)
				&& Objects.equals(type, other.type) && Objects.equals(useableSurface, other.useableSurface)
				&& Objects.equals(yearBuilt, other.yearBuilt);
	}
}
