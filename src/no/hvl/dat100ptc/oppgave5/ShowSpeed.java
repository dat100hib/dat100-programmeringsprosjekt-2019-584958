package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		//Definerer relevante variabler. Lager en for loop der hoyden til soylen er 
		//farten i gpspoints[i]. legger sammen hver hatighet til averageSpeed variabelen
		//for aa senere regne gjennomsnittshastigheten. 
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
				
		int x = MARGIN;
		int xAv = MARGIN;
		int bredde = 1;
		int breddeAvg = N;
		int hoydeAvg = 2;
		int mellomrom = 2;
		int hoyde = 0;
		int averageSpeed = 0;
		for (int i = 0; i<N; i++) {
			hoyde =(int) GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			averageSpeed+=hoyde;
			setColor(0, 0, 255);
			if (hoyde>0) {
				fillRectangle(x,ybase-hoyde, bredde, hoyde);
				x+=mellomrom;
			}
		}
		breddeAvg = x-xAv;
		averageSpeed = (int)((averageSpeed/N)+0.5);
		setColor(0,100,100);
		fillRectangle(xAv,ybase-averageSpeed, breddeAvg , hoydeAvg);
	}
}
