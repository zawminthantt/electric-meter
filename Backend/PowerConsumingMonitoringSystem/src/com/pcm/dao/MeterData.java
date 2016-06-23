package com.pcm.dao;

import java.io.Serializable;
import java.util.Date;

public class MeterData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7310183639031839610L;

	private int id;
	private int meterId;
	private int locationId;
	private String zipCode;
	private String mpower;
	private String mdatetime;
	private String current;
	private String frequency;
	private String voltage;
	private String breakerState;
	private Date createDate;
	private String from;
	private String to;
	
	public MeterData() {
		super();
	}
	public MeterData(int meterId, int locationId) {
		super();
		this.meterId = meterId;
		this.locationId = locationId;
	}
	public MeterData(int meterId, int locationId, String from, String to) {
		super();
		this.meterId = meterId;
		this.locationId = locationId;
		this.from = from;
		this.to = to;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMeterId() {
		return meterId;
	}
	public void setMeterId(int meterId) {
		this.meterId = meterId;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getBreakerState() {
		return breakerState;
	}
	public void setBreakerState(String breakerState) {
		this.breakerState = breakerState;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
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
	public String getMpower() {
		return mpower;
	}
	public void setMpower(String mpower) {
		this.mpower = mpower;
	}
	public String getMdatetime() {
		return mdatetime;
	}
	public void setMdatetime(String mdatetime) {
		this.mdatetime = mdatetime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breakerState == null) ? 0 : breakerState.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((current == null) ? 0 : current.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + id;
		result = prime * result + locationId;
		result = prime * result + ((mdatetime == null) ? 0 : mdatetime.hashCode());
		result = prime * result + meterId;
		result = prime * result + ((mpower == null) ? 0 : mpower.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + ((voltage == null) ? 0 : voltage.hashCode());
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
		MeterData other = (MeterData) obj;
		if (breakerState == null) {
			if (other.breakerState != null)
				return false;
		} else if (!breakerState.equals(other.breakerState))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (current == null) {
			if (other.current != null)
				return false;
		} else if (!current.equals(other.current))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (id != other.id)
			return false;
		if (locationId != other.locationId)
			return false;
		if (mdatetime == null) {
			if (other.mdatetime != null)
				return false;
		} else if (!mdatetime.equals(other.mdatetime))
			return false;
		if (meterId != other.meterId)
			return false;
		if (mpower == null) {
			if (other.mpower != null)
				return false;
		} else if (!mpower.equals(other.mpower))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (voltage == null) {
			if (other.voltage != null)
				return false;
		} else if (!voltage.equals(other.voltage))
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
		return "MeterData [id=" + id + ", meterId=" + meterId + ", locationId=" + locationId + ", zipCode=" + zipCode
				+ ", mpower=" + mpower + ", mdatetime=" + mdatetime + ", current=" + current + ", frequency="
				+ frequency + ", voltage=" + voltage + ", breakerState=" + breakerState + ", createDate=" + createDate
				+ ", from=" + from + ", to=" + to + "]";
	}
}
