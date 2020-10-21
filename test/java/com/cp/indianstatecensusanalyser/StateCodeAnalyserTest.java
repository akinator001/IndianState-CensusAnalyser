package com.cp.indianstatecensusanalyser;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class StateCodeAnalyserTest {
	public static final String STATE_CODE_DATA = "StateCode.csv";

	StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();

	@Test
    public void ensureNoOfRecordMatches() throws CensusException{
        int records = stateCodeAnalyser.loadCSVFile(Paths.get(STATE_CODE_DATA));
        Assert.assertEquals(37, records);
    }
}
