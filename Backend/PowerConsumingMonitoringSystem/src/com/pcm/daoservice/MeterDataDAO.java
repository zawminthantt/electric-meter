package com.pcm.daoservice;

import java.util.List;

import com.pcm.dao.MeterData;

public interface MeterDataDAO {
	public boolean insertMeterData(MeterData meterData);
	public MeterData getLatestMeterDataById(MeterData meterData);
	public List<MeterData> searchMeterDataHistoryById(MeterData meterData);
}
