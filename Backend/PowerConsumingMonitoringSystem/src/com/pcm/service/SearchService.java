package com.pcm.service;

import java.util.List;
import java.util.Map;

import com.pcm.dao.Location;
import com.pcm.dao.Meter;
import com.pcm.dao.MeterData;

public interface SearchService {
	public Map<Location, List<Meter>> loadMeters();
	public MeterData getCurrentStatus(String meterId, String locationId, String zipCode);
	public List<MeterData> searchHistory(String meterId, String locationId, String zipCode, String from, String to);
}
