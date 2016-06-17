package com.pcm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pcm.dao.Location;
import com.pcm.dao.Meter;
import com.pcm.dao.MeterData;
import com.pcm.daoservice.LocationDAO;
import com.pcm.daoservice.MeterDAO;
import com.pcm.daoservice.MeterDataDAO;

public class SearchServiceImpl implements SearchService {
	
	private static Logger logger = Logger.getLogger(SearchServiceImpl.class);
	
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
	public Map<Location, List<Meter>> loadMeters() {
		Map<Location, List<Meter>> result = new HashMap<>();
		
		// Load location.
		List<Location> locations = locationDAO.getAllLocation();
		if (locations == null || locations.isEmpty()) {
			return result;
		}
		
		// Load all meters by location.
		for (Location location : locations) {
			List<Meter> meters = meterDAO.getMeterByLocationId(location.getLocationId());
			if (meters == null || meters.isEmpty()) {
				continue;
			}
			result.put(location, meters);
		}
		
		return result;
	}

	@Override
	public MeterData getCurrentStatus(String meterId, String locationId, String zipCode) {
		// Load latest status of meter.
		try {
			int id = 0;
			if (locationId == null || locationId.isEmpty()) {
				Location location = locationDAO.getLocationByZipCode(zipCode);

				if (location != null) {
					id = location.getLocationId();
				}
			} else {
				id = Integer.parseInt(locationId);
			}
			if (id > 0) {
				MeterData meterData = new MeterData(Integer.parseInt(meterId.trim()), id);
				meterData = meterDataDAO.getLatestMeterDataById(meterData);
				
				return meterData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<MeterData> searchHistory(String meterId, String locationId, String zipCode, String from, String to) {
		List<MeterData> meterDataList = new ArrayList<>();
		try {
			int id = 0;
			if (locationId == null || locationId.isEmpty()) {
				Location location = locationDAO.getLocationByZipCode(zipCode);

				if (location != null) {
					id = location.getLocationId();
				}
			} else {
				id = Integer.parseInt(locationId);
			}
			if (id > 0) {
				// Load latest status of meter.
				MeterData searchData = new MeterData(Integer.parseInt(meterId), id, from, to);
				List<MeterData> result = meterDataDAO.searchMeterDataHistoryById(searchData);
				if (result != null) {
					meterDataList.addAll(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		return meterDataList;
	}

}
