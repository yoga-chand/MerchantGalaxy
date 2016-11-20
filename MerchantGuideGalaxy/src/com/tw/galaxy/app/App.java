package com.tw.galaxy.app;

import java.io.IOException;
import java.util.List;

import com.tw.galaxy.processor.FileProcessor;

class App{
	public static void main(String[] args) {
		
		try {
			FileProcessor fileProcessor = new FileProcessor();
			List<String> inputList = fileProcessor.readFile();
			fileProcessor.processFile(inputList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}