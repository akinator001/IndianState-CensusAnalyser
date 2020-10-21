package com.cp.indianstatecensusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {
	public<E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CensusException{
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean.iterator();
		}
		catch(IllegalStateException e) {
			throw new CensusException(e.getMessage(), CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public<E> int getCount(Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		int noOfStates = (int) StreamSupport.stream((iterable).spliterator(), false).count();
		return noOfStates;
	}
}
