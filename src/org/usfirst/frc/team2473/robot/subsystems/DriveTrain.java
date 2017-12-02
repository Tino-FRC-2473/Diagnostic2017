package org.usfirst.frc.team2473.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public CANTalon motor_fr, motor_fl, motor_br, motor_bl; //4 CANTalon objects for the 4 motors
	
	public DriveTrain() {
		//construction of the 4 talons
		motor_fr = new CANTalon(RobotMap.FRONT_RIGHT);
		motor_fl = new CANTalon(RobotMap.FRONT_LEFT);
		motor_br = new CANTalon(RobotMap.BACK_RIGHT);
		motor_bl = new CANTalon(RobotMap.BACK_LEFT);
	}

	public void initDefaultCommand() {
		
	}

	public void runMotors(double pow) {
		motor_fr.set(pow);
		motor_fl.set(pow);
		motor_br.set(pow);
		motor_bl.set(pow);
	}
	
	public double getSpeed() { //in rpm
    	return motor_fl.getSpeed()*600;
    }
    
    public double getCurrent() { //in amps
    	return motor_fl.getOutputCurrent();
    }
}