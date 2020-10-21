package com.cp.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCodeAnalyser {
	public int loadCSVFile(Path path) throws CensusException {
		try (Reader reader = Files.newBufferedReader(path)){
			Iterator<StateCode> stateCodeIterator = this.getCsvFileIterator(reader, StateCode.class);
			Iterable<StateCode> stateCodeIterable = () -> stateCodeIterator;
			int noOfStates = (int) StreamSupport.stream(((Iterable<Path>) stateCodeIterator).spliterator(), false).count();
			return noOfStates;
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
	}
	
	private<E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CensusException{
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean.iterator();
		}
		catch(IllegalStateException e) {
			throw new CensusException(e.getMessage(), CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}	
	
}
