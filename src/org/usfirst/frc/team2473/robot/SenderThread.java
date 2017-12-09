package org.usfirst.frc.team2473.robot;

import java.io.PrintStream;

public class SenderThread extends Thread {
	private PrintStream toPrintTo;
	
	public SenderThread(PrintStream toPrintTo) {
		this.toPrintTo = toPrintTo;
	}
	
	@Override
	public void run() {
		toPrintTo.print("test\n");
	}
}
