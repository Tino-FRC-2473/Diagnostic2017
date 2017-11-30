package org.usfirst.frc.team1.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc.team1.robot.Robot;
import org.usfirst.frc.team1.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public CANTalon motor_fr, motor_fl, motor_br, motor_bl; //4 CANTalon objects for the 4 motors
	Robot robot; //robot object for ThreadingRobot framework functionality
	
	public DriveTrain(Robot bot) {
		//construction of the 4 talons
		motor_fr = new CANTalon(RobotMap.motor_fr);
		motor_fl = new CANTalon(RobotMap.motor_fl);
		motor_br = new CANTalon(RobotMap.motor_br);
		motor_bl = new CANTalon(RobotMap.motor_bl);
		robot = bot; //assign the Robot object a passed value
	}

	public void initDefaultCommand() {

	}

	public double getEncPosition(String motor) { //returns the encoder position based on the passed in string representing the motor name
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
		return -1; //default value returned is -1
	}

	public void runMotors(double pow) { //run all 4 motors
		motor_fr.set(pow);
		motor_fl.set(pow);
		motor_br.set(pow);
		motor_bl.set(pow);
	}
	
	public double diff() {
		//add the encoder values to a list if they are > 0
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
		System.out.println(list); //print list for testing purposes
		Collections.sort(list); //sort the list
		System.out.println(list); //print again for testing
		double returner = list.get(list.size() - 1) - list.get(0); //difference between largest and smallest entities in list
		System.out.println("Encoder difference: " + returner); //print the difference
		return returner; //return the difference
	}	
	
	public void turnLeft(double pow) { //turn the drive train to the left
		motor_fr.set(pow);
		motor_fl.set(pow);
		motor_br.set(pow);
		motor_bl.set(pow);
	}

	public void turnRight(double pow) { //turn the drive train to the right
		motor_fr.set(-pow);
		motor_fl.set(-pow);
		motor_br.set(-pow);
		motor_bl.set(-pow);
	}
	
	public void stop() { //stop the drive train motors
		motor_fr.set(0);
		motor_fl.set(0);
		motor_br.set(0);
		motor_bl.set(0);
	}
}