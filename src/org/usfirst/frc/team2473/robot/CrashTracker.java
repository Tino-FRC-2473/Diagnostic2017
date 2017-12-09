package org.usfirst.frc.team2473.robot;

import java.io.PrintStream;
import java.util.Arrays;

public class CrashTracker {
	PrintStream printStream;

	public CrashTracker(PrintStream ps) {
		printStream = ps;
	}

	public void logMarker(String s, Throwable exception) {
		String toSend = "[" + System.currentTimeMillis() + "] " + s;

		if (exception != null) {
			toSend += ", " + Arrays.toString(exception.getStackTrace());
		}
		printStream.print(toSend);
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
