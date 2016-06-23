package com.pcm.service;

import com.pcm.dao.Location;
import com.pcm.dao.Meter;
import com.pcm.dao.MeterData;
import com.pcm.daoservice.LocationDAO;
import com.pcm.daoservice.MeterDAO;
import com.pcm.daoservice.MeterDataDAO;

public class EntryServiceImpl implements EntryService {
	
	private LocationDAO locationDAO;
	private MeterDAO meterDAO;
	private MeterDataDAO meterDataDAO;

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public MeterDAO getMeterDAO() {
		return meterDAO;
	}

	public void setMeterDAO(MeterDAO meterDAO) {
		this.meterDAO = meterDAO;
	}

	public MeterDataDAO getMeterDataDAO() {
		return meterDataDAO;
	}

	public void setMeterDataDAO(MeterDataDAO meterDataDAO) {
		this.meterDataDAO = meterDataDAO;
	}
	
	@Override
	public boolean insertData(String zipCode
								, int meterId
								, String power
								, String datetime
								, String current
								, String frequency
								, String voltage
								, String breakerState) {
		
		MeterData meterData = new MeterData();
		meterData.setZipCode(zipCode);
		meterData.setMeterId(meterId);
		meterData.setMpower(power);
		meterData.setMdatetime(datetime);
		meterData.setCurrent(current);
		meterData.setFrequency(frequency);
		meterData.setVoltage(voltage);
		meterData.setBreakerState(breakerState);
		// Check location.
		Location location = locationDAO.getLocationByZipCode(meterData.getZipCode());
		if (location == null) {
			location = new Location(meterData.getZipCode());
			boolean result = locationDAO.insertLocation(location);
			if (result) {
				location = locationDAO.getLocationByZipCode(meterData.getZipCode());
			} else {
				return false;
			}
		}
		meterData.setLocationId(location.getLocationId());
		
		// Check meter.
		Meter incomingMeter = new Meter(meterData.getMeterId(), meterData.getLocationId());
		Meter meter = meterDAO.getMeterById(incomingMeter);
		if (meter == null) {
			boolean result = meterDAO.insertMeter(incomingMeter);
			if (!result) {
				return false;
			}
		}
		
		// Insert data.
		boolean result = meterDataDAO.insertMeterData(meterData);
		
		return result;
	}

}
