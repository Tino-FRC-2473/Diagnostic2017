package org.usfirst.frc.team6038.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc.team6038.robot.Robot;
import org.usfirst.frc.team6038.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public CANTalon motor_fr, motor_fl, motor_br, motor_bl;
	Robot robot;
	
	public DriveTrain(Robot bot) {
		motor_fr = new CANTalon(RobotMap.motor_fr);
		motor_fl = new CANTalon(RobotMap.motor_fl);
		motor_br = new CANTalon(RobotMap.motor_br);
		motor_bl = new CANTalon(RobotMap.motor_bl);
		robot = bot;
	}

	public void initDefaultCommand() {

	}

	public double getEncPosition(String motor) {
		switch (motor) {
		case "fr":
			return motor_fr.getEncPosition();
		case "fl":
			return motor_fl.getEncPosition();
		case "br":
			return motor_br.getEncPosition();
		case "bl":
			return motor_bl.getEncPosition();
		}
		return -1;
	}

	public void runMotors(double pow) {
		motor_fr.set(pow);
		motor_fl.set(pow);
		motor_br.set(pow);
		motor_bl.set(pow);
	}
	
	public double diff() {
		ArrayList<Double> list = new ArrayList<>();
		if(robot.getDeviceValue("motor_fr") > 0) {
			list.add(robot.getDeviceValue("motor_fr"));			
		}
		if(robot.getDeviceValue("motor_fl") > 0) {
			list.add(robot.getDeviceValue("motor_fl"));
		}
		if(robot.getDeviceValue("motor_br") > 0) {
			list.add(robot.getDeviceValue("motor_br"));
		}
		if(robot.getDeviceValue("motor_bl") > 0) {
			list.add(robot.getDeviceValue("motor_bl"));
		}
		System.out.println(list);
		Collections.sort(list);		
		System.out.println(list);
		double returner = list.get(list.size() - 1) - list.get(0);
		System.out.println("Encoder difference: " + returner);
		return returner;
	}	
}