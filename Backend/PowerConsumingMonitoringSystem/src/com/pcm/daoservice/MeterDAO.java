package com.pcm.daoservice;

import java.util.List;

import com.pcm.dao.Meter;

public interface MeterDAO {
	public boolean insertMeter(Meter meter);
	public Meter getMeterById(Meter meter);
	public List<Meter> getMeterByLocationId(int locationId);
	public List<Meter> selectAllMeter();
}
