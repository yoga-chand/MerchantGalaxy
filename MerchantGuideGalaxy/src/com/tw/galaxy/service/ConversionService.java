package com.tw.galaxy.service;

import java.util.Arrays;

import com.tw.galaxy.domain.RomanNumerals;

public class ConversionService {
	
	public Float convertRomanToDecimals(String romanNumerals){
		
		float decimal = 0;
		float lastNumber = 0;
		char[] romanNumeral = romanNumerals.toUpperCase().toCharArray();
		Float decimalValue = 0.0F;
		//Operation to be performed on upper cases even if user enters Roman values in lower case chars
		for (int x = romanNumeral.length- 1; x >= 0 ; x--) {
			Character convertToDecimal = romanNumeral[x];
			char[] tempCharArr = {convertToDecimal};
			String temp = new String(tempCharArr);
		
		if (lastNumber > decimal) {
			decimalValue = subtractionLogic(temp, lastNumber, decimalValue);
		}
		else {
			decimalValue = RomanNumerals.valueOf(temp).getDecimalValue() + decimal;
		}
		}
		return decimalValue;
	}
	
	public static float subtractionLogic(String roman, Float lastNumber, Float lastDecimalValue){
		Float decimal = RomanNumerals.valueOf(roman).getDecimalValue();
		if(Arrays.asList(RomanNumerals.valueOf(roman).getDigitsToSubtract()).contains(Math.round(lastNumber))){
			return lastDecimalValue - decimal;
		}
		else
			return lastDecimalValue + decimal;
	}

}
