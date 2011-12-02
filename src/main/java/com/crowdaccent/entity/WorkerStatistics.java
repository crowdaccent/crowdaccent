package com.crowdaccent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Worker Entity.
 * 
 * @author mkutare
 * 
 */
@Entity
public class WorkerStatistics {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 512)
	private String worker_id;
	
	@Column(length = 512)
	private String number_assignments_approved;
	
    @Column(length = 512)
    private String number_assignments_rejected;

    @Column(length = 512)
    private String percent_assignments_approved;
    
    @Column(length = 512)
    private String  percent_assignments_ejected;
    
    @Column(length = 512)
    private String number_known_answers_correct;
    
    @Column(length = 512)
    private String number_known_answers_incorrect;
    
    @Column(length = 512)
    private String number_known_answers_evaluated;
    
    @Column(length = 512)
    private String PercentKnownAnswersCorrect;
    
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
     * @return the number_assignments_approved
     */
    public String getNumber_assignments_approved() {
      return number_assignments_approved;
    }
    /**
     * @param number_assignments_approved the number_assignments_approved to set
     */
    public void setNumber_assignments_approved(String number_assignments_approved) {
      this.number_assignments_approved = number_assignments_approved;
    }
    /**
     * @return the number_assignments_rejected
     */
    public String getNumber_assignments_rejected() {
      return number_assignments_rejected;
    }
    /**
     * @param number_assignments_rejected the number_assignments_rejected to set
     */
    public void setNumber_assignments_rejected(String number_assignments_rejected) {
      this.number_assignments_rejected = number_assignments_rejected;
    }
    /**
     * @return the percent_assignments_approved
     */
    public String getPercent_assignments_approved() {
      return percent_assignments_approved;
    }
    /**
     * @param percent_assignments_approved the percent_assignments_approved to set
     */
    public void setPercent_assignments_approved(String percent_assignments_approved) {
      this.percent_assignments_approved = percent_assignments_approved;
    }
    /**
     * @return the percent_assignments_ejected
     */
    public String getPercent_assignments_ejected() {
      return percent_assignments_ejected;
    }
    /**
     * @param percent_assignments_ejected the percent_assignments_ejected to set
     */
    public void setPercent_assignments_ejected(String percent_assignments_ejected) {
      this.percent_assignments_ejected = percent_assignments_ejected;
    }
    /**
     * @return the number_known_answers_correct
     */
    public String getNumber_known_answers_correct() {
      return number_known_answers_correct;
    }
    /**
     * @param number_known_answers_correct the number_known_answers_correct to set
     */
    public void setNumber_known_answers_correct(String number_known_answers_correct) {
      this.number_known_answers_correct = number_known_answers_correct;
    }
    /**
     * @return the number_known_answers_incorrect
     */
    public String getNumber_known_answers_incorrect() {
      return number_known_answers_incorrect;
    }
    /**
     * @param number_known_answers_incorrect the number_known_answers_incorrect to set
     */
    public void setNumber_known_answers_incorrect(
        String number_known_answers_incorrect) {
      this.number_known_answers_incorrect = number_known_answers_incorrect;
    }
    /**
     * @return the number_known_answers_evaluated
     */
    public String getNumber_known_answers_evaluated() {
      return number_known_answers_evaluated;
    }
    /**
     * @param number_known_answers_evaluated the number_known_answers_evaluated to set
     */
    public void setNumber_known_answers_evaluated(
        String number_known_answers_evaluated) {
      this.number_known_answers_evaluated = number_known_answers_evaluated;
    }
    /**
     * @return the percentKnownAnswersCorrect
     */
    public String getPercentKnownAnswersCorrect() {
      return PercentKnownAnswersCorrect;
    }
    /**
     * @param percentKnownAnswersCorrect the percentKnownAnswersCorrect to set
     */
    public void setPercentKnownAnswersCorrect(String percentKnownAnswersCorrect) {
      PercentKnownAnswersCorrect = percentKnownAnswersCorrect;
    }
}
