
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team2473.robot.commands.Type1AutoCommand;
import org.usfirst.frc.team2473.robot.subsystems.MotorSubsystem;
import org.usfirst.frc.team2473.robot.subsystems.TrackableSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TrackingRobot {
	public static Controls oi;
	
	@Override
	protected String getProgramName() {
		return "Architecture Testing";
	}
	
	@Override
	protected ArrayList<Class<? extends TrackableSubsystem>> getSubsystemClasses() {
		return new ArrayList<Class<? extends TrackableSubsystem>>(Arrays.asList(MotorSubsystem.class));
	}
	
	@Override
	protected Class<? extends Command> getAutonomousCommand() {
		return Type1AutoCommand.class;
	}
	
	@Override
	protected void innerRobotInit() {
		oi = Controls.getInstance();
	}
	
	@Override
	protected void innerAutonomousInit() {
		
	}

	@Override
	protected void innerAutonomousPeriodic() {
		
	}

	@Override
	protected void innerTeleopInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void innerTeleopPeriodic() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void innerDisabledInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void innerDisabledPeriodic() {
		// TODO Auto-generated method stub
		
	}
}
