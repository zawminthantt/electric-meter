package com.pcm.rest.req;

public class LatestStatusRequest extends PCMRequest {	

	private String meterId;
	private String locationId;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "CurrentStatusRequest [meterId=" + meterId + ", locationId=" + locationId + ", zipCode=" + zipCode + "]";
	}
}
