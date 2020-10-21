package com.cp.indianstatecensusanalyser;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class StateCodeAnalyserTest {
	public static final String STATE_CODE_DATA = "StateCode.csv";
	public static final String WRONG_STATE_CODE_DATA = "statecode2.csv";
			
	StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();

	@Test
    public void ensureNoOfRecordMatches() throws CensusException{
        int records = stateCodeAnalyser.loadCSVFile(Paths.get(STATE_CODE_DATA));
        Assert.assertEquals(37, records);
    }
	@Test
    public void checkWrongPath() throws CensusException{
    	try {
    		stateCodeAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CODE_DATA));
    	}
    	catch(CensusException e) {
    		Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
    	}
    }	
}
