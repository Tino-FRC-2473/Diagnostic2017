package org.usfirst.frc.team2473.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class TrackableSubsystem extends Subsystem {
    public abstract void stop();
    public abstract String getState();
}
