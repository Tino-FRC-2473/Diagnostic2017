package org.usfirst.frc.team6038.robot.commands;

import org.usfirst.frc.team6038.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GyroTest extends CommandGroup {
	Robot robot;
	public GyroTest(Robot bot) {
		robot = bot;
		addSequential(new PromptUser("Set robot on the ground and press enter when completed...")); //ask user the set the robot down
		addSequential(new PromptUser("Step away from robot and press enter when completed")); //ask the user to step away from the robot
		addSequential(new TurnTestRight(45, bot)); //turn the robot right 45° and check the accuracy
		addSequential(new PromptUser("About to proceed in opposite direction...sensors to be calibrated. Keep away from bot and press enter.")); //inform the user that the test is not yet complete		
		addSequential(new TurnTestLeft(-45, bot)); //turn the robot left 45° and check the accuracy
	}
}