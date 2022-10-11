package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;

	// TODO - objektvariable
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {
		
		this.latitude=latitude;
		this.time=time;
		this.longitude=longitude;
		this.elevation=elevation;

		// TODO - konstruktør

		// Fjern linjene med throw new etterhvert som metodene implementeres

	}

	// TODO - get/set metoder
	public int getTime() {
		return time;
		
		
		
	}

	public void setTime(int time) {
		this.time=time;
				

	}

	public double getLatitude() {
		return latitude;
		
		
	}

	public void setLatitude(double latitude) {
		this.latitude=latitude;
		
		
	}

	public double getLongitude() {
		return longitude;
		
		
	}

	public void setLongitude(double longitude) {
		this.longitude=longitude;
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getElevation() {
		return elevation;
		
		
	}

	public void setElevation(double elevation) {
		this.elevation=elevation;
		
		
	}
	
	public String toString() {
		
		String str;
		
		// TODO - start

		throw new UnsupportedOperationException(TODO.method());

		// TODO - slutt
		
	}
}
