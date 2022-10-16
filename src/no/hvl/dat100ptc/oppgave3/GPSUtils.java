package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.util.Locale;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double m : da) {
			if (m < min) {
				min = m;
			}
		}

		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < latitudes.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;

	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		for (int u = 0; u < longitudes.length; u++) {
			longitudes[u] = gpspoints[u].getLongitude();
		}
		return longitudes;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());

		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());

		double latTotal = latitude2 - latitude1;
		double longTotal = longitude2 - longitude1;

		double a = (Math.pow(Math.sin(latTotal) / 2, 2))
				+ (Math.cos(latitude1)) * (Math.cos(latitude2)) * (Math.pow(Math.sin(longTotal) / 2, 2));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		d = R * c;

		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double kmt;
		double distance;
		double time=(gpspoint2.getTime()-gpspoint1.getTime())/60.0/60.0;

		distance = distance(gpspoint1, gpspoint2) / 1000;

		kmt=distance/time;

		return kmt;
	}

	public static String formatTime(int secs) {

		String timestr = "";
		String TIMESEP = ":";

		int hr = secs / 3600;
		int min = (secs / 60) % 60;
		int sec = secs % 60;

		String hh = String.format("%02d", hr);
		String mm = String.format("%02d", min);
		String ss = String.format("%02d", sec);

		timestr = hh + TIMESEP + mm + TIMESEP + ss;
		timestr = String.format("%10s", timestr);

		return timestr;

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		str = String.format("%10s", String.format(Locale.US,"%.2f", d));
		return str;
		// TODO - SLUTT

	}
}