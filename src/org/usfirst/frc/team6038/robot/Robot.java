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
	final String mode = "gyro";

	@Override
	public void robotInit() {
		setNetworking(false);
		train = new DriveTrain(this);
		beam = new BreakbeamSystem();
		gyroSystem = new GyroSystem();
		motor_test = new MotorTest();
		break_test = new BreakbeamTest();
		gyro_test = new GyroTest(this);
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