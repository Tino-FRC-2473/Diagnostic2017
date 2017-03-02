package org.usfirst.frc.team6038.robot.commands;

import java.util.Scanner;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GyroTest extends Command {
	private Robot bot;
	private Scanner scan;

	public GyroTest(Robot bot) {
		this.bot = bot;
		scan = new Scanner(System.in);
		requires(Robot.gyroSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Current gyro value: " + bot.getDeviceValue("gyro"));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return scan.nextLine().equals("\n");
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
