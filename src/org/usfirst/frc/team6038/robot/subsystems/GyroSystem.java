package org.usfirst.frc.team6038.robot.subsystems;

import org.usfirst.frc.team6038.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GyroSystem extends Subsystem {
	AnalogGyro gyro;
	
	public GyroSystem() {
		gyro = new AnalogGyro(RobotMap.gyro);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public double getValue() {
		return gyro.getAngle();
	}
	
	public void reset() {
		gyro.reset();
	}
}
