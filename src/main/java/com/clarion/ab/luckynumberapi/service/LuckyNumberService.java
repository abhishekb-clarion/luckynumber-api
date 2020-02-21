package com.clarion.ab.luckynumberapi.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.clarion.ab.luckynumberapi.model.User;

@Service
public class LuckyNumberService {

	private static final Logger logger = LoggerFactory.getLogger(LuckyNumberService.class);
	
	/*
	 * This function checks lucky number of a user depending upon name and birth
	 * date
	 */
	public void checkLuckyNumber(User user) {		
		user.setLuckyNumber(getLuckyNumberFromBirthdate(user.getBirthDate()));
		user.setNameNumber(getNumberFromName(user.getName().toUpperCase()));		
		
		if(user.getLuckyNumber() == user.getNameNumber()) {
			user.setMessage(String.format("Congratulations %s, your lucky number matches your name.", user.getName()));
			user.setSuggestion("");
		}else {
			user.setMessage(String.format("Sorry %s, your lucky number does not match your name.", user.getName()));
			user.setSuggestion(getSuggestionsForName(user));
		}
	}
	
	/*
	 * This function will calculate the 'name number' by adding numbers equivalent
	 * to each character in the name string
	 */ 
	private int getNumberFromName(String userName) {
		logger.info("Name of the user is " + userName);
		int nameTotal = 0;
		
		for(char c : userName.toCharArray()) {
			nameTotal += (c - 'A' + 1);
			logger.info("Value of " + c + " = " + (c - 'A' + 1));
		}
		
		logger.info("Total of " + userName + " is " + nameTotal);
		return getSingleDigitTotal(nameTotal);
	}
	
	/*
	 * This function will calculate the lucky number from birthdate by adding
	 * individual digits of date dd-MM-yyyy in d+d+M+M+y+y+y+y fashion
	 */
	private int getLuckyNumberFromBirthdate(LocalDate birthDate) {
		logger.info("Birthdate of the user is " + birthDate);
		int dateTotal = 0;
		String dateNumbers = birthDate.toString().replace("-", "");
		
		for(char d : dateNumbers.toCharArray()) {
			dateTotal += Character.getNumericValue(d);
		}
		logger.info("Total of digits in birthdate " +birthDate+ " is " + dateTotal);
		return getSingleDigitTotal(dateTotal);
	}
	
	/*
	 * This function will calculate a single digit sum of a number by adding
	 * individual digits.
	 */
	private int getSingleDigitTotal(int input) {
		int result = 0;

		while(input > 0) {
			result += input % 10;
			input /= 10;
		}

		logger.info("Single digit total is " + result);
		return (result > 9) ? getSingleDigitTotal(result) : result;
	}
	
	/*
	 * This function will calculate the difference in both numbers and suggest
	 * addition of vowels
	 */
	private String getSuggestionsForName(User user) {
		int luckyNumber = user.getLuckyNumber(); 
		int nameNumber = user.getNameNumber();
		String suggestion = "Try to add some vowels to your name.";
		int numberDiff = 0;

		/*
		 * Vowels with their corresponding values.
		 * A = 1, E = 5, I = 9, O = 15, U = 21
		 * Vowels with double digit value can be reduced to single digit as the effective sum is same.
		 * So resulting face values would be
		 * A = 1, E = 5, I = 9, O = 6, U = 3
		 * Building a map to avoid recalculation. 
		 */
		
		Map<Integer, String> vowelMap = new HashMap<>();
		vowelMap.put(1, "an additional 'A'");
		vowelMap.put(5, "an additional 'E'");
		vowelMap.put(9, "an additional 'I'");
		vowelMap.put(6, "an additional 'O'");
		vowelMap.put(3, "an additional 'U'");
		vowelMap.put(2, "two 'A's");
		vowelMap.put(4, "characters 'A' and 'U'");
		vowelMap.put(7, "characters 'A' and 'O'");
		vowelMap.put(8, "characters 'E' and 'U'");
		
		if(nameNumber < luckyNumber) {
			numberDiff = luckyNumber - nameNumber;
		}else {
			int targetSum = 10 + (luckyNumber-1);
			logger.info("Target sum is " + targetSum);
			numberDiff = targetSum - nameNumber;
		}
		
		if(vowelMap.containsKey(numberDiff)) {
			suggestion = String.format("Try to add %s to your name.", vowelMap.get(numberDiff));
		}else {
			suggestion = "You're really unlucky!";
		}
		
		return suggestion;
	}
}
