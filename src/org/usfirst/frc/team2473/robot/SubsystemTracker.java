package org.usfirst.frc.team2473.robot;

import java.util.ArrayList;

import org.usfirst.frc.team2473.robot.subsystems.TrackableSubsystem;

public class SubsystemTracker {
	private CrashTracker crashTracker;
	private ArrayList<TrackableSubsystem> allSubsystems;
	
	public SubsystemTracker(CrashTracker crash, ArrayList<Class<? extends TrackableSubsystem>> subsystemClassList) {
		crashTracker = crash;
		allSubsystems = new ArrayList<TrackableSubsystem>();
		for(Class<? extends TrackableSubsystem> c : subsystemClassList) {
			allSubsystems.add((TrackableSubsystem)Instances.getInstanceOf(c));
		}
	}
	
	public void stop() {
		allSubsystems.forEach((s) -> s.stop());
	}
	
	public void logCurrentState() {
		for (TrackableSubsystem i: allSubsystems) {
			crashTracker.logMarker(i.getClass().getSimpleName() + i.getState());
		}
	}
	
	public void logCurrentState(TrackableSubsystem r) {
		crashTracker.logMarker(r.getClass().getSimpleName() + r.getState());
	}
}
