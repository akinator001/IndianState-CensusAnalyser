package com.cp.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadCSVFile(Path path)throws CensusException {
		try (Reader reader = Files.newBufferedReader(path)){
			CsvToBean<StateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(StateCensus.class).withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCensus> iterator = csvToBean.iterator();
			ArrayList<StateCensus> stateCensusList = new ArrayList<StateCensus>();
			while(iterator.hasNext()) {
				 stateCensusList.add(iterator.next());
			}
			return stateCensusList.size();
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}	
	}
}