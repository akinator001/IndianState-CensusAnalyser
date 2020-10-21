package com.cp.indianstatecensusanalyser;

public class CensusException extends Exception{
	enum ExceptionType{
		WRONG_CSV, WRONG_DELIMITER, WRONG_HEADER
	}

	ExceptionType type;

	public CensusException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
