package com.crowdaccent.orchestration.gateway;

public class HITResponse {
	
	private HIT syncResp = null;
	private HIT asyncResp = null;

	public HIT getSyncResp() {
		return syncResp;
	}

	public void setSyncResp(HIT hit) {
		this.syncResp = hit;
	}
	
	public HIT getAsyncResp() {
		return asyncResp;
	}

	public void setAsyncResp(HIT hit) {
		this.asyncResp = hit;
	}
	

}
