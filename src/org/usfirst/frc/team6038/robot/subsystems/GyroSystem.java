package org.usfirst.frc.team6038.robot.subsystems;

import org.usfirst.frc.team6038.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GyroSystem extends Subsystem {
	AnalogGyro gyro; // gyro object

	public GyroSystem() {
		gyro = new AnalogGyro(RobotMap.gyro); // construct object in constructor
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public double getValue() { // returns the value, or heading, of the gyro
		return gyro.getAngle(); // return the angle of the gyro object
	}

	public void reset() { // resets the contents of the subsystem
		gyro.reset(); // reset heading of the gyro object
	}
}
