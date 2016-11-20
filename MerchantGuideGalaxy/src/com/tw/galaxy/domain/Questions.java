package com.tw.galaxy.domain;

import java.util.List;

import com.tw.galaxy.service.ValidatorService;

public class Questions extends ValidatorService{
	
	Inputs input;
	
	List<String> questionList;
	
	public Questions(List<String> questionList, Inputs input){
		this.questionList = questionList;
		this.input = input;
	}
	
	
	
}
