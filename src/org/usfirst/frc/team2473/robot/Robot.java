
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2473.robot.commands.ExampleCommand;
import org.usfirst.frc.team2473.robot.subsystems.MotorSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TrackingRobot {

	public static MotorSubsystem motorSub;
	public static ExampleCommand exCmd;
	public static Controls oi;
	
	@Override
	protected void innerRobotInit() {
		oi = Controls.getInstance();
		motorSub = (MotorSubsystem)Instances.getInstanceOf(MotorSubsystem.class);
		exCmd = new ExampleCommand();
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
	
	@Override
	protected void innerAutonomousInit() {
		exCmd.start();
	}

	@Override
	protected void innerDisabledInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void innerDisabledPeriodic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void innerAutonomousPeriodic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void innerTeleopInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void innerTeleopPeriodic() {
		// TODO Auto-generated method stub
		
	}
}
