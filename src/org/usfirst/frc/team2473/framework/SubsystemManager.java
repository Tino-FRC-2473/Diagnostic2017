package org.usfirst.frc.team2473.framework;

public class SubsystemManager {
	private CrashTracker crashTracker;
	private TrackableSubsystem[] subsystems;
	
	public SubsystemManager(CrashTracker crash, TrackableSubsystem[] arr) {
		crashTracker = crash;
		subsystems = arr;
	}
	
	public TrackableSubsystem getSubsystem(Class<? extends TrackableSubsystem> cls) {
		for(int i = 0; i < subsystems.length; i++) {
			if(subsystems[i].getClass().equals(cls)) {
				return subsystems[i];
			}
		}
		return null;
	}
	
	public TrackableSubsystem[] getSubsystems() {
		return subsystems;
	}
	
	public void logCurrentState() {
		for (TrackableSubsystem i: subsystems) {
			crashTracker.logMarker(i.getClass().getSimpleName() + i.getState());
		}
	}
	
	public void logCurrentState(TrackableSubsystem r) {
		crashTracker.logMarker(r.getClass().getSimpleName() + r.getState());
	}
}
