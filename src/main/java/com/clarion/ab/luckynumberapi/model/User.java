package com.clarion.ab.luckynumberapi.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	
	@NotBlank
	@Pattern(regexp="[A-Za-z]*", message="User name is invalid")
	private String name;
	
	private int nameNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthDate;
	
	private int luckyNumber;
	 
	private String message;
	
	private String suggestion;
	
	public User(String name, LocalDate birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNameNumber() {
		return nameNumber;
	}

	public void setNameNumber(int nameNumber) {
		this.nameNumber = nameNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getLuckyNumber() {
		return luckyNumber;
	}

	public void setLuckyNumber(int luckyNumber) {
		this.luckyNumber = luckyNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}		
}
