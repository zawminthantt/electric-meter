package com.pcm.daoservice;

import java.sql.SQLException;
import java.util.List;

import com.pcm.dao.Location;

public class LocationDAOImpl extends AbstractDAO implements LocationDAO {

	@Override
	public boolean insertLocation(Location location) {
		try {
			sqlMap.insert("insertLocation", location);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Location getLocationByZipCode(String zipCode) {
		Location device = null;
		try {
			device = (Location) sqlMap.queryForObject("getLocationByZipCode", zipCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return device;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAllLocation() {
		List<Location> locations = null;
		try {
			locations = sqlMap.queryForList("getAllLocation");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locations;
	}

}
