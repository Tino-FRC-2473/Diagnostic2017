package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.TrackableSubsystem;

import com.ctre.CANTalon;

/**
 *
 */
public class MotorSubsystem extends TrackableSubsystem {
	private CANTalon talon1;
	private double power;
	
	public MotorSubsystem() {
		talon1 = new CANTalon(2);
		this.power = .5;
	}

	public void initDefaultCommand() {
	}
	
	public void run() {
		talon1.set(power);
	}

	@Override
	public void stop() {
		talon1.set(0);
		
	}

	@Override
	public String getState() {
		return "Current: " + talon1.getOutputCurrent() + ", Speed: " + talon1.getSpeed();
	}
}
