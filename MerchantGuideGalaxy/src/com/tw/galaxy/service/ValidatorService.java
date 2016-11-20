package com.tw.galaxy.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tw.galaxy.domain.RomanNumerals;

public abstract class ValidatorService {

	protected static final List<String> nonRepeatableCharacters = Arrays.asList(("D,L,V").split(","));

	protected static final List<String> limitedRepeatableCharacters = Arrays.asList(("I,V,X,M").split(","));
	
	private static Map<Character, Integer> nonRepeatableCharsCount = getNonRepeatableCharsCount();

	private static Map<Character,Integer> getNonRepeatableCharsCount(){
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		Character temp = null;
		for(String nonRepeatableChar : nonRepeatableCharacters){
			temp = nonRepeatableChar.charAt(0);
			map.put(temp, 0);
		}
		return map;
	}

	private static Map<Character,Integer> limitedRepeatableCharsCount = getLimitedRepeatableCharsCount();

	private static Map<Character,Integer> getLimitedRepeatableCharsCount(){
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		Character temp = null;
		for(String limitedRepeatableChar : limitedRepeatableCharacters){
			temp = limitedRepeatableChar.charAt(0);
			map.put(temp, 0);
		}
		return map;	
	}
	
	public static boolean checkCharExists(List<String> list, char romanChar){
		boolean result = false;
		if(list.contains(romanChar)){
			result = true;
		}
		return result;
	}
	
	
	public boolean validate(char[] currChar){
		boolean isValid = true;
		for(char charValue : currChar){
			if(checkCharExists(nonRepeatableCharacters, charValue)){
				nonRepeatableCharsCount.put(charValue, nonRepeatableCharsCount.get(charValue) + 1);
				if(nonRepeatableCharsCount.containsValue(2)){
					System.err.println("Invalid exchange code: Roman Numerals V,L,D cannot be repeated");
					isValid = false;
				}
			}
			else if(checkCharExists(limitedRepeatableCharacters, charValue)){
				limitedRepeatableCharsCount.put(charValue, limitedRepeatableCharsCount.get(charValue)+1);
				char[] charArr = {charValue};
				Character maxRepeatedChar = getMaxRepeatedChar();
				char[] maxRepeatedCharArr = {maxRepeatedChar};
				if(maxRepeatedChar != '\0'){
					if (charValue == maxRepeatedChar){
						isValid = false;
						System.err.println("Error : Roman Numeral "+charValue+" cannot repeat 4 times successively");
						System.exit(0);
					}
					else if(isSmallerThanPrevious(charArr, maxRepeatedCharArr)) {
						limitedRepeatableCharsCount.put(charValue, limitedRepeatableCharsCount.get(charValue)+1);
						limitedRepeatableCharsCount.put(maxRepeatedChar, 0);
					}
				}
				else{
					limitedRepeatableCharsCount.put(charValue, limitedRepeatableCharsCount.get(charValue)+1);
				}
			}
		}
		return isValid;
	}
	
	private static Character getMaxRepeatedChar(){
		for(Map.Entry<Character, Integer> entry : limitedRepeatableCharsCount.entrySet()){
			if(entry.getValue()==3){
				return entry.getKey();
			}
		}
		return '\0';
	}
	
	private static boolean isSmallerThanPrevious(char[] currentValue, char[] maxRepeatedChars){
		String currValue = new String(currentValue);
		String maxRepeatedNumeral = new String(maxRepeatedChars);
		if(RomanNumerals.valueOf(currValue).getDecimalValue() > RomanNumerals.valueOf(maxRepeatedNumeral).getDecimalValue()){
			return false;
		}
		else{
			return true;
		}
	}
	
}
