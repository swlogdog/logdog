package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

public class OSVesionErrorRate {

	private String name;
	private ArrayList<Integer> data;
	public OSVesionErrorRate(String osVersion) {
		super();
		name = osVersion;
		data = new ArrayList<Integer>();
	}
	
	public void initRate(int size)
	{
		for(int i=0;i<size;i++)
			data.add(0);
	}
	public void setRate(int in,int r)
	{
		data.set(in,r);
	}
	public String getOsVersion() {
		return name;
	}
	
	public int getDatasize()
	{
		return data.size();
	}
	 
}
