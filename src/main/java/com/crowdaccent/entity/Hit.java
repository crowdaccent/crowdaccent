package com.crowdaccent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

/**
 * HIT Entity.
 * 
 * @author mkutare
 * 
 */
@Entity
@Table(appliesTo="Hit", indexes = { @Index(name="IDX_HIT_ID", columnNames={"hit_id"} ) } )
public class Hit {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Task task;
		
	@Column(length = 200, nullable = false)
	private String hit_id;
	
	@Column(length = 512)
	private String hit_type_id;

	@Column(length = 512)
    private String hit_url;

	@Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date creation_time;
	
	@Column(length = 512)
	private String title;
	@Column(columnDefinition = "TEXT")
	private String description;
    @Column(length = 512)
    private String keywords;
    @Column(length = 512)
    private String hit_status;
   
    @Column
    private Double reward;
    @Column
    private Integer life_time_in_secs;
    @Column
    private Integer max_assignments;
    @Column
    private Integer auto_approval_delay_in_secs;
    @Column
    private Integer num_similar_hits;
    
    @Column(length = 512)
    private String hit_review_status;
    
    @Column
    private Integer number_of_assignments_pending;
    @Column
    private Integer number_of_assignments_available;
    @Column
    private Integer number_of_assignments_completed;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the hit_id
     */
    public String getHit_id() {
        return hit_id;
    }
    /**
     * @param hit_id the hit_id to set
     */
    public void setHit_id(String hit_id) {
        this.hit_id = hit_id;
    }
    /**
     * @return the hit_type_id
     */
    public String getHit_type_id() {
        return hit_type_id;
    }
    /**
     * @param hit_type_id the hit_type_id to set
     */
    public void setHit_type_id(String hit_type_id) {
        this.hit_type_id = hit_type_id;
    }
    /**
     * @return the hit_url
     */
    public String getHit_url() {
        return hit_url;
    }
    /**
     * @param hit_url the hit_url to set
     */
    public void setHit_url(String hit_url) {
        this.hit_url = hit_url;
    }
    /**
     * @return the creation_time
     */
    public Date getCreation_time() {
        return creation_time;
    }
    /**
     * @param creation_time the creation_time to set
     */
    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }
    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    /**
     * @return the hit_status
     */
    public String getHit_status() {
        return hit_status;
    }
    /**
     * @param hit_status the hit_status to set
     */
    public void setHit_status(String hit_status) {
        this.hit_status = hit_status;
    }
    /**
     * @return the reward
     */
    public Double getReward() {
        return reward;
    }
    /**
     * @param reward the reward to set
     */
    public void setReward(Double reward) {
        this.reward = reward;
    }
    /**
     * @return the life_time_in_secs
     */
    public Integer getLife_time_in_secs() {
        return life_time_in_secs;
    }
    /**
     * @param life_time_in_secs the life_time_in_secs to set
     */
    public void setLife_time_in_secs(Integer life_time_in_secs) {
        this.life_time_in_secs = life_time_in_secs;
    }
    /**
     * @return the max_assignments
     */
    public Integer getMax_assignments() {
        return max_assignments;
    }
    /**
     * @param max_assignments the max_assignments to set
     */
    public void setMax_assignments(Integer max_assignments) {
        this.max_assignments = max_assignments;
    }
    /**
     * @return the auto_approval_delay_in_secs
     */
    public Integer getAuto_approval_delay_in_secs() {
        return auto_approval_delay_in_secs;
    }
    /**
     * @param auto_approval_delay_in_secs the auto_approval_delay_in_secs to set
     */
    public void setAuto_approval_delay_in_secs(Integer auto_approval_delay_in_secs) {
        this.auto_approval_delay_in_secs = auto_approval_delay_in_secs;
    }
    /**
     * @return the num_similar_hits
     */
    public Integer getNum_similar_hits() {
        return num_similar_hits;
    }
    /**
     * @param num_similar_hits the num_similar_hits to set
     */
    public void setNum_similar_hits(Integer num_similar_hits) {
        this.num_similar_hits = num_similar_hits;
    }
    /**
     * @return the hit_review_status
     */
    public String getHit_review_status() {
        return hit_review_status;
    }
    /**
     * @param hit_review_status the hit_review_status to set
     */
    public void setHit_review_status(String hit_review_status) {
        this.hit_review_status = hit_review_status;
    }
    /**
     * @return the number_of_assignments_pending
     */
    public Integer getNumber_of_assignments_pending() {
        return number_of_assignments_pending;
    }
    /**
     * @param number_of_assignments_pending the number_of_assignments_pending to set
     */
    public void setNumber_of_assignments_pending(Integer number_of_assignments_pending) {
        this.number_of_assignments_pending = number_of_assignments_pending;
    }
    /**
     * @return the number_of_assignments_available
     */
    public Integer getNumber_of_assignments_available() {
        return number_of_assignments_available;
    }
    /**
     * @param number_of_assignments_available the number_of_assignments_available to set
     */
    public void setNumber_of_assignments_available(Integer number_of_assignments_available) {
        this.number_of_assignments_available = number_of_assignments_available;
    }
    /**
     * @return the number_of_assignments_completed
     */
    public Integer getNumber_of_assignments_completed() {
        return number_of_assignments_completed;
    }
    /**
     * @param number_of_assignments_completed the number_of_assignments_completed to set
     */
    public void setNumber_of_assignments_completed(Integer number_of_assignments_completed) {
        this.number_of_assignments_completed = number_of_assignments_completed;
    }
	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}
}
