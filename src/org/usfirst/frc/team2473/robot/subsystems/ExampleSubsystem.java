package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.CrashTracker;

/**
 *
 */
public class ExampleSubsystem extends RobotSubsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

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
	public void printToDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetSensors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String currentState() {
		// TODO Auto-generated method stub
		return null;
	}
}
