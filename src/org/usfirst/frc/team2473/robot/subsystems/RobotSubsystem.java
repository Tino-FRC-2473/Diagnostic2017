package org.usfirst.frc.team2473.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class RobotSubsystem extends Subsystem{
	public void writeToLog() {
    };

    public abstract void printToDashboard();

    public abstract void stop();

    public abstract void resetSensors();
}
