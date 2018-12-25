package com.mac.springboot.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marco
 */
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private String userMail;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@OneToMany(mappedBy = "ordine")
	private Set<Detail> produtcs;

	public Set<Detail> getProdutcs() {
		return produtcs;
	}

	public void setProdutcs(Set<Detail> produtcs) {
		this.produtcs = produtcs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}