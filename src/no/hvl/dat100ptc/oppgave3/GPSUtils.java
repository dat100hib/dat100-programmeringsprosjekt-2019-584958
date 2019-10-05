package no.hvl.dat100ptc.oppgave3;

import java.util.ArrayList;

import static java.lang.Math.*;

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

		double min=da[0];
		for (double d:da) {
			min = Math.min(d, min);
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double [] latitudes = new double [gpspoints.length];
		for (int i = 0; i < latitudes.length; i++ ) {
			latitudes[i]=gpspoints[i].getLatitude();
		}
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		
		double [] longitudes = new double [gpspoints.length];
		for (int i = 0; i < longitudes.length; i++ ) {
			longitudes[i]=gpspoints[i].getLongitude();
		}
		return longitudes;
		
		/*
		ArrayList<Double> longitudes = new ArrayList<>();
		for (int i = 0; i < gpspoints.length; i++ ) {
			longitudes.add(gpspoints[i].getLongitude());
		}
		*/
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		//finner verdiene for hvert punkt.
		latitude1=gpspoint1.getLatitude();
		latitude2=gpspoint2.getLatitude();
		longitude1=gpspoint1.getLongitude();
		longitude2=gpspoint2.getLongitude();
		
		//regner ut og returnerer d ved bruk av Haversine-formlen
				
		double deltaLatitude = Math.toRadians(latitude2) - Math.toRadians(latitude1);
		double deltaLongitude =  Math.toRadians(longitude2)- Math.toRadians(longitude1);
		
		double a = Math.pow((Math.sin(deltaLatitude/2)), 2) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.pow(Math.sin(deltaLongitude/2), 2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return d=R*c;
		
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
