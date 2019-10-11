package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int MARGIN = 50;		// margin on the sides 
	
	//FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start
		//Gitt den startveri x for hvor den skal begynne fra venste. bredde paa soylene.
		//mellomrom variablen er mellomrom mellom hver soyle.
		//hoyden til soylen blir definert i for loopen. fyller hvert rektangel
		//for hver soyle gitt punktet gpspoints[i].
		double maxEle = 0;
		double minEle = 0;
		for (int i = 0; i<gpspoints.length; i++) {
			maxEle = Math.max(maxEle, gpspoints[i].getElevation());
			minEle = Math.min(minEle, gpspoints[i].getElevation());
		}
		double eleStep = MAXBARHEIGHT/(maxEle-minEle);
		
		setColor(0, 0, 255);
		int y, x=MARGIN;
		int mellomrom = 2;
		int bredde = 1;
		for (int i = 0; i<gpspoints.length; i++) {
			y = (int) ((gpspoints[i].getElevation()-minEle)*eleStep);
			System.out.println(y);
			fillRectangle(x,ybase-y, bredde, y);
			x += mellomrom;
		}
	}
}
