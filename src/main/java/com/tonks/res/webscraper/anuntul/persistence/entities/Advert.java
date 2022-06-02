package com.tonks.res.webscraper.anuntul.persistence.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "advert")
public class Advert implements Cloneable, Serializable {

	private static final long serialVersionUID = -2327904238539653933L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	@Column(name = "type")
	protected String type;

	@Column(name = "price")
	protected Double price;

	@Column(name = "currency")
	protected String currency;

	@Column(name = "title", length = 512)
	protected String title;

	@Column(name = "description", length = 15000)
	protected String description;

	@Column(name = "pictures", length = 16000)
	protected String pictures;

	@Column(name = "date_added")
	protected Date dateAdded;

	@Column(name = "date_sold")
	protected Date dateSold;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public Date getDateSold() {
		return dateSold;
	}

	public void setDateSold(Date dateSold) {
		this.dateSold = dateSold;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, dateAdded, dateSold, description, pictures, price, title, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advert other = (Advert) obj;
		return Objects.equals(currency, other.currency) && Objects.equals(dateAdded, other.dateAdded)
				&& Objects.equals(dateSold, other.dateSold) && Objects.equals(description, other.description)
				&& Objects.equals(pictures, other.pictures) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title) && Objects.equals(type, other.type);
	}
}
