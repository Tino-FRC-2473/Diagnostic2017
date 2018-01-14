package org.usfirst.frc.team2473.framework;

public class SubsystemManager {
	private TrackableSubsystem[] subsystems;
	
	public SubsystemManager(TrackableSubsystem[] arr) {
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
			System.out.println(i.getClass().getSimpleName() + ": " + i.getState());
		}
	}
	
	public void logCurrentState(TrackableSubsystem r) {
		System.out.println(r.getClass().getSimpleName() + ": " + r.getState());
	}
}
