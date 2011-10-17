package com.crowdaccent.constraint;

/**
 * Provides estimates for cost and accuracy when speed is constraint for micro tasks
 * @author mkutare
 *
 */
public class Speed {
	
	private int timeTarget = -1;
	private int observedTimePerHit = -1;
	private double rewardPerHit = 0.0;
	private int totalHumanJudgements = 0;
	private int totalJudgements = 0;
	private double humanAccuracy = 0.0;
	private double machineAccuracy = 0.0;

	
	public Speed (int timeTarget, int observedTimePerHit, double rewardPerHit, int totalJudgements, int totalHumanJudgements, int humanAccuracy, int machineAccuracy) {
		this.timeTarget = timeTarget;
		this.observedTimePerHit = observedTimePerHit;
		this.rewardPerHit = rewardPerHit;
		this.totalHumanJudgements = totalHumanJudgements;
		this.totalJudgements = totalJudgements;
		this.machineAccuracy = machineAccuracy;
		this.humanAccuracy = humanAccuracy;
	}
	
	public int estimatedHumanLoad() {
		return this.timeTarget/observedTimePerHit;
	}
	
	public double estimatedCost() {
		return this.rewardPerHit * estimatedHumanLoad();
	}
	
	public double estimatedAccuracy() {
		return (this.humanAccuracy * this.totalHumanJudgements) + (this.machineAccuracy * (this.totalJudgements - this.totalHumanJudgements));
	}
	
	public static void main (String [] args) {
		//TODO
	}

}
