
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2473.robot.commands.ExampleCommand;
import org.usfirst.frc.team2473.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TrackingRobot {

	public static ExampleSubsystem exampleSubsystem = ExampleSubsystem.getInstance();
	public static OI oi;

	Command autonomousCommand;
	
	@Override
	protected Command getAutoCommand() {
		return new ExampleCommand();
	}
	
	@Override
	protected void innerRobotInit() {
		oi = new OI();
	}

	@Override
	public void disabledPeriodic() {
		try {
			Scheduler.getInstance().run();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		try {
			/*
			 * String autoSelected = SmartDashboard.getString("Auto Selector",
			 * "Default"); switch(autoSelected) { case "My Auto":
			 * autonomousCommand = new MyAutoCommand(); break; case
			 * "Default Auto": default: autonomousCommand = new
			 * ExampleCommand(); break; }
			 */

			// schedule the autonomous command (example)
			CrashTracker.logAutoInit();
			if (autonomousCommand != null)
				autonomousCommand.start();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		try {
			Scheduler.getInstance().run();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		try {
			CrashTracker.logTeleopInit();
			if (autonomousCommand != null)
				autonomousCommand.cancel();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		try {
			Scheduler.getInstance().run();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		try {
			LiveWindow.run();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	
}
