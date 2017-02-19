package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MotorTest extends Command {
	long mill; //stores the current time in milliseconds
	
	public MotorTest() {
		requires(Robot.train); //command requires the drivetrain object
		mill = System.currentTimeMillis(); //assign mill
	}
	
	@Override
	public void execute() {
		Robot.train.runMotors(0.2); //continuously run the motors at 0.2 power
	}
	
	@Override
	protected boolean isFinished() {
		return testFinished(); //return the testFinished() value
	}

	boolean testFinished() {
		boolean returner = false; //value to be returned is currently false
		String status = "testing..."; //set status default value
		if(System.currentTimeMillis() - mill > 5000) { //if the elapsed time is over 5 seconds
			if(Robot.train.diff() <= 200000) { //if the difference between the least and greatest encoder value is <= 200000
				status = "DriveTrain hardware functional..."; //update status
				returner = true; //value to be returned is true
			} else {
				status = "DriveTrain hardware faulty..."; //update status
			}
		}
		System.out.println(status); //print status
		return returner; //return value to be returned
	}
}