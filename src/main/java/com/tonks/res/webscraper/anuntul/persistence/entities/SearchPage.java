package com.tonks.res.webscraper.anuntul.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "search_page")
public class SearchPage implements Cloneable, Serializable {

	private static final long serialVersionUID = 4272768832814458652L;

	@Id
	@Column(name = "url")
	protected String url;

	@Column(name = "date_added")
	protected Date dateAdded;

	@Column(name = "source")
	protected final String source = "anuntul";

	public SearchPage() {
	}

	public SearchPage(String pageLink, Date dateAdded) {
		this.url = pageLink;
		this.dateAdded = dateAdded;
		if (this.url.indexOf('#') > 0) {
			this.url = this.url.substring(0, this.url.indexOf('#'));
		}
		if (this.url.indexOf('?') > 0) {
			this.url = this.url.substring(0, this.url.indexOf('?'));
		}
	}

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

	public String getSource() {
		return source;
	}

	@Override
	public boolean equals(Object obj) {
		if (!Objects.isNull(obj) && obj instanceof SearchPage) {
			SearchPage that = (SearchPage) obj;
			return Objects.equals(this.url, that.url) && Objects.equals(this.source, that.source);
		}
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new SearchPage(this.url, this.dateAdded);
	}

	@Override
	public int hashCode() {
		final int prime = 19;
		int hash = 0;
		hash = prime * hash + (this.url == null ? 0 : this.url.hashCode());
		hash = prime * hash + (this.source == null ? 0 : this.source.hashCode());
		return hash;
	}

}
