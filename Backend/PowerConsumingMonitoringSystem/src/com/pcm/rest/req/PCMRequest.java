package com.pcm.rest.req;

/**
 * 
 * @author swemon
 *
 */
public class PCMRequest {

	protected String ver;
	
	public PCMRequest() {
		
	}
	
	public PCMRequest(String ver) {
		this.ver = ver;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

}
