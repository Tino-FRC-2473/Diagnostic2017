
package org.usfirst.frc.team2473.robot;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.usfirst.frc.team2473.robot.subsystems.TrackableSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public abstract class TrackingRobot extends IterativeRobot {
	private ServerSocket server;
	private Socket socket;
	private CrashTracker crashTracker;
	private SubsystemTracker subsystemTracker;
	private SenderThread senderThread;
	
	protected abstract String getProgramName();
	protected abstract ArrayList<Class<? extends TrackableSubsystem>> getSubsystemClasses();
	protected abstract Class<? extends Command> getAutonomousCommand();
	protected abstract void innerRobotInit();
	protected abstract void innerAutonomousInit();
	protected abstract void innerAutonomousPeriodic();
	protected abstract void innerTeleopInit();
	protected abstract void innerTeleopPeriodic();
	protected abstract void innerDisabledInit();
	protected abstract void innerDisabledPeriodic();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		try {
			System.out.println("Running: " + getProgramName());
			System.out.println("Autonomous: " + getAutonomousCommand().getSimpleName());
			IntStream.range(0, 44).forEach(e -> System.out.print("*")); //tribute to pramukh naduthota
			System.out.println("");
			
			server = new ServerSocket(50505);
			System.out.println("WAITING FOR CLIENT CONNECTION");
			socket = server.accept();
			System.out.println("CONNECTED");
			
			
			crashTracker = new CrashTracker(new PrintStream(socket.getOutputStream()));
			subsystemTracker = new SubsystemTracker(crashTracker, getSubsystemClasses());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			crashTracker.logRobotInit();
			innerRobotInit();
		} catch (Throwable t) {
			crashTracker.logThrowableCrash(t);
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
			MemeError m = new MemeError();
		} catch (Throwable t) {
			crashTracker.logThrowableCrash(t);
			//throw t;
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
