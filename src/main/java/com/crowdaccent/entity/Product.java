package com.crowdaccent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.transaction.annotation.Transactional;

/**
 * Product Entity.
 * 
 * @author kbhalla
 * 
 */
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 200)
	private String subject;
	@Column(length = 512)
	private String imageURL;
	@Column(length = 512)
	private String category;
	@Column(columnDefinition = "TEXT")
	private String summary;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date hitCreated;
	@Column(length = 512)
	private String hitURL;

	@PrePersist
	protected void onCreate() {
		dateCreated = new Date();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL
	 *            the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the hitCreated
	 */
	public Date getHitCreated() {
		return hitCreated;
	}

	/**
	 * @param hitCreated
	 *            the hitCreated to set
	 */
	public void setHitCreated(Date hitCreated) {
		this.hitCreated = hitCreated;
	}

	/**
	 * @return the hitURL
	 */
	public String getHitURL() {
		return hitURL;
	}

	/**
	 * @param hitURL
	 *            the hitURL to set
	 */
	public void setHitURL(String hitURL) {
		this.hitURL = hitURL;
	}
}
