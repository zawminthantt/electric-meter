package com.pcm.daoservice;

import java.util.List;

import com.pcm.dao.Location;

public interface LocationDAO {
	public boolean insertLocation(Location location);
	public Location getLocationByZipCode(String zipCode);
	public List<Location> getAllLocation();
}
