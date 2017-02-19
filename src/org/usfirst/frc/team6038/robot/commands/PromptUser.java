package org.usfirst.frc.team6038.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import java.util.Scanner;

public class PromptUser extends Command{

	String command; //command to be printed
	Scanner scan; //Scanner object for user input

	public PromptUser(String s) {
		//Construction of described attributes
		command = s;
		scan = new Scanner(System.in);
	}
	
	@Override
	public void execute() {
		System.out.println(command); //print the command continuously
	}
	
	@Override
	protected boolean isFinished() {
		return scan.nextLine().equals("\n"); //isFinished() returns true if enter is pressed by the user, meaning the command keeps running until enter is pressed
	}
}
