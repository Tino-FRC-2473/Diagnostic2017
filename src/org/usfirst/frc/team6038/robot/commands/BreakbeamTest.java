package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BreakbeamTest extends Command {

	public BreakbeamTest() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.beam);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Place an object in front of the breakbeam sensor.");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Waiting...");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Robot.beam.getBreakbeamValue() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Breakbeam system operational.");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("A fatal excaption 0E has occured at 0028:C0011E36 in UXD UMM(01)" + "\n"
				+ "The current application will be terminated.");
	}
}
