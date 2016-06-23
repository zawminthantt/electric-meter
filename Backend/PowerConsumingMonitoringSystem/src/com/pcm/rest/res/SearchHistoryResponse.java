package com.pcm.rest.res;

import java.util.List;

public class SearchHistoryResponse {
	
	private List<StatusResponse> responseData = null;

	public List<StatusResponse> getResponseData() {
		return responseData;
	}

	public void setResponseData(List<StatusResponse> responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "NotificationResponse [responseData=" + responseData + "]";
	}
	
}


