package com.pcm.service;

public interface EntryService {
	public boolean insertData(String zipCode
								, int meterId
								, String power
								, String datetime
								, String current
								, String frequency
								, String voltage
								, String breakerState);
}
