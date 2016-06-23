package com.pcm.dao;

import java.io.Serializable;
import java.sql.Date;

public class Meter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9181829606597572332L;

	private int meterId;
	private int locationId;
	private Date createDate;
	
	public Meter() {
		super();
	}
	public Meter(int meterId, int locationId) {
		super();
		this.meterId = meterId;
		this.locationId = locationId;
	}
	public int getMeterId() {
		return meterId;
	}
	public void setMeterId(int meterId) {
		this.meterId = meterId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
		result = prime * result + meterId;
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
		Meter other = (Meter) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (locationId != other.locationId)
			return false;
		if (meterId != other.meterId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Meter [meterId=" + meterId + ", locationId=" + locationId + ", createDate=" + createDate + "]";
	}
}
