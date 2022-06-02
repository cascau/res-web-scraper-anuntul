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
@Table(name = "seller")
public class Seller implements Cloneable, Serializable {

	private static final long serialVersionUID = 1324468644835933356L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "offer_url", referencedColumnName = "url")
	protected Offer offer;

	public Seller() {
	}

	public Seller(String name, String phone, String type) {
		this.name = name;
		this.phone = phone;
		this.type = type;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, phone, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (!Objects.isNull(obj) && obj instanceof Seller) {
			Seller other = (Seller) obj;
			return Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
					&& Objects.equals(type, other.type);
		}
		return false;
	}
}
