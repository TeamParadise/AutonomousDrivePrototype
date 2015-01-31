/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot using input from the joysticks.
 * 
 * @author student
 */
public class DriveWithJoystick extends Command
{
    public DriveWithJoystick()
    {
        requires(Robot.driveTrain);
    }

    protected void initialize() 
    {
    }

    protected void execute() 
    { 
        double dampingFactor = Robot.oi.getDampening();       // Between 0 and 1; 0 = No damping 

        double calX     = Robot.oi.getDriveX();
        double calY     = Robot.oi.getDriveY(); 
        double calTwist = Robot.oi.getDriveTwist(); 

        calX     = dampingFactor*calX*calX*calX             + (1-dampingFactor)*calX; 
        calY     = dampingFactor*calY*calY*calY             + (1-dampingFactor)*calY; 
        calTwist = dampingFactor*calTwist*calTwist*calTwist + (1-dampingFactor)*calTwist; 

        Robot.driveTrain.driveCartesian(calX, calY, calTwist, 0);
    }
    
    // This command never finishes.
    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        stop();
    }

    protected void interrupted() 
    {
        stop();
    }
    
    /** Stop all drive motion */
    private void stop()
    {
        Robot.driveTrain.driveCartesian(0, 0, 0, 0);
    }
}