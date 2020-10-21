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

public class StateCensusAnalyser {
	public int loadCSVFile(Path path)throws CensusException {
		try (Reader reader = Files.newBufferedReader(path)){
			Iterator<StateCensus> stateCensusIterator = new OpenCSVBuilder().getCsvFileIterator(reader, StateCensus.class);
			return new OpenCSVBuilder().getCount(stateCensusIterator);
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("Internal data of file is not valid ", CensusException.ExceptionType.WRONG_HEADER);
		}
	}
}