package org.usfirst.frc.team6038.robot.subsystems;

import org.usfirst.frc.team6038.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private CANTalon ropeMotor1;
	private CANTalon ropeMotor2;

	public Climber() {
		System.out.println("Init motors");
		ropeMotor1 = new CANTalon(RobotMap.ropeMotor1);
		ropeMotor2 = new CANTalon(RobotMap.ropeMotor2);
	}

	public void initDefaultCommand() {

	}

	public void climb(double value) {
		ropeMotor1.set(value);
		ropeMotor2.set(value);
	}

	public double getCurrent(int num) {
		if(num == 1) {
			return ropeMotor1.getOutputCurrent();
		} else {
			return ropeMotor2.getOutputCurrent();
		}
	}
	
	public void runIndividual(int num) {
		if(num == 1) {
			ropeMotor1.set(0.4);
		} else {
			ropeMotor2.set(0.4);
		}
	}
}
