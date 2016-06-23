package com.pcm.rest.res;

import java.util.List;

import com.pcm.dao.Meter;

public class LoadMetersResponse {
	
	private List<ResponseData> responseData = null;

	public List<ResponseData> getResponseData() {
		return responseData;
	}

	public void setResponseData(List<ResponseData> responseData) {
		this.responseData = responseData;
	}
	
	public static class ResponseData {
		
		private String locationId;
		private String zipCode;
		private List<Meter> meters;
		
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

		public List<Meter> getMeters() {
			return meters;
		}

		public void setMeters(List<Meter> meters) {
			this.meters = meters;
		}

		public ResponseData(String locationId, String zipCode, List<Meter> meters) {
			super();
			this.locationId = locationId;
			this.zipCode = zipCode;
			this.meters = meters;
		}
	}
}


