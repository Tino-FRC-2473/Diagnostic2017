package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.CrashTracker;

/**
 *
 */
public class ExampleSubsystem extends TrackableSubsystem {
	private static ExampleSubsystem theInstance;

	static {
		theInstance = new ExampleSubsystem();
	}
	
	@Override
	public TrackableSubsystem getInstance() {
		return theInstance;
	}
	
	private ExampleSubsystem() {
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void run() {
		try {
			// add actual code here
		}
		catch(Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String currentState() {
		return null;
	}

	
}
