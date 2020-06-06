package com.sujithchenanath.coronatracker.models;

public class LocationStatus {
	
	private String state;
	private String country;
	private int latestCases;
	private int previousDayCases;
	
	
	public int getPreviousDayCases() {
		return previousDayCases;
	}
	public void setPreviousDayCases(int previousDayCases) {
		this.previousDayCases = previousDayCases;
	}
	public String getState() {
		return state;
	}
	@Override
	public String toString() {
		return "LocationStatus [state=" + state + ", country=" + country + ", latestCases=" + latestCases + "]";
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestCases() {
		return latestCases;
	}
	public void setLatestCases(int latestCases) {
		this.latestCases = latestCases;
	}

	
	
}
