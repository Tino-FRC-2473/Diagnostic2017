package org.usfirst.frc.team6038.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberTest extends Command {
	private int maxSize;
	private boolean working;
	private ArrayList<Double> currentList;

	// Called just before this Command runs the first time
	protected void initialize() {
		requires(Robot.climber);
		maxSize = 4;
		working = false;
		currentList = new ArrayList<Double>();
		System.out.println("Started motor");
		Robot.climber.climb(0.3);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		currentList.add(Robot.climber.getCurrent());
		if (currentList.size() == maxSize) {
			double sum = 0;
			for (int i = 0; i < maxSize; i++) {
				sum += currentList.get(i);
			}
			if (sum / maxSize > 9) {
				working = false;
			}
			else {
				working = true;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (!working) {
			System.out.println("Not working, running again");
		}
		else {
			System.out.println("Working, stopping program");
		}
		return working;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climber.climb(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
