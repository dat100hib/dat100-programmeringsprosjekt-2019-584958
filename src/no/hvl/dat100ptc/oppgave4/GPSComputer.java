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
	
	//Gjorde de fleste av metodene under static for aa kunne referere til dem sener
	//i prosjektet. Gjorde private static GPSPoint[] gpspoints; på linje 11 static.
	
	// bereger total distances (i meter)
	public double totalDistance() {
		double distance = 0;
		for (int i = 0; i<gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}
		return distance;

	}

	// beregner total høydemeter (i meter)
	public double totalElevation() {
		//hvis det neste gps punktet er er mindre enn det første vil
		//vi sette elevation lik den høyeste elevation til gps punktene.
		double elevation = 0;
		for (int i = 0; i<gpspoints.length-1; i++) {
			if (gpspoints[i].getElevation()<gpspoints[i+1].getElevation()) {
				elevation=gpspoints[i+1].getElevation();
			}	
		}
		return elevation;
		
	}

	// beregner total tid for hele turen (i sekunder)
	public int totalTime() {
		//returnerer sluttid minus starttid for å får total tid.
		return (gpspoints[gpspoints.length-1].getTime()-gpspoints[0].getTime());
	}
		
	// beregner gjennomsnitshastighet mellom hver av gps punktene
	public double[] speeds() {
		double [] speedsTab = new double [gpspoints.length-1];
		for (int i = 0; i<gpspoints.length-1; i++) {
			speedsTab[i]=GPSUtils.speed(gpspoints[i],gpspoints[i+1]);
		}
		return speedsTab;
	}
	
	public double maxSpeed() {
		//bruker medtoden findMax som tar inn en tabell med double og returnerer det største tallet.
		return GPSUtils.findMax(speeds());
	}

	public double averageSpeed() {
		return totalDistance()/totalTime()*3.6;
	}

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		//kcal = MET * bodyweight(kg) * time (h)
		double met = 0;		
		double speedmph = speed * MS;

		if (speedmph<10 && speedmph>0) {
			met=4.0;
		} else if (10<=speedmph && speedmph<12) {
			met=6.0;
		} else if (12<=speedmph && speedmph<14) {
			met=8.0;
		}else if (14<=speedmph && speedmph<16) {
			met=10.0;
		}else if (16<=speedmph && speedmph<20) {
			met=12.0;
		}else if (20<=speedmph) {
			met=16.0;
		}
		
		kcal= met * weight * secs/3600;
		
		return kcal;
	}
//må snakke med folk på labben om hvorfor denne er feil.
	public double totalKcal(double weight) {
		return kcal(weight, totalTime(), averageSpeed());
	}
	
	private static double WEIGHT = 80.0;
	//String.format("%1$10.2f", d)
	public void displayStatistics() {
		//usikker på det hvordan man skal gjøre det med kcal ettersom
		//det ikke funker.
		System.out.println("==============================================");
		System.out.println("Total Time"+"\t:     "+ GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance"+"\t:"+String.format("%1$12.2f", totalDistance()/1000)+"km");
		System.out.println("Total elevation"+"\t:"+String.format("%1$12.2f", totalElevation())+"m");
		System.out.println("Max speed"+"\t:"+String.format("%1$12.2f", maxSpeed())+"km/t");
		System.out.println("Average speed"+"\t:"+String.format("%1$12.2f", averageSpeed())+"km/t");
		System.out.println("Energy"+"\t\t:"+String.format("%1$14.2f", totalKcal(WEIGHT))+"kcal");
		System.out.println("==============================================");
		
		
	}

}
