package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportRange;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RangeFinder extends Subsystem
{
	private Ultrasonic ultrasonic;

	public RangeFinder()
	{
		ultrasonic = new Ultrasonic(RobotMap.ultrasonicPingChannel, RobotMap.ultrasonicEchoChannel);
		ultrasonic.setDistanceUnits(Unit.kInches);
		ultrasonic.setAutomaticMode(true);
	}

	public double getRange()
	{
		return ultrasonic.getRangeInches();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportRange());
	}
}
