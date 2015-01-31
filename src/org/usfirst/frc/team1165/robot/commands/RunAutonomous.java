package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunAutonomous extends CommandGroup
{

	public RunAutonomous()
	{
		// Add elements to smart dashboard for driving forward:
		SmartDashboard.putNumber("Forward Speed", -1);
		SmartDashboard.putNumber("Brake Range", 50.0);
		SmartDashboard.putNumber("Target Range", 19.0);
		SmartDashboard.putNumber("Creep Speed", -0.1);

		// Add elements to smart dashboard for driving sideways:
		SmartDashboard.putNumber("Sideways Spd", 0);
		SmartDashboard.putNumber("Sideways X", 0);
		SmartDashboard.putNumber("Sideways Y", 0);
		SmartDashboard.putNumber("Sideways Rot", 0);

		addSequential(new DriveToObject("Forward Speed", "Brake Range", "Target Range", "Creep Speed"));
	}
}
