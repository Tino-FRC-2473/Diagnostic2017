package org.usfirst.frc.team6038.robot;

import org.usfirst.frc.team2473.robot.ThreadingRobot;
import org.usfirst.frc.team6038.robot.commands.BreakbeamTest;
import org.usfirst.frc.team6038.robot.commands.GyroTest;
import org.usfirst.frc.team6038.robot.commands.MotorTest;
import org.usfirst.frc.team6038.robot.subsystems.BreakbeamSystem;
import org.usfirst.frc.team6038.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6038.robot.subsystems.GyroSystem;

public class Robot extends ThreadingRobot {

	public static DriveTrain train;
	public static BreakbeamSystem beam;
	public static GyroSystem gyroSystem;
	MotorTest motor_test;
	BreakbeamTest break_test;
	GyroTest gyro_test;
	final String mode = "motor";

	@Override
	public void robotInit() {
		setNetworking(false);
		train = new DriveTrain(this);
		motor_test = new MotorTest();
		break_test = new BreakbeamTest();
		gyro_test = new GyroTest(this);
		beam = new BreakbeamSystem();
		super.robotInit();
	}

	@Override
	public void updateDeviceCalls() {
		addDeviceCall("motor_fr", () -> train.getEncPosition("fr"));
		addDeviceCall("motor_fl", () -> train.getEncPosition("fl"));
		addDeviceCall("motor_br", () -> train.getEncPosition("br"));
		addDeviceCall("motor_bl", () -> train.getEncPosition("bl"));
		addDeviceCall("gyro", () -> gyroSystem.getValue());
		addDeviceCall("beam", () -> beam.getBreakbeamValue());
	}

	@Override
	public void teleopPeriodic() {		
		startTest();
		super.runTeleop();
	}
	
	public void startTest() {
		switch(mode) {
		case "motor":
			motor_test.start();
			break;
		case "breakbream":
			break_test.start();
			break;
		case "gyro":
			gyro_test.start();
			break;
		}		
	}
}