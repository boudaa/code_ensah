package com.services;


import com.bo.WordPolarity;

public interface SentimentAnalysis {

	public double getTextPolarity(String tokens);
	
	
	public void addWord(WordPolarity word);
	
	public void destinationRating();

}
