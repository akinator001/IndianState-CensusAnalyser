package com.cp.indianstatecensusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder implements ICSVBuilder {
	
	public<E> int getCount(Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		int noOfStates = (int) StreamSupport.stream((iterable).spliterator(), false).count();
		return noOfStates;
	}

	@Override
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVException {
		CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
		return csvToBean.iterator();
	}
}
