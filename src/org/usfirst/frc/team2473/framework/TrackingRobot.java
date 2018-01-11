
package org.usfirst.frc.team2473.framework;

import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public abstract class TrackingRobot extends IterativeRobot {
	public static SubsystemManager subs;
	
	private DatabaseAndPingThread dnpThread;
	
	protected abstract boolean runJetsonNetworking();
	
	protected abstract String getProgramName();
	protected abstract TrackableSubsystem[] getTSubsystems();
	protected abstract Command getAutonomousCommand();
	protected void innerRobotInit(){}
	protected void innerAutonomousInit(){}
	protected void innerAutonomousPeriodic(){}
	protected void innerTeleopInit(){}
	protected void innerTeleopPeriodic(){}
	protected void innerDisabledInit(){}
	protected void innerDisabledPeriodic(){}
	protected void innerTestInit(){}
	protected void innerTestPeriodic(){}
	
	private Command autoCmd;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("ROBOT INIT");
		try {
			if(runJetsonNetworking()) {
				dnpThread = new DatabaseAndPingThread();
			}
			
			subs = new SubsystemManager(getTSubsystems());
			
			System.out.println("Running: " + getProgramName() + "\n");
			System.out.println("Autonomous: " + getAutonomousCommand().getClass().getSimpleName() + "\n");
			System.out.println("Jetson Networking: " + runJetsonNetworking() + "\n");
			IntStream.range(0, 44).forEach(e -> System.out.println("*")); //tribute to pramukh naduthota
			System.out.println("\n");
			
			innerRobotInit();
			
		} catch (Exception e) {
			System.out.println("ERROR IN TRACKINGROBOT STARTUP:\n\t" + e.getStackTrace());
		}
		
		System.out.println("Robot Init");
		autoCmd = getAutonomousCommand();
		dnpThread.start();
		innerRobotInit();
	}
	
	public TrackableSubsystem getSubsystem(Class<? extends TrackableSubsystem> cls) {
		return subs.getSubsystem(cls);
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		System.out.println("DISABLED INIT");
		innerDisabledInit();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		innerDisabledPeriodic();
	}

	@Override
	public void autonomousInit() {
		System.out.println("AUTO INIT");
		innerAutonomousInit();
		if (autoCmd != null) {autoCmd.start();}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		innerAutonomousPeriodic();
	}

	@Override
	public void teleopInit() {
		System.out.println("TELEOP INIT");
		innerTeleopInit();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		innerTeleopInit();
	}
	
	@Override
	public void testInit() {
		System.out.println("TEST INIT");
		innerTestInit();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
		innerTestPeriodic();
	}
}
