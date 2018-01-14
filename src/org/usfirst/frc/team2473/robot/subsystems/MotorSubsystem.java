package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class MotorSubsystem extends TrackableSubsystem {
	private TalonSRX talon1;
	private double power;
	
	public MotorSubsystem(double p) {
		talon1 = Devices.getInstance().getTalon(2);
		//talon2.follow(talon1);
		power = p;
	}

	public void initDefaultCommand() {
	}
	
	public void run() {
		talon1.set(ControlMode.PercentOutput, power);
	}

	@Override
	public void stop() {
		talon1.set(ControlMode.PercentOutput, 0);
		
	}

	@Override
	public String getState() {
		return "Current: " + talon1.getOutputCurrent();
	}
}
