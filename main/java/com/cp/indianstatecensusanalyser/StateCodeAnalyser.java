package com.cp.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.cp.CSVBuilder.CSVBuilderFactory;
import com.cp.CSVBuilder.CSVException;
import com.cp.CSVBuilder.ICSVBuilder;
import com.cp.CSVBuilder.OpenCSVBuilder;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCodeAnalyser {
	public int loadCSVFile(Path path) throws CensusException, CSVException {
		try (Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCode> stateCodeList = csvBuilder.getCSVFileList(reader, StateCode.class);
			return stateCodeList.size();
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public String getStateWiseSortedCodeData(Path path) throws CensusException, CSVException {
		try (Reader reader = Files.newBufferedReader(path)){
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCode> stateCodeList = csvBuilder.getCSVFileList(reader, StateCode.class);
			Comparator<StateCode> censusComparator = Comparator.comparing(census -> census.stateName);
			this.sort(stateCodeList, censusComparator);
			String sortedStateCode = new Gson().toJson(stateCodeList);
			return sortedStateCode;
		}
		catch(IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV); 
		}
		catch(RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
		catch(CSVException e) {
			throw new CensusException("Unable to parse", CensusException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	private void sort(List<StateCode> codeList, Comparator<StateCode> censusComparator) {
		for(int i = 0; i < codeList.size() - 1; i++) {
			for(int j = 0; j< codeList.size() - 1 - i; j++) {
				StateCode code1 = codeList.get(j);
				StateCode code2 = codeList.get(j+1);
				if(censusComparator.compare(code1, code2) > 0) {
					codeList.set(j, code2);
					codeList.set(j+1, code1);
				}
			}
		}
	}
}
