package com.crowdaccent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Behavior Entity.
 * 
 * @author mkutare
 * 
 */
@Entity
@DiscriminatorValue("behavior")
public class Behavior extends Task{
	@Column(length = 512)
	private String key;
	@Column(length = 512)
	private String value;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date behavior_time;
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param value
	 * the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 * the value to set
	 */
	public void setSummary(String value) {
		this.value = value;
	}

	/**
	 * @return the behavior time
	 */
	public Date getBehaviorTime() {
		return behavior_time;
	}

	/**
	 * @param behavior_time
	 * the behavior time to set
	 */
	public void setImageURL(Date behaviorTime) {
		this.behavior_time = behaviorTime;
	}

}
