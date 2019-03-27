package com.bfm.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "GET_TICKERS", query = "SELECT t FROM Ticker t")
@NamedQuery(name = "GET_BY_CODE", query = "SELECT t FROM Ticker t WHERE t.code = :code")
@NamedQuery(name = "SEARCH_QUERY", query = "SELECT t from Ticker t where t.name like :query or lower(t.name) like :query or t.code like :query")
public class Ticker {

	@Id
	@GeneratedValue
	private Integer id;
	private String code;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ticker [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	
}
