
package org.usfirst.frc.team2473.framework;

import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public abstract class TrackingRobot extends TimedRobot {
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
		System.out.println("[diag] robot init - arch setup");
		try {
			if(runJetsonNetworking()) {
				dnpThread = new DatabaseAndPingThread();
			}
			
			subs = new SubsystemManager(getTSubsystems());
			
			System.out.println("Running: " + getProgramName());
			System.out.println("Autonomous: " + getAutonomousCommand().getClass().getSimpleName());
			System.out.println("Jetson Networking: " + runJetsonNetworking());
			IntStream.range(0, 44).forEach(e -> System.out.print("*")); //tribute to pramukh naduthota
			System.out.println("\n");
			
			innerRobotInit();
			
		} catch (Exception e) {
			System.out.println("ERROR IN TRACKINGROBOT STARTUP:\n\t" + e.getStackTrace());
		}
		
		System.out.println("[diag] robot init - after arch setup");
		autoCmd = getAutonomousCommand();
		if(dnpThread != null) dnpThread.start();
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
		System.out.println("[diag] disabled init");
		innerDisabledInit();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		innerDisabledPeriodic();
	}

	@Override
	public void autonomousInit() {
		System.out.println("[diag] auto init");
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
		System.out.println("[diag] teleop init");
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
		System.out.println("[diag] test init");
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
