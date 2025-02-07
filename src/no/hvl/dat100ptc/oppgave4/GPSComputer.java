package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {

			if (gpspoints[i].getElevation() < gpspoints[i + 1].getElevation()) {
				elevation += gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			}
		}
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int time = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();

		return time;

	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		double[] farter = new double[gpspoints.length - 1];
		for (int i = 0; i < gpspoints.length - 1; i++) {
			farter[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);

		}
		return farter;

	}

	public double maxSpeed() {

		return GPSUtils.findMax(speeds());

	}

	public double averageSpeed() {
		double distance = totalDistance();
		double time = totalTime();

		return (distance / 1000.0) / (time / 60.0 / 60.0);

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = weight * secs / speed;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph >= 10 && speedmph < 12) {
			met = 6.0;
		} else if (speedmph >= 12 && speedmph < 14) {
			met = 8.0;
		} else if (speedmph >= 14 && speedmph < 16) {
			met = 10.0;
		} else if (speedmph >= 16 && speedmph < 20) {
			met = 12.0;
		} else if (speedmph > 20) {
			met = 16.0;
		}

		kcal = (met * weight * (secs / 3600.0));

		return kcal;
	}

	public double totalKcal(double weight) {
		int fullTid = totalTime();
		double fullFart = averageSpeed();

		double totalkcal = kcal(weight, fullTid, fullFart);
		return totalkcal;

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {
		int time = totalTime() / 3600;
		int minutt = (totalTime() - (3600 * time)) / 60;
		int sek = (totalTime() - (3600 * time) - (60 * minutt));
		String visTime = "" + time;
		String visMin = "" + minutt;
		String visSek = "" + sek;
		if (time < 10) {
			visTime = "0" + time;
		}
		if (minutt < 10) {
			visMin = "0" + minutt;
		}
		if (sek < 10) {
			visSek = "0" + sek;
		}
		
		System.out.println("==============================================");
		System.out.println("Total Time 	   :  " + visTime + ":" + visMin + ":" + visSek);
		System.out.println("Total Distance :    " + totalDistance() + " km");
		System.out.println("Total Elevation:     " + totalElevation() + " m");
		System.out.println("Max Speed      :     " + maxSpeed() + " km/t");
		System.out.println("Average Speed  :     " + averageSpeed() + " km/t");
		System.out.println("Energy         :     " + totalKcal(WEIGHT) + "kcal");
		System.out.println("==============================================");
	}

}
