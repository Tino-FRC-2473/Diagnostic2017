package org.usfirst.frc.team6038.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberTest extends Command {
	Robot bot;
	private int maxSize;
	private boolean notWorking;
	private ArrayList<Double> currentList;
	private double power;

	public ClimberTest(Robot bot) {
		requires(Robot.climber);
		this.bot = bot;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		maxSize = 4;
		power = 0.3;
		notWorking = false;
		currentList = new ArrayList<Double>();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.climber.climb(power);
		currentList.add(bot.getDeviceValue("climber"));
		if (!notWorking && currentList.size() == maxSize) {
			double sum = 0;
			for (int i = 0; i < maxSize; i++) {
				sum += currentList.get(i);
			}
			double avg = sum / maxSize;
			if (avg > 9) {
				power = 0;
				notWorking = true;
				end();
			}
			else {
				System.out.println("Working at average of " + avg);
				notWorking = false;
			}
			currentList.remove(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !notWorking;
	}

	// Called once after isFinished returns true
	protected void end() {
		power = 0;
		System.out.println("Not Working/Disabled");
		Robot.climber.climb(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("Interrupted!");
		end();
	}
}
