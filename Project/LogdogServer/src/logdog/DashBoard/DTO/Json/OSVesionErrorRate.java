package logdog.DashBoard.DTO.Json;

import java.util.ArrayList;

public class OSVesionErrorRate {

	private String OsVersion;
	private ArrayList<Integer> rate;
	public OSVesionErrorRate(String osVersion) {
		super();
		OsVersion = osVersion;
		rate = new ArrayList<Integer>();
	}
	
	public void addRate(int r)
	{
		rate.add(r);
	}
}
