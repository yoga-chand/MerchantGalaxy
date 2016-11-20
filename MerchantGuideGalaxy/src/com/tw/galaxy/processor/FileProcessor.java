package com.tw.galaxy.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tw.galaxy.domain.RomanNumerals;
import com.tw.galaxy.service.ConversionService;

public class FileProcessor {
	
	public List<String> readFile() throws IOException{
		
		ClassLoader classLoader = FileProcessor.class.getClassLoader();
		File file = new File(classLoader.getResource("input.txt").getFile());
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		List<String> inputLines = new ArrayList<String>();
		while ((line = bufferedReader.readLine()) != null) {
			inputLines.add(line);
		}
		bufferedReader.close();
		return inputLines;
	}
	
	public void processFile(List<String> inputLines){
		
		Map<String, String> symbolToValueMap = new HashMap<String, String>();
		Map<String, Float> metalValueMap = null;
		List<String> metalInputLines = new ArrayList<String>();
		for(String input : inputLines){
			String inputArr[] = input.split(" ");
			if(inputArr.length == 3 && inputArr[1].equalsIgnoreCase("is")){
				symbolToValueMap.put(inputArr[0], RomanNumerals.valueOf(inputArr[inputArr.length-1]).getRomanValue());
			}
			if(input.toLowerCase().endsWith("credits")){
				metalInputLines.add(input);
			}
		}
		
	}

	private static Map<String, Float> findMetalValues(List<String> metalInputList, Map<String, String> symbolToValueMap){
		Map<String, Float> metalValueMap = new HashMap<String, Float>();
		for(String metalInput : metalInputList){
			String metalInputArray[] = metalInput.split(" ");
			String creditValue = null;
			String[] alienInputs = null;
			String metal= null; 
			int creditIndex = 0;
			for (int i = 0; i < metalInputArray.length; i++) {
				if(metalInputArray[i].toLowerCase().equals("is")){
					metal = metalInputArray[i-1];
					creditValue = metalInputArray[i+1];
					alienInputs = java.util.Arrays.copyOfRange(metalInputArray, 0, i-1);
					break;
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			
			for(String alienCodes : alienInputs){
				if(symbolToValueMap.containsKey(alienCodes)){
					stringBuilder.append(RomanNumerals.valueOf(symbolToValueMap.get(alienCodes)).getRomanValue());
				}
				else{
					System.err.println("please check the inputs once");
				}
			}
			ConversionService conversion = new ConversionService();
			float decimalValue = conversion.convertRomanToDecimals(stringBuilder.toString());
			metalValueMap.put(metal, Float.parseFloat(creditValue)/decimalValue);

		}
		return metalValueMap;
	}
	
}
