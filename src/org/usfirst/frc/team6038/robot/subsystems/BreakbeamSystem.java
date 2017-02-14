package org.usfirst.frc.team6038.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BreakbeamSystem extends Subsystem {

	private DigitalInput breakBeam;

	@Override
	protected void initDefaultCommand() {

	}

	public boolean getBreakbeamValue() {
		return breakBeam.get();
	}
}
