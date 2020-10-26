package com.cp.indianstatecensusanalyser;

public class StateCensus {
	public static String state;
	public static String population;
	public static String areaInSqKm;
	public static String densityPerSqKm;

	public StateCensus() {
	}

	public StateCensus(String state, String population, long areaInSqSKm, String densityPerSqKm) {
		this.state = state;
		this.population = population;
		this.areaInSqKm = areaInSqKm;
		this.densityPerSqKm = densityPerSqKm;
	}

	@Override
	public String toString() {
		return "IndiaCensusCSV{" + "State='" + state + '\'' + ", Population='" + population + '\'' + ", AreaInSqKm='"
				+ areaInSqKm + '\'' + ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}';
	}
}
