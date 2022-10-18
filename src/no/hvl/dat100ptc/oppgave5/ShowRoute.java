package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlon - minlon));

		return ystep;

	}

	public void showRouteMap(int ybase) {

		int x = MARGIN;
		int y = ybase;

		for (int i = 0; i < gpspoints.length; i++) {
			setColor(0, 0, 255);
			drawLine(x - (int)xstep(),y-(int)ystep(),10,10);
		}
	}

	public void showStatistics() {
		int time = gpscomputer.totalTime() / 3600;
		int minutt = (gpscomputer.totalTime() - (3600 * time)) / 60;
		int sek = (gpscomputer.totalTime() - (3600 * time) - (60 * minutt));
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

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		drawString(("=============================================="), 20, 20);
		drawString(("Total Time 	   :  " + visTime + ":" + visMin + ":" + visSek),20,40);
		drawString("Total Distance :    " + gpscomputer.totalDistance() + " km",20,60);
		drawString(("Total Elevation:     " + gpscomputer.totalElevation() + " m"),20,80);
		drawString("Max Speed      :     " + gpscomputer.maxSpeed() + " km/t",20,100);
		drawString("Average Speed  :     " + gpscomputer.averageSpeed() + " km/t",20,120);
		drawString("Energy         :     " + gpscomputer.totalKcal(80) + "kcal",20,140);
		drawString("==============================================",20,160);
	}

}
