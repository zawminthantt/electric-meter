package com.pcm.dao;

import java.io.Serializable;
import java.util.Date;

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955618378954024295L;

	private int locationId;
	private String zipCode;
	private Date createDate;
	
	public Location() {
		super();
	}
	public Location(String zipCode) {
		super();
		this.zipCode = zipCode;
	}
	public Location(int locationId, String zipCode, Date createDate) {
		super();
		this.locationId = locationId;
		this.zipCode = zipCode;
		this.createDate = createDate;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + locationId;
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (locationId != other.locationId)
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", zipCode=" + zipCode + ", createDate=" + createDate + "]";
	}
}
