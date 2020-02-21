package com.clarion.ab.luckynumberapi.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.clarion.ab.luckynumberapi.model.User;

/*This class tests the service class method 'checkLuckyNumber' with various scenarios.*/
class LuckyNumberServiceTest {
		
	LuckyNumberService luckyNumberService = new LuckyNumberService();
	
	@Test
	void testCheckLuckyNumber() {
		User testUser = new User("Abhishek", LocalDate.of(1990, 12, 31));
		luckyNumberService.checkLuckyNumber(testUser);
		assertEquals("Sorry Abhishek, your lucky number does not match your name.", testUser.getMessage());
		assertEquals("Try to add characters 'E' and 'U' to your name.", testUser.getSuggestion());
	}
	
	@Test
	void testCheckLuckyNumber_WithReceivedSuggestion1() {
		User testUser = new User("AbhiEshUek", LocalDate.of(1990, 12, 31));
		luckyNumberService.checkLuckyNumber(testUser);
		assertEquals("Congratulations AbhiEshUek, your lucky number matches your name.", testUser.getMessage());
	}
	
	@Test
	void testCheckLuckyNumber_LuckyNumberGreaterThanNameNumber() {
		User testUser = new User("Ali", LocalDate.of(1991, 01, 13));
		luckyNumberService.checkLuckyNumber(testUser);
		assertEquals("Sorry Ali, your lucky number does not match your name.", testUser.getMessage());
		assertEquals("Try to add an additional 'U' to your name.", testUser.getSuggestion());
	}

	@Test
	void testCheckLuckyNumber_WithReceivedSuggestion2() {
		User testUser = new User("AliU", LocalDate.of(1991, 01, 13));
		luckyNumberService.checkLuckyNumber(testUser);
		assertEquals("Congratulations AliU, your lucky number matches your name.", testUser.getMessage());
	}
	
	@Test
	void testCheckLuckyNumber_NameNumberGreaterThanLuckyNumber() {
		User testUser = new User("Jimmy", LocalDate.of(2001, 03, 24));
		luckyNumberService.checkLuckyNumber(testUser);
		assertEquals("Sorry Jimmy, your lucky number does not match your name.", testUser.getMessage());
		assertEquals("Try to add an additional 'E' to your name.", testUser.getSuggestion());
	}
	
	@Test
	void testCheckLuckyNumber_WithReceivedSuggestion3() {
		User testUser = new User("JimmEy", LocalDate.of(2001, 03, 24));
		luckyNumberService.checkLuckyNumber(testUser);
		assertEquals("Congratulations JimmEy, your lucky number matches your name.", testUser.getMessage());
	}

	@Test
	void testInvalidDayInBirthDate_ThrowsException() {
		Exception exception = assertThrows(DateTimeException.class, () -> {
			User testUser = new User("Jimmy", LocalDate.of(2001, 12, 34));
	    });
	 
	    String expectedMessage = "Invalid value for DayOfMonth";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidMonthInBirthDate_ThrowsException() {
		Exception exception = assertThrows(DateTimeException.class, () -> {
			User testUser = new User("Jimmy", LocalDate.of(2001, 15, 24));
	    });
	 
	    String expectedMessage = "Invalid value for MonthOfYear";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
