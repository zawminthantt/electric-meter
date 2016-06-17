package com.pcm.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcm.dao.MeterData;
import com.pcm.dao.Location;
import com.pcm.dao.Meter;
import com.pcm.rest.req.SendDataRequest;
import com.pcm.rest.req.LatestStatusRequest;
import com.pcm.rest.req.LoadMetersRequest;
import com.pcm.rest.req.SearchHistoryRequest;
import com.pcm.rest.res.SendDataResponse;
import com.pcm.rest.res.StatusResponse;
import com.pcm.rest.res.LoadMetersResponse;
import com.pcm.rest.res.LoadMetersResponse.ResponseData;
import com.pcm.rest.res.SearchHistoryResponse;
import com.pcm.service.EntryService;
import com.pcm.service.SearchService;

@Controller
@Configurable
@RequestMapping("/pcm")
public class PCMRestController {
	
	private static Logger logger = Logger.getLogger(PCMRestController.class);
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public @ResponseBody SimpleResponse hello(@RequestBody SimpleRequest request) {
		SimpleResponse response = new SimpleResponse();
		
		return response;
	}
	
	class SimpleResponse {
		private String status = "hello";

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
	
	class SimpleRequest {
	}

	@RequestMapping(value = "sendData", method = RequestMethod.POST)
	public @ResponseBody SendDataResponse sendData(@RequestBody SendDataRequest request) {
		SendDataResponse response = new SendDataResponse();
		logger.info(request);
		
		try {
			boolean result = entryService.insertData(request.getZipCode()
										, Integer.parseInt(request.getMeterId())
										, request.getPower()
										, request.getDatetime()
										, request.getCurrent()
										, request.getFrequency()
										, request.getVoltage()
										, request.getBreakerState());
			response.setSuccess(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(response);
		
		return response;
	}

	@RequestMapping(value = "loadMeters", method = RequestMethod.POST)
	public @ResponseBody LoadMetersResponse loadMeters(@RequestBody LoadMetersRequest request) {
		LoadMetersResponse response = new LoadMetersResponse();
		logger.info(request);
		
		Map<Location, List<Meter>> meters = searchService.loadMeters();
		List<LoadMetersResponse.ResponseData> data = new ArrayList<>();

		for (Entry<Location, List<Meter>> entry : meters.entrySet()) {
			Location location = entry.getKey();
			ResponseData responseData = new LoadMetersResponse.ResponseData(
												String.valueOf(location.getLocationId())
												, location.getZipCode()
												, entry.getValue());
			
			data.add(responseData);
		}
		response.setResponseData(data);
		logger.info(response);
		
		return response;
	}

	@RequestMapping(value = "latestStatus", method = RequestMethod.POST)
	public @ResponseBody SearchHistoryResponse currentStatus(@RequestBody LatestStatusRequest request) {
		SearchHistoryResponse response = new SearchHistoryResponse();
		logger.info(request);
		
		MeterData data = searchService.getCurrentStatus(request.getMeterId(), request.getLocationId(), request.getZipCode());
		if (data != null) {
			StatusResponse statusResponse = new StatusResponse();
			statusResponse.setPower(data.getMpower());
			statusResponse.setDatetime(data.getMdatetime());
			statusResponse.setCurrent(data.getCurrent());
			statusResponse.setFrequency(data.getFrequency());
			statusResponse.setVoltage(data.getVoltage());
			statusResponse.setBreakerState(data.getBreakerState());
			
			List<StatusResponse> statusList = new ArrayList<>();
			statusList.add(statusResponse);
			
			response.setResponseData(statusList);
		}
		
		logger.info(response);
		
		return response;
	}

	@RequestMapping(value = "searchHistory", method = RequestMethod.POST)
	public @ResponseBody SearchHistoryResponse searchHistory(@RequestBody SearchHistoryRequest request) {
		SearchHistoryResponse response = new SearchHistoryResponse();
		logger.info(request);
		
		List<MeterData> data = searchService.searchHistory(request.getMeterId()
									, request.getMeterId()
									, request.getZipCode()
									, request.getFrom()
									, request.getTo());
		if (data != null) {
			List<StatusResponse> statusList = new ArrayList<>();
			for (MeterData each : data) {
				StatusResponse status = new StatusResponse();
				status.setPower(each.getMpower());
				status.setDatetime(each.getMdatetime());
				status.setCurrent(each.getCurrent());
				status.setFrequency(each.getFrequency());
				status.setVoltage(each.getVoltage());
				status.setBreakerState(each.getBreakerState());
				statusList.add(status);
			}
			response.setResponseData(statusList);
		}
		logger.info(response);
		
		return response;
	}
}