package com.crowdaccent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Result Entity.
 * 
 * @author mkutare
 * 
 */
@Entity
public class Result {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 256)
	private String hit_id;
	@Column(length = 512)
	private String hit_type_id;
    @Column(columnDefinition = "TEXT")
	private String title;
	@Column(columnDefinition = "TEXT")
	private String description;
    @Column(length = 512)
    private String keywords;
    @Column(length = 512)
    private Double reward;    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date creation_time;
    @Column(length = 512)
    private Integer max_assignments;
    @Column(length = 512)
    private Integer auto_approval_delay_in_secs;
    @Column
    private Integer assignment_duration_in_secs;
    //Data from Assignment data structure
    @Column(length = 512)
    private Integer num_similar_hits;
    @Column(length = 512)
    private Integer life_time_in_secs;
    //Extra data apart from Assignment and HIT data structure
    @Column(length = 512)
    private String requester_annotation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date expiration_time;
    // Data from Assignment data structure
    @Column(length = 512)
    private String assignment_id;
    @Column(length = 512)
    private String worker_id;
    @Column(length = 512)
    private String assignment_status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date accept_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date submit_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date auto_approval_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date approval_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date rejection_time;
    @Column(length = 512)
    private String requester_feedback;
    //Data for worker
    @Column(length = 512)
    private Integer work_time_in_secs;
    @Column(length = 512)
    private String life_time_approval_rate;
    @Column(length = 512)
    private String last_30_days_approval_rate;
    @Column(length = 512)
    private String last_7_days_approval_rate;
    //Data for answers
    @Column(length = 512)
    private String answers;
    @Column(length = 512)
    private String hit_status;
    @Column(length = 512)
    private String hit_review_status;
    @Column(length = 512)
    private Integer number_of_assignments_pending;
    @Column(length = 512)
    private Integer number_of_assignments_available;
    @Column(length = 512)
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
     * @return the requester_annotation
     */
    public String getRequester_annotation() {
        return requester_annotation;
    }
    /**
     * @param requester_annotation the requester_annotation to set
     */
    public void setRequester_annotation(String requester_annotation) {
        this.requester_annotation = requester_annotation;
    }
    /**
     * @return the expiration_time
     */
    public Date getExpiration_time() {
        return expiration_time;
    }
    /**
     * @param expiration_time the expiration_time to set
     */
    public void setExpiration_time(Date expiration_time) {
        this.expiration_time = expiration_time;
    }
    /**
     * @return the assignment_id
     */
    public String getAssignment_id() {
        return assignment_id;
    }
    /**
     * @param assignment_id the assignment_id to set
     */
    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }
    /**
     * @return the worker_id
     */
    public String getWorker_id() {
        return worker_id;
    }
    /**
     * @param worker_id the worker_id to set
     */
    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }
    /**
     * @return the assignment_status
     */
    public String getAssignment_status() {
        return assignment_status;
    }
    /**
     * @param assignment_status the assignment_status to set
     */
    public void setAssignment_status(String assignment_status) {
        this.assignment_status = assignment_status;
    }
    /**
     * @return the accept_time
     */
    public Date getAccept_time() {
        return accept_time;
    }
    /**
     * @param accept_time the accept_time to set
     */
    public void setAccept_time(Date accept_time) {
        this.accept_time = accept_time;
    }
    /**
     * @return the submit_time
     */
    public Date getSubmit_time() {
        return submit_time;
    }
    /**
     * @param submit_time the submit_time to set
     */
    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }
    /**
     * @return the auto_approval_time
     */
    public Date getAuto_approval_time() {
        return auto_approval_time;
    }
    /**
     * @param auto_approval_time the auto_approval_time to set
     */
    public void setAuto_approval_time(Date auto_approval_time) {
        this.auto_approval_time = auto_approval_time;
    }
    /**
     * @return the assignment_duration_in_secs
     */
    public Integer getAssignment_duration_in_secs() {
        return assignment_duration_in_secs;
    }
    /**
     * @param assignment_duration_in_secs the assignment_duration_in_secs to set
     */
    public void setAssignment_duration_in_secs(Integer assignment_duration_in_secs) {
        this.assignment_duration_in_secs = assignment_duration_in_secs;
    }
    /**
     * @return the approval_time
     */
    public Date getApproval_time() {
        return approval_time;
    }
    /**
     * @param approval_time the approval_time to set
     */
    public void setApproval_time(Date approval_time) {
        this.approval_time = approval_time;
    }
    /**
     * @return the rejection_time
     */
    public Date getRejection_time() {
        return rejection_time;
    }
    /**
     * @param rejection_time the rejection_time to set
     */
    public void setRejection_time(Date rejection_time) {
        this.rejection_time = rejection_time;
    }
    /**
     * @return the requester_feedback
     */
    public String getRequester_feedback() {
        return requester_feedback;
    }
    /**
     * @param requester_feedback the requester_feedback to set
     */
    public void setRequester_feedback(String requester_feedback) {
        this.requester_feedback = requester_feedback;
    }
    /**
     * @return the work_time_in_secs
     */
    public Integer getWork_time_in_secs() {
        return work_time_in_secs;
    }
    /**
     * @param work_time_in_secs the work_time_in_secs to set
     */
    public void setWork_time_in_secs(Integer work_time_in_secs) {
        this.work_time_in_secs = work_time_in_secs;
    }
    /**
     * @return the life_time_approval_rate
     */
    public String getLife_time_approval_rate() {
        return life_time_approval_rate;
    }
    /**
     * @param life_time_approval_rate the life_time_approval_rate to set
     */
    public void setLife_time_approval_rate(String life_time_approval_rate) {
        this.life_time_approval_rate = life_time_approval_rate;
    }
    /**
     * @return the last_30_days_approval_rate
     */
    public String getLast_30_days_approval_rate() {
        return last_30_days_approval_rate;
    }
    /**
     * @param last_30_days_approval_rate the last_30_days_approval_rate to set
     */
    public void setLast_30_days_approval_rate(String last_30_days_approval_rate) {
        this.last_30_days_approval_rate = last_30_days_approval_rate;
    }
    /**
     * @return the last_7_days_approval_rate
     */
    public String getLast_7_days_approval_rate() {
        return last_7_days_approval_rate;
    }
    /**
     * @param last_7_days_approval_rate the last_7_days_approval_rate to set
     */
    public void setLast_7_days_approval_rate(String last_7_days_approval_rate) {
        this.last_7_days_approval_rate = last_7_days_approval_rate;
    }
    /**
     * @return the answers
     */
    public String getAnswers() {
        return answers;
    }
    /**
     * @param answers the answers to set
     */
    public void setAnswers(String answers) {
        this.answers = answers;
    }

}
