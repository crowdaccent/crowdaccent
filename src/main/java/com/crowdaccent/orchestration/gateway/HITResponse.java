package com.crowdaccent.orchestration.gateway;

import com.amazonaws.mturk.requester.HIT;

public class HITResponse {
	
	private HIT syncResp = null;
	private HIT asyncResp = null;

	public HIT getSyncResponse() {
		return syncResp;
	}

	public void setSyncResponse(HIT hit) {
		this.syncResp = hit;
	}
	
	public HIT getAsyncResponse() {
		return asyncResp;
	}

	public void setAsyncResponse(HIT hit) {
		this.asyncResp = hit;
	}

}
