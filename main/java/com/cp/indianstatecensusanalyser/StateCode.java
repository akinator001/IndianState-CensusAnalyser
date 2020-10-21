package com.cp.indianstatecensusanalyser;

public class StateCode {
	public static String srNo;
	public static String stateName;
	public static int tin;
	public static String stateCode;

	public StateCode() {
	}

	public StateCode(String srNo, String stateName, int tin, String stateCode) {
		this.srNo = srNo;
		this.stateName = stateName;
		this.tin = tin;
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "IndiaStateCodeCSV{" + "srNo='" + srNo + '\'' + ", stateName=" + stateName + ", tin=" + tin
				+ ", stateCode=" + stateCode + '}';
	}
}
