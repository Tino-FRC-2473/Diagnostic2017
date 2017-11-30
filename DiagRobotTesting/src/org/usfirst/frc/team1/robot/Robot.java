package org.usfirst.frc.team1.robot;

import org.usfirst.frc.team2473.robot.ThreadingRobot;
import org.usfirst.frc.team1.robot.subsystems.DriveTrain;

public class Robot extends ThreadingRobot {

	public static DriveTrain train; //drivetrain subsystem
	final String mode = "climber1"; //this string is for testing purposes, and represents which of the tests are running
	/*
	 * Here are all of the possible tests and their representative Strings:
	 * "gyro" --> gyro_test
	 * "breakbeam" --> break_test
	 * "motor" --> motor_test
	 * */

	@Override
	public void robotInit() {
		setNetworking(false); //no networking is being run for this code=
		train = new DriveTrain(this); //creation of the drivetrain subsystem object
		super.robotInit(); //run super.robotInit() for extended ThreadingRobot framework features
	}

	@Override
	public void updateDeviceCalls() {
		addDeviceCall("motor_fr", () -> train.getEncPosition("fr")); //add listener for motor motor_fr's encoder position with reference name "motor_fr"
		addDeviceCall("motor_fl", () -> train.getEncPosition("fl")); //add listener for motor motor_fl's encoder position with reference name "motor_fl"
		addDeviceCall("motor_br", () -> train.getEncPosition("br")); //add listener for motor motor_br's encoder position with reference name "motor_br"
		addDeviceCall("motor_bl", () -> train.getEncPosition("bl")); //add listener for motor motor_bl's encoder position with reference name "motor_fl"
	}

	@Override
	public void teleopPeriodic() {		
		
	}

	@Override
	public void resetEncoders() {
		train.motor_fr.setEncPosition(0);
		train.motor_fl.setEncPosition(0);
		train.motor_br.setEncPosition(0);
		train.motor_bl.setEncPosition(0);
	}
}