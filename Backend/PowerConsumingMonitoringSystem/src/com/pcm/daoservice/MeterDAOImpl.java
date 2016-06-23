package com.pcm.daoservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcm.dao.Meter;

public class MeterDAOImpl extends AbstractDAO implements MeterDAO {

	@Override
	public boolean insertMeter(Meter meter) {
		try {
			sqlMap.insert("insertMeter", meter);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Meter getMeterById(Meter meter) {
		try {
			return (Meter) sqlMap.queryForObject("getMeterById", meter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Meter> getMeterByLocationId(int locationId) {
		try {
			return sqlMap.queryForList("getMeterByLocationId", locationId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Meter> selectAllMeter() {
		List<Meter> meters = new ArrayList<Meter>();
		try {
			meters = sqlMap.queryForList("getAllMeter");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return meters;
	}

}
