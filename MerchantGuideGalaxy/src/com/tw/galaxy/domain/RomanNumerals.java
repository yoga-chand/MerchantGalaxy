package com.tw.galaxy.domain;

/**
 * 
 * @author Yoga 
 * Symbols to decimal mapping in the form of enum
 *		I	1
		V	5
		X	10
		L	50
		C	100
		D	500
		M	1,000

 */
public enum RomanNumerals {
	I(1.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {5.0F, 10.0F};
			return temp;
		}
	},
	V(5.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {};
			return temp;
		}
	},
	X(10.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {50.0F, 100.0F};
			return temp;
		}
	},
	L(50.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {};
			return temp;
		}
	},
	C(100.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {100.0F, 1000.0F};
			return temp;
		}
	},
	D(500.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {};
			return temp;
		}
	},
	M(1000.0F){
		public Float[] getDigitsToSubtract(){
			Float[] temp = {};
			return temp;
		}
	};

	private Float roman;
	
	RomanNumerals(Float roman){
		this.roman = roman;
	}
	
	public Float getDecimalValue(){
		return this.roman;
	}
	
	public String getRomanValue(){
		return this.name();
	}
	
	public abstract Float[] getDigitsToSubtract(); 
	
}

