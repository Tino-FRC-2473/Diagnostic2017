package org.usfirst.frc.team6038.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import java.util.Scanner;

public class PromptUser extends Command{

	String command;
	Scanner scan;

	public PromptUser(String s) {
		command = s;
		scan = new Scanner(System.in);
	}
	
	@Override
	public void execute() {
		System.out.println(command);
	}
	
	@Override
	protected boolean isFinished() {
		return scan.nextLine().equals("\n");
	}
}
