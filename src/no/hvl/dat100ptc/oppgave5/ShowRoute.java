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
		//hva skal man bruke ystep og xstep til?
		int diameter = 3;
		int MARGIN=80;
		int x,y;
		
		//ystep();
		
		
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		
		for (int i = 0; i<gpspoints.length; i++) {
			//Finner ut av hvilke inkrementer som må ganges til den x verdien og y verdein
			//slik at de er innenfor bildet på 800 * 800.
			int inkrementX=100;
			while ((((maxlon*(maxlon-minlon)/MAPXSIZE*xstep())+MARGIN)*inkrementX)<MAPXSIZE) {
				inkrementX-=1;
			}
			int inkrementY=100;
			while ((500-(maxlat*(maxlat-minlat)/MAPYSIZE*ystep())*inkrementY)<MAPYSIZE) {
				inkrementY-=1;
			}
			//Finner x og y verdi gjennom å gange høyseste verdi for punktet med latituden i punktet
			//minus laveste punkt for å ha en null verdi. Resten var for å få det interaktivt og ganget med inkrement.
			x=(int)((maxlon*(gpspoints[i].getLongitude()-minlon)/MAPXSIZE*xstep())*inkrementX);
			y=(int)((maxlat*(gpspoints[i].getLatitude()-minlat)/MAPYSIZE*ystep()*inkrementY));
			
			//når man begynner og slutter reisen får man en blå dott. Når man går oppover
			//skriver man ut en grønn sirkel og en rød når man går nedover.
			
			if (i==0) { 
				setColor(0,0,255);
			} else if (i==gpspoints.length-1) {
				setColor(0,0,255);
			} else if (gpspoints[i].getElevation()>=gpspoints[i-1].getElevation() && i>0 && i!=gpspoints.length) {
				setColor(0,255,0);
			} else if (gpspoints[i].getElevation()<gpspoints[i-1].getElevation() && i>0 && i!=gpspoints.length){
				setColor(255,0,0);
			}
			
			fillCircle(x+MARGIN,200-y, diameter);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		drawString("=========================================",TEXTDISTANCE,20 );
		drawString("Total Time"+"\t\t\t\t\t\t\t"+":"+ GPSUtils.formatTime(GPSComputer.totalTime()),TEXTDISTANCE,30 );
		drawString("Total distance"+"\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.totalDistance()/1000)+" km",TEXTDISTANCE,40 );
		drawString("Total elevation"+"\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.totalElevation())+" m",TEXTDISTANCE,50 );
		drawString("Max speed"+"\t\t\t\t\t\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.maxSpeed())+" km/t",TEXTDISTANCE,60 );
		drawString("Average speed"+"\t\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.averageSpeed())+" km/t",TEXTDISTANCE,70 );
		drawString("Energy"+"\t\t\t\t\t\t\t\t\t\t\t"+":"+"\t\t"+String.format("%.2f", GPSComputer.totalKcal(80))+" kcal",TEXTDISTANCE,80 );
		drawString("=========================================",TEXTDISTANCE,90 );
		
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}
