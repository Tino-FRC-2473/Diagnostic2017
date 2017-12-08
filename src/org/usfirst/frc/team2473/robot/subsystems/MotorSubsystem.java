package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.CrashTracker;
import org.usfirst.frc.team2473.robot.commands.ExampleCommand;

import com.ctre.CANTalon;

/**
 *
 */
public class MotorSubsystem extends TrackableSubsystem {
	private CANTalon talon1;
	private double power;
	
	public MotorSubsystem(double power) {
		talon1 = new CANTalon(2);
		this.power = power;
	}

	public void initDefaultCommand() {
	}
	
	public void run() {
		try {
			talon1.set(power);
		}
		catch(Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void stop() {
		talon1.set(0);
		
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
