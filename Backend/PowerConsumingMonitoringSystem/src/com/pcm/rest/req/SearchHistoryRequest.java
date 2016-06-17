package com.pcm.rest.req;

public class SearchHistoryRequest extends PCMRequest {

	private String meterId;
	private String locationId;
	private String from;
	private String to;
	private String zipCode;
	
	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "SearchHistoryRequest [meterId=" + meterId + ", locationId=" + locationId + ", from=" + from + ", to="
				+ to + ", zipCode=" + zipCode + "]";
	}
}
