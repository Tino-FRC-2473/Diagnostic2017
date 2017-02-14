package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MotorTest extends Command {
	long mill;
	
	public MotorTest() {
		requires(Robot.train);
		mill = System.currentTimeMillis();
	}
	
	@Override
	public void execute() {
		Robot.train.runMotors(0.2);
	}
	
	@Override
	protected boolean isFinished() {
		return testFinished();
	}

	boolean testFinished() {
		boolean returner = false;
		String status = "testing...";
		if(System.currentTimeMillis() - mill > 5000) {
			if(Robot.train.diff() <= 200000) {
				status = "DriveTrain hardware functional...";
				returner = true;
			} else {
				status = "DriveTrain hardware faulty...";
			}
		}
		System.out.println(status);
		return returner;
	}
}