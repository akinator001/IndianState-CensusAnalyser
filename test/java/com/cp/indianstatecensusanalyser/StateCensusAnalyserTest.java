package com.cp.indianstatecensusanalyser;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
	public static final String STATE_CENSUS_DATA = "./StateCensus.csv";

    @Test
    public void ensureNoOfRecordMatches(){
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int records = stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
        Assert.assertEquals(29, records);
    }
}
