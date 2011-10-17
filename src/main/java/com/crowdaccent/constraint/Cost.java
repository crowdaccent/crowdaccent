package com.crowdaccent.constraint;

/**
 * Provides estimates for speed and accuracy when cost is constraint for micro tasks
 * @author mkutare
 *
 */
public class Cost {

	private int costTarget = -1;
	private int observedTimeForHits = -1;
	private double rewardPerHit = 0.0;
	private int totalHumanJudgements = 0;
	private int totalJudgements = 0;
	private double humanAccuracy = 0.0;
	private double machineAccuracy = 0.0;

	public Cost (int costTarget, int observedTimeForHits, double rewardPerHit, int totalJudgements, int totalHumanJudgements, int humanAccuracy, int machineAccuracy) {
		this.costTarget = costTarget;
		this.observedTimeForHits = observedTimeForHits;
		this.rewardPerHit = rewardPerHit;
		this.totalHumanJudgements = totalHumanJudgements;
		this.totalJudgements = totalJudgements;
		this.machineAccuracy = machineAccuracy;
		this.humanAccuracy = humanAccuracy;
	}
	
	public double estimatedHumanLoad() {
		return this.costTarget/rewardPerHit;
	}
	
	public double estimatedTime() {
		return this.observedTimeForHits * estimatedHumanLoad();
	}
	
	public double estimatedAccuracy() {
		return (this.humanAccuracy * this.totalHumanJudgements) + (this.machineAccuracy * (this.totalJudgements - this.totalHumanJudgements));
	}
	
	public static void main (String [] args) {
		//TODO
	}
	
}
