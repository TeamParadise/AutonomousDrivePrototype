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
	SmartDashboard.putNumber("Forward Spd", .6);
	SmartDashboard.putNumber("Forward X", 270);
	SmartDashboard.putNumber("Forward Y", 0);
	SmartDashboard.putNumber("Forward Rot", 0.65);
	SmartDashboard.putNumber("Brake Range", 12.0);
	SmartDashboard.putNumber("Forward Range", 2.0);
	SmartDashboard.putNumber("Creep Magnitude", 0.1);

	// Add elements to smart dashboard for driving sideways:
	SmartDashboard.putNumber("Sideways Spd", 0);
	SmartDashboard.putNumber("Sideways X", 0);
	SmartDashboard.putNumber("Sideways Y", 0);
	SmartDashboard.putNumber("Sideways Rot", 0);

	addSequential(new DriveToObject("Forward Spd", "Forward X", "Forward Y", "Forward Rot", "Brake Range", "Forward Range", "Creep Magnitude"));
    }
}
