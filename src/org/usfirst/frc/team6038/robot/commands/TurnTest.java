package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnTest extends Command {

	boolean reset, repeated;
	double target;
	Robot robot;

	public TurnTest(double deg, Robot bot) {
		requires(Robot.gyroSystem);
		requires(Robot.train);
		Robot.gyroSystem.reset();
		reset = false;
		repeated = false;
		target = deg;
		robot = bot;
	}
	
	@Override
	public void execute() {
		if(!reset && Robot.gyroSystem.getValue() == 0) {
			reset = true;
		} else if(reset) {
			if(target > 0) {
				Robot.train.turnRight(0.3);
			} else {
				Robot.train.turnLeft(0.3);
			}
			if(robot.getDeviceValue("gyro") == 0) {
				repeated = true;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		boolean returner = false;
		String message = "";
		if((robot.getDeviceValue("gyro") - target) <= 5) {
			returner = true;
			message = "Testing gyro";
		} else {
			returner = false;
			if(repeated) {
				message = "Gyro dysfunctional...";
			} else {
				message = "Testing...";
			}
		}
		System.out.println(message);
		return returner;
	}

}