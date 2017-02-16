package org.usfirst.frc.team6038.robot.subsystems;

import org.usfirst.frc.team6038.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BreakbeamSystem extends Subsystem {

	private DigitalInput breakBeam;
	
	public BreakbeamSystem() {
		breakBeam = new DigitalInput(RobotMap.breakbeamChannel);
	}
	
	@Override
	protected void initDefaultCommand() {

	}

	public double getBreakbeamValue() {
		if(breakBeam.get()) {
			return 1;
		} else {
			return 2;
		}
	}
}
