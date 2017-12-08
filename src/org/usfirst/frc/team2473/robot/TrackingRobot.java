
package org.usfirst.frc.team2473.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public abstract class TrackingRobot extends IterativeRobot {
	UtilitySocket socket;
	CrashTracker tracker;
	
	protected abstract void innerRobotInit();
	protected abstract void innerDisabledInit();
	protected abstract void innerDisabledPeriodic();
	protected abstract void innerAutonomousInit();
	protected abstract void innerAutonomousPeriodic();
	protected abstract void innerTeleopInit();
	protected abstract void innerTeleopPeriodic();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		try {
			System.out.println("WAITING FOR CLIENT CONNECTION");
			socket = new UtilitySocket("10.24.73.2", 50505);
			tracker = new CrashTracker(socket);
			System.out.println("CONNECTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			CrashTracker.logRobotInit();
			innerRobotInit();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		try {
			CrashTracker.logDisabledInit();
			innerDisabledInit();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void disabledPeriodic() {
		try {
			Scheduler.getInstance().run();
			innerDisabledPeriodic();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void autonomousInit() {
		try {
			CrashTracker.logAutoInit();
			innerAutonomousInit();
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
			innerAutonomousPeriodic();
		} catch (Throwable t) {
			CrashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void teleopInit() {
		try {
			CrashTracker.logTeleopInit();
			innerTeleopInit();
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
			innerTeleopInit();
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
