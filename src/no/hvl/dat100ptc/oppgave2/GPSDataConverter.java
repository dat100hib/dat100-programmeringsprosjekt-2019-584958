package no.hvl.dat100ptc.oppgave2;

import java.util.Arrays;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// 2017-08-13T08:52:26.000Z
	// [2,0,1,7,-,0,8,....0,0,0,Z]

	public static int toSeconds(String timestr) {
		
		int secs=0;
		int hr, min, sec;
		
		//Setter hele Stringen inn i en ny String tabell.
		String [] timeStrTab=timestr.split("");
		String [] timeStrNum= { 
				timeStrTab[11],timeStrTab[12], //timer 
				timeStrTab[14],timeStrTab[15], //minutter
				timeStrTab[17],timeStrTab[18]  //sekunder
		};
		
		//Skriver om tabell til Int timer.
		if (timeStrNum[0].equals("0")) {
			hr=Integer.parseInt(timeStrNum[1]);
			secs+=(hr*3600);
		} else {
			hr=Integer.parseInt(timeStrNum[0]+timeStrNum[1]);
			secs+=(hr*3600);
		}
		
		//Skriver om tabell til Int minutter.
		if (timeStrNum[2].equals("0")) {
			min=Integer.parseInt(timeStrNum[3]);
			secs+=(min*60);
		} else {
			min=Integer.parseInt(timeStrNum[2]+timeStrNum[3]);
			secs+=(min*60);
		}
		
		//Skriver om tabell til Int sekunder.
		if (timeStrNum[4].equals("0")) {
			sec=Integer.parseInt(timeStrNum[5]);
			secs+=(sec);
		} else {
			sec=Integer.parseInt(timeStrNum[4]+timeStrNum[5]);
			secs+=(sec);
		}
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint=new GPSPoint(toSeconds(timeStr), Double.parseDouble(latitudeStr),
				Double.parseDouble(longitudeStr) , Double.parseDouble(elevationStr));
		return gpspoint;
	}
}
