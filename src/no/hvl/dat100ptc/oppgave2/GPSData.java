package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		//Setter antall 0 og lager et objekt for GPSPoints i tabellen gpspoints.
		this.antall=0;
		gpspoints= new GPSPoint[antall];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		//Hvis antall er større enn lengden på tabellen vil den ikke sette 
		//noe inn i den. Setter gpspoint inn i tabellen på gitt plass lik antall.
		//inkrementerer antall, slik at neste gpspoint blir satt i riktig pos.
		if (gpspoints.length>antall) {
			gpspoints[antall]= gpspoint;
			antall++;
			inserted=true;
		}
		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;
		gpspoint= GPSDataConverter.convert(time, latitude, longitude, elevation);
		if (insertGPS(gpspoint)) {
			return true;
		}else {
			return false;
		}
	}

	public void print() {
		
		System.out.println("====== Konvertert GPS Data - START ======");
		for (int i = 0; i<antall; i++) {
			gpspoints[i].toString();
		}
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
