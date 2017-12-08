package org.usfirst.frc.team2473.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class CrashTracker {
	UtilitySocket socket;
	
	public CrashTracker(UtilitySocket s) {
		socket = s;
	}
	
	public void logMarker(String s, Throwable exception) {
		
		try {
			String toSend = new Date().toString() + ", " + s;
			
			if (exception != null) {
				toSend += ", " + exception.getS;
				exception.printStackTrace(writer);
			}
			socket.sendLine(toSend);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logMarker(String s) {
		logMarker(s,null);
	}
	
	public void logRobotStartup() {
		logMarker("Robot startup");
	}
	
	public void logRobotInit() {
		logMarker("Robot init");
	}
	
	public void logAutoInit() {
		logMarker("Auto init");
	}
	
	public void logTeleopInit() {
		logMarker("Teleop init");
	}
	
	public void logDisabledInit() {
		logMarker("Disabled init");
	}
	
	public void logThrowableCrash(Throwable throwable) {
		logMarker("Exception", throwable);
	}
	
}
