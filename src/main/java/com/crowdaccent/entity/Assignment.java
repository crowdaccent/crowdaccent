package com.crowdaccent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Assignment Entity.
 * 
 * @author mkutare
 * 
 */
@Entity
public class Assignment {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Hit hit;

	@Column(length = 512)
	private String assignment_id;
	@Column(length = 512)
	private String worker_id;
	@Column(length = 512)
	private String hitID;
    @Column(length = 512)
    private String assignment_status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date auto_approval_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date accept_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date submit_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date approval_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date rejection_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date deadline;
    @Column(length = 512)
    private String requester_feedback;

    @Column(length = 512)
    private String answer;

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
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }
    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
	 * @return the hit
	 */
	public Hit getHit() {
		return hit;
	}
	/**
	 * @param hit the hit to set
	 */
	public void setHit(Hit hit) {
		this.hit = hit;
	}
	/**
	 * @return the hitID
	 */
	public String getHitID() {
		return hitID;
	}
	/**
	 * @param hitID the hitID to set
	 */
	public void setHitID(String hitID) {
		this.hitID = hitID;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
