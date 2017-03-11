package org.usfirst.frc.team6038.robot;

import org.usfirst.frc.team2473.robot.ThreadingRobot;
import org.usfirst.frc.team6038.robot.commands.BreakbeamTest;
import org.usfirst.frc.team6038.robot.commands.ClimberTest;
import org.usfirst.frc.team6038.robot.commands.GyroTest;
import org.usfirst.frc.team6038.robot.commands.MotorTest;
import org.usfirst.frc.team6038.robot.subsystems.BreakbeamSystem;
import org.usfirst.frc.team6038.robot.subsystems.Climber;
import org.usfirst.frc.team6038.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6038.robot.subsystems.GyroSystem;

public class Robot extends ThreadingRobot {

	public static DriveTrain train; //drivetrain subsystem
	public static BreakbeamSystem beam; //breakbeam subsystem
	public static GyroSystem gyroSystem; //gyro subsystem
	public static Climber climber; //climber subsystem
	MotorTest motor_test; //this command is run to test the motors
	BreakbeamTest break_test; //this command is run to test the break beams
	GyroTest gyro_test; //this command is run to test the gyroscope
	ClimberTest climber_test1, climber_test2;
	final String mode = "climber1"; //this string is for testing purposes, and represents which of the tests are running
	/*
	 * Here are all of the possible tests and their representative Strings:
	 * "gyro" --> gyro_test
	 * "breakbeam" --> break_test
	 * "motor" --> motor_test
	 * */

	@Override
	public void robotInit() {
		setNetworking(false); //no networking is being run for this code
		climber = new Climber(); //creation of climber subsystem object
		train = new DriveTrain(this); //creation of the drivetrain subsystem object
		beam = new BreakbeamSystem(); //creation of the breakbeam subsystem object
		gyroSystem = new GyroSystem(); //creation of the gyro subsystem object
		climber_test1 = new ClimberTest(this, 1);
		climber_test2 = new ClimberTest(this, 2);
		motor_test = new MotorTest(); //creation of the motor test command object
		break_test = new BreakbeamTest(); //creation of the breakbeam test command object
		gyro_test = new GyroTest(this); //creation of the gyro test command object
		super.robotInit(); //run super.robotInit() for extended ThreadingRobot framework features
	}

	@Override
	public void updateDeviceCalls() {
		addDeviceCall("motor_fr", () -> train.getEncPosition("fr")); //add listener for motor motor_fr's encoder position with reference name "motor_fr"
		addDeviceCall("motor_fl", () -> train.getEncPosition("fl")); //add listener for motor motor_fl's encoder position with reference name "motor_fl"
		addDeviceCall("motor_br", () -> train.getEncPosition("br")); //add listener for motor motor_br's encoder position with reference name "motor_br"
		addDeviceCall("motor_bl", () -> train.getEncPosition("bl")); //add listener for motor motor_bl's encoder position with reference name "motor_fl"
		addDeviceCall("gyro", () -> gyroSystem.getValue()); //add listener for AnalogGyro gyro's heading with reference name "gyro"
		addDeviceCall("beam", () -> beam.getBreakbeamValue()); //add listener for the breakbeam object's value with reference name "beam"
		addDeviceCall("climber_motor1", () -> climber.getCurrent(1));
		addDeviceCall("climber_motor2", () -> climber.getCurrent(2));
	}

	@Override
	public void teleopPeriodic() {		
		//the following switch statement starts the correct command based on the value of the String mode
		switch (mode) {
		case "motor":
			motor_test.start();
			break;
		case "breakbeam":
			break_test.start();
			break;
		case "gyro":
			gyro_test.start();
			break;
		case "climber1":
			climber_test1.start();
			break;
		case "climber2":
			climber_test2.start();
			break;
		}
		super.runTeleop();
	}

	@Override
	public void resetEncoders() {
		train.motor_fr.setEncPosition(0);
		train.motor_fl.setEncPosition(0);
		train.motor_br.setEncPosition(0);
		train.motor_bl.setEncPosition(0);
	}
}
