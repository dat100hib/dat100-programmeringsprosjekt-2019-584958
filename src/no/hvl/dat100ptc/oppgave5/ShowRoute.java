package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 400;
	private static int MAPYSIZE = 400;

	private GPSPoint[] gpspoints;
	private static GPSComputer gpscomputer;
	//Satt private GPSComputer gpscomputer; til static
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

		//playRoute(MARGIN + MAPYSIZE);
		
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
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
	
		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 
		return ystep;
	}

	public void showRouteMap(int ybase) {
		//hvorfor har alle gpspoints[i] for getlongitude og getlatitude samme verdi?
		//hva skal man bruke ystep og xstep til?
		int diameter = 3;
		setColor(0, 0, 255);
		int MARGIN=80;
		int x,y;
		for (int i = 0; i<gpspoints.length; i++) {
			x=(int)gpspoints[i].getLongitude()+MARGIN;
			y=(int)gpspoints[i].getLatitude();
			
			fillCircle(x,ybase-y, diameter);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		drawString("=========================================",TEXTDISTANCE,20 );
		drawString("Total Time"+"\t\t\t\t\t\t\t"+":"+ GPSUtils.formatTime(GPSComputer.totalTime()),TEXTDISTANCE,30 );
		drawString("Total distance"+"\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.totalDistance()/1000),TEXTDISTANCE,40 );
		drawString("Total elevation"+"\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.totalElevation()),TEXTDISTANCE,50 );
		drawString("Max speed"+"\t\t\t\t\t\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.maxSpeed()),TEXTDISTANCE,60 );
		drawString("Average speed"+"\t\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.averageSpeed())+"km/t",TEXTDISTANCE,70 );
		drawString("Energy"+"\t\t\t\t\t\t\t\t\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.totalKcal(80))+"kcal",TEXTDISTANCE,80 );
		drawString("=========================================",TEXTDISTANCE,90 );
		
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}
