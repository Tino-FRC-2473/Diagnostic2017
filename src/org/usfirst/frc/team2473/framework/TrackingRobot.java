
package org.usfirst.frc.team2473.framework;

import java.io.PrintStream;
import java.util.stream.IntStream;

import org.usfirst.frc.team2473.utility.UtilityServerSocket;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public abstract class TrackingRobot extends IterativeRobot {
	private UtilityServerSocket dashServer;
	
	private CrashTracker crashTracker;
	private SubsystemManager subsystemManager;
	
	private PrintStream sendPrintStream;
	private InfoSendThread sendThread;
	
	private DatabaseAndPingThread dnpThread;
	
	protected abstract boolean runDashboardNetworking();
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
	
	private Command autoCmd;
	
	private void doublePrint(PrintStream ps, String s) {
		System.out.print(s);
		ps.print(s);
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		try {
			
			if(runDashboardNetworking()) {
				dashServer = new UtilityServerSocket(50505);
				dashServer.connect();
			}
			sendPrintStream = new PrintStream( runDashboardNetworking() ? dashServer.getSocket().getOutputStream() : System.out);
			
			if(runJetsonNetworking()) {
				dnpThread = new DatabaseAndPingThread();
			}
			
			crashTracker = new CrashTracker(sendPrintStream);
			subsystemManager = new SubsystemManager(crashTracker, getTSubsystems());
			sendThread = new InfoSendThread(sendPrintStream, subsystemManager);
			
			doublePrint(sendPrintStream, "Running: " + getProgramName() + "\n");
			doublePrint(sendPrintStream, "Autonomous: " + getAutonomousCommand().getClass().getSimpleName() + "\n");
			doublePrint(sendPrintStream, "Dashboard Networking: " + runDashboardNetworking() + ", ");
			doublePrint(sendPrintStream, "Jetson Networking: " + runJetsonNetworking() + "\n");
			IntStream.range(0, 44).forEach(e -> doublePrint(sendPrintStream, "*")); //tribute to pramukh naduthota
			doublePrint(sendPrintStream, "\n");
			
			innerRobotInit();
			
		} catch (Exception e) {
			System.out.println("ERROR IN TRACKINGROBOT STARTUP:\n\t" + e.getStackTrace());
		}
		
		try {
			crashTracker.logRobotInit();
			autoCmd = getAutonomousCommand();
			sendThread.start();
			dnpThread.start();
			innerRobotInit();
		} catch (Throwable t) {
			crashTracker.logThrowableCrash(t);
			throw t;
		}
	}
	
	public TrackableSubsystem getSubsystem(Class<? extends TrackableSubsystem> cls) {
		return subsystemManager.getSubsystem(cls);
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		try {
			
			crashTracker.logDisabledInit();
			innerDisabledInit();
			
		} catch (Throwable t) {
			
			crashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void disabledPeriodic() {
		try {
			
			Scheduler.getInstance().run();
			innerDisabledPeriodic();
			
		} catch (Throwable t) {
			
			crashTracker.logThrowableCrash(t);
			throw t;	
		}
	}

	@Override
	public void autonomousInit() {
		try {
			
			crashTracker.logAutoInit();
			innerAutonomousInit();
			
			autoCmd.start();
			
		} catch (Throwable t) {
			
			crashTracker.logThrowableCrash(t);
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
			
			crashTracker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void teleopInit() {
		try {
			
			crashTracker.logTeleopInit();
			innerTeleopInit();
			
		} catch (Throwable t) {
			
			crashTracker.logThrowableCrash(t);
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
			
			crashTracker.logThrowableCrash(t);
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
			
			crashTracker.logThrowableCrash(t);
			throw t;
		}
	}
}
