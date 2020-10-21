package com.cp.indianstatecensusanalyser;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
	public static final String STATE_CENSUS_DATA = "./StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA ="./StateCensus2.csv";
	public static final String WRONG_STATE_CENSUS_DATA_HEADER = "./StateCode.csv";
	
    @Test
    public void ensureNoOfRecordMatches() throws CensusException{
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int records = stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
        Assert.assertEquals(29, records);
    }
 
    @Test
    public void checkWrongPath() throws CensusException{
    	try {
    		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    		stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
    	}
    }
    
    @Test
    public void checkWrongHeader() throws CensusException{
    	try {
    		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    		stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_HEADER));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_HEADER, e.type);;
    	}
    }
   
}
