package com.pcm.rest.req;

public class SendDataRequest extends PCMRequest {
	
	private String zipCode;
	private String meterId;
	private String power;
	private String datetime;
	private String current;
	private String frequency;
	private String voltage;
	private String breakerState;
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
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
	@Override
	public String toString() {
		return "SendDataRequest [zipCode=" + zipCode + ", meterId=" + meterId + ", power=" + power + ", datetime="
				+ datetime + ", current=" + current + ", frequency=" + frequency + ", voltage=" + voltage
				+ ", breakerState=" + breakerState + "]";
	}
}
