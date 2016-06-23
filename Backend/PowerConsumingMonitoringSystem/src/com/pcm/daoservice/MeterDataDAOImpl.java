package com.pcm.daoservice;

import java.sql.SQLException;
import java.util.List;

import com.pcm.dao.MeterData;

public class MeterDataDAOImpl extends AbstractDAO implements MeterDataDAO {

	@Override
	public boolean insertMeterData(MeterData meterData) {
		try {
			sqlMap.insert("insertMeterData", meterData);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public MeterData getLatestMeterDataById(MeterData meterData) {
		MeterData data = null;
		try {
			data = (MeterData) sqlMap.queryForObject("getLatestMeterDataById", meterData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MeterData> searchMeterDataHistoryById(MeterData meterData) {
		List<MeterData> dataList = null;
		try {
			dataList = sqlMap.queryForList("searchMeterDataHistoryById", meterData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

}
