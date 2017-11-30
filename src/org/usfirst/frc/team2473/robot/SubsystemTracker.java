package org.usfirst.frc.team2473.robot;

import java.util.ArrayList;
import org.usfirst.frc.team2473.robot.subsystems.RobotSubsystem;

public class SubsystemTracker {
	private final ArrayList<RobotSubsystem> allSubsystems;
	
	public SubsystemTracker(ArrayList<RobotSubsystem> subsystemList) {
		allSubsystems = subsystemList;
	}
	
	public void writeToLog() {
		allSubsystems.forEach((s) -> s.writeToLog());
	}
	
	public void printToDashboard() {
		allSubsystems.forEach((s) -> s.printToDashboard());
	}
	
	public void stop() {
		allSubsystems.forEach((s) -> s.stop());
	}
	
	public void resetSensors() {
		allSubsystems.forEach((s) -> s.resetSensors());
	}
	
	public void logCurrentState() {
		for (RobotSubsystem i: allSubsystems) {
			CrashTracker.logMarker(i.getClass().getSimpleName() + i.currentState());
		}
	}
	
	public void logCurrentState(RobotSubsystem r) {
		CrashTracker.logMarker(r.getClass().getSimpleName() + r.currentState());
	}
}
