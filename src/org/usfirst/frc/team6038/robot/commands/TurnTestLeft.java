package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnTestLeft extends Command {

	boolean reset, completed;
	double target, pow;
	Robot robot;

	public TurnTestLeft(double deg, Robot bot) {
		requires(Robot.gyroSystem);
		requires(Robot.train);
		Robot.gyroSystem.reset();
		reset = false;
		target = deg;
		robot = bot;
		pow = 0.3;
	}
	
	@Override
	public void execute() {
		if(!reset && (Math.floor(Robot.gyroSystem.getValue()) == 0 || Math.floor(Robot.gyroSystem.getValue()) == -0)) {
			reset = true;
		} else if(reset) {
			Robot.train.turnLeft(pow);
		}
	}

	@Override
	protected boolean isFinished() {
		boolean returner = false;
		String message = "";
		if(Math.abs(robot.getDeviceValue("gyro") - target) <= 5) {
			returner = true;
			message = "Gyro functional...";
			completed = true;
			pow = 0;
		} else {
			returner = false;
			message = "Testing...";
		}
		if(completed) {
			message = "Gyro functional";
			Robot.train.stop();
			end();
		}
		System.out.println(message);
		return returner;
	}
	
	@Override
	public void end() {
		Robot.train.stop();
	}
}