package org.usfirst.frc.team2473.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CrashTracker {
	
	public static void logMarker(String s, Throwable exception) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter("crash_tracker.txt"), true);
			writer.print(new Date().toString());
			writer.print(", ");
			writer.print(s);
			if (exception != null) {
				writer.print(", ");
				exception.printStackTrace(writer);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logMarker(String s) {
		logMarker(s,null);
	}
	
	public static void logRobotStartup() {
		logMarker("Robot startup");
	}
	
	public static void logRobotInit() {
		logMarker("Robot init");
	}
	
	public static void logAutoInit() {
		logMarker("Auto init");
	}
	
	public static void logTeleopInit() {
		logMarker("Teleop init");
	}
	
	public static void logDisabledInit() {
		logMarker("Disabled init");
	}
	
	public static void logThrowableCrash(Throwable throwable) {
		logMarker("Exception", throwable);
	}
}
