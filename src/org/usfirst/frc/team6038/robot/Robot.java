package org.usfirst.frc.team6038.robot;

import org.usfirst.frc.team2473.robot.ThreadingRobot;
import org.usfirst.frc.team6038.robot.commands.MotorTest;
import org.usfirst.frc.team6038.robot.subsystems.DriveTrain;

public class Robot extends ThreadingRobot {

	public static DriveTrain train;
	MotorTest test;

	@Override
	public void robotInit() {
		setNetworking(false);
		train = new DriveTrain(this);
		test = new MotorTest();
		super.robotInit();
	}

	@Override
	public void updateDeviceCalls() {
		addDeviceCall("motor_fr", () -> train.getEncPosition("fr"));
		addDeviceCall("motor_fl", () -> train.getEncPosition("fl"));
		addDeviceCall("motor_br", () -> train.getEncPosition("br"));
		addDeviceCall("motor_bl", () -> train.getEncPosition("bl"));
	}
	
	@Override
	public void teleopPeriodic() {
		System.out.println("teleop running");
		test.start();
		super.runTeleop();
	}
}