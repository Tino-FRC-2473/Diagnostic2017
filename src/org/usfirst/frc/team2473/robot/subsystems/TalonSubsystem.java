package org.usfirst.frc.team2473.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TalonSubsystem extends Subsystem {
	private CANTalon talon;
	
	public TalonSubsystem() {
		talon = new CANTalon(2);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public void setPow(double pow) {
    	talon.set(pow);
    }
    
    public double getSpeed() { //in rpm
    	return talon.getSpeed()*600;
    }
    
    public double getCurrent() { //in amps
    	return talon.getOutputCurrent();
    }
}

