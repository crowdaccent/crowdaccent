package com.crowdaccent.constraint;

/**
 * Provides estimates for cost and speed when accuracy is constraint for micro tasks
 * @author mkutare
 *
 */
public class Accuracy {
	
	private int accuracyTarget = -1;
	private int observedTimePerHit = -1;
	private double rewardPerHit = 0.0;
	private int totalJudgements = 0;
	private double humanAccuracy = 0.0;
	private double machineAccuracy = 0.0;

	
	public Accuracy (int accuracyTarget, int observedTimePerHit, double rewardPerHit, int totalJudgements, int totalHumanJudgements, int humanAccuracy, int machineAccuracy) {
		this.accuracyTarget = accuracyTarget;
		this.observedTimePerHit = observedTimePerHit;
		this.rewardPerHit = rewardPerHit;
		this.totalJudgements = totalJudgements;
		this.machineAccuracy = machineAccuracy;
		this.humanAccuracy = humanAccuracy;
	}
	
	public double estimatedHumanLoad() {
		return this.totalJudgements * ((accuracyTarget - this.machineAccuracy) /(this.humanAccuracy - this.machineAccuracy));
	}
	
	public double estimatedCost() {
		return this.rewardPerHit * estimatedHumanLoad();
	}
	
	public double estimatedTime() {
		return this.observedTimePerHit * estimatedHumanLoad();
	}
	
	public static void main (String [] args) {
		//TODO
	}

}
