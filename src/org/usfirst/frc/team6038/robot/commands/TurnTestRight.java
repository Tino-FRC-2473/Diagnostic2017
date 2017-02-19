package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnTestRight extends Command {

	boolean completed; //completed is true if the robot hits its target number of degrees
	//target is the target number of degrees to turn to; pow represents the power at which the robot is turning
	double target, pow;
	Robot robot; //robot object needed for access to certain listeners(using ThreadingRobot framework)

	public TurnTestRight(double deg, Robot bot) {
		//program requires 2 subsystems, both created already in the Robot class
		requires(Robot.gyroSystem);
		requires(Robot.train);
		Robot.gyroSystem.reset(); //reset the gyros
		target = deg; //set target number of degrees
		robot = bot; //assign the Robot object passed in as a parameter
		pow = 0.3; //assign the power passed in as a parameter
	}
	
	@Override
	public void execute() {
		Robot.train.turnRight(pow); //continuously turn the robot to the right direction
	}

	@Override
	protected boolean isFinished() {
		boolean returner = false; //value to be returned by isFinished currently false
		String message = ""; //status message to print
		if(Math.abs(robot.getDeviceValue("gyro") - target) <= 5) { //if the gyro value is within 5
			returner = true; //value to be returned is true
			message = "Gyro functional..."; //update status
			completed = true; //set completed variable to true
			pow = 0; //power is 0
		} else {
			returner = false; //value to be returned is false, testing continues
			message = "Testing..."; //update status
		}
		if(completed) { //if completed is true
			message = "Gyro functional"; //update status
			Robot.train.stop(); //run stop()
			end(); //if stop() doesnt work, run end()
		}
		System.out.println(message); //print the status message
		return returner; //return the value to be returned
	}
	
	@Override
	public void end() { //is called when the command is to end
		Robot.train.stop();
	}
}