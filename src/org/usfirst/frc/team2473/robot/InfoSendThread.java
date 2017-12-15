package org.usfirst.frc.team2473.robot;

import java.io.PrintStream;

import org.usfirst.frc.team2473.framework.SubsystemManager;
import org.usfirst.frc.team2473.robot.subsystems.TrackableSubsystem;

public class InfoSendThread extends Thread {
	private PrintStream printStream;
	private SubsystemManager subsystemManager;
	private boolean alive;
	
	public InfoSendThread(PrintStream ps, SubsystemManager sm) {
		printStream = ps;
		subsystemManager = sm;
		alive = true;
	}
	
	@Override
	public void run() {
		while(alive) {
			String str = "";
			for(int i = 0; i < subsystemManager.getSubsystems().length; i++) {
				TrackableSubsystem s = subsystemManager.getSubsystems()[i];
				str += "[" + s.getClass() + ": " + s.getState() + "] ";
			}
			printStream.print(str + "\n");
		}
	}
	
	public void kill() {
		alive = false;
	}
}