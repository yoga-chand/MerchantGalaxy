package com.tw.galaxy.domain;

import java.util.Map;

public class Inputs {
	
	private Map<String, String> symbolToValueMap;
	
	private Map<String, Float> metalValueMap;

	public Inputs(Map<String,String> symbolToValueMap, Map<String, Float> metalValueMap){
		
		this.symbolToValueMap = symbolToValueMap;
		this.metalValueMap = metalValueMap;
	}
	
	
	
}
