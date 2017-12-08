package org.usfirst.frc.team2473.robot;

import java.util.ArrayList;

import org.usfirst.frc.team2473.robot.subsystems.TrackableSubsystem;

public class SubsystemTracker {
	private final ArrayList<TrackableSubsystem> allSubsystems;
	
	public SubsystemTracker(ArrayList<TrackableSubsystem> subsystemList) {
		allSubsystems = subsystemList;
	}
	
	public void stop() {
		allSubsystems.forEach((s) -> s.stop());
	}
	
	public void logCurrentState() {
		for (TrackableSubsystem i: allSubsystems) {
			CrashTracker.logMarker(i.getClass().getSimpleName() + i.getState());
		}
	}
	
	public void logCurrentState(TrackableSubsystem r) {
		CrashTracker.logMarker(r.getClass().getSimpleName() + r.getState());
	}
}
