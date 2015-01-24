package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToObject extends Command
{
    private String magnitudeKey;
    private String xKey;
    private String yKey;
    private String rotationKey;
    private String brakeRangeKey;
    private String rangeKey;
    private String creepMagnitudeKey;
    
    private double magnitude;
    private double x;
    private double y;
    private double rotation;
    private double brakeRange;
    private double targetRange;
    private double creepMagnitude;
    
    private double currentRange;
    private double previousRange;
    private double twistCorrection;
    private boolean appliedBrakes;
   

    /** 
     * Uses values from the smart dashboard to drive the robot
     * @param magnitudeKey
     * @param directionKey
     * @param timeoutKey 
     * @param rotationKey 
     */
    public DriveToObject(String magnitudeKey, String xKey, String yKey, String rotationKey, String brakeRangeKey, String rangeKey, String creepMagnitudeKey)
    {
        requires(Robot.driveTrain);
        this.magnitudeKey = magnitudeKey;
        this.xKey = xKey;
        this.yKey = yKey;
        this.rotationKey = rotationKey;
	this.brakeRangeKey = brakeRangeKey;
	this.rangeKey = rangeKey;
	this.creepMagnitudeKey = creepMagnitudeKey;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
	// See if we are using values from the smart dashboard:
	if (null != magnitudeKey)
	{
	    magnitude = SmartDashboard.getNumber(magnitudeKey);
	    x = SmartDashboard.getNumber(xKey);
	    y = SmartDashboard.getNumber(yKey);
	    rotation = SmartDashboard.getNumber(rotationKey);
	    brakeRange = SmartDashboard.getNumber(brakeRangeKey);
	    targetRange = SmartDashboard.getNumber(rangeKey);
	    creepMagnitude = SmartDashboard.getNumber(creepMagnitudeKey);
	}
	Robot.gyroscope.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	
	// We drive forward until we reach brakeRange.
	// We then reverse the motors until we come to a stop.
	// We then creep forwards until we reach range.
	currentRange = Robot.rangeFinder.getRange();
	twistCorrection = Robot.gyroscope.getTwistCorrection();
	
	if (currentRange > brakeRange)
	{
	    Robot.driveTrain.driveCartesian(1, 0, rotation+twistCorrection, 0);
	    Robot.analogOutput0.setVoltage(2.2);
	}
	else if (previousRange - currentRange > .1) 
	{
	    
	    Robot.driveTrain.driveCartesian(-1, 0, rotation+twistCorrection, 0);
	    Robot.analogOutput0.setVoltage(1.6);
	    
//	    if ( currentRange - previousRange > .1)
//	    {
//		Robot.driveTrain.driveCartesian(0,0,0,0);
//		Robot.analogOutput0.setVoltage(0);
//	    }
	}
	else if (currentRange > targetRange  )
	{
	    Robot.driveTrain.driveCartesian(creepMagnitude, 0, rotation+twistCorrection, 0);
	}
	
	previousRange = currentRange;

	//	Robot.driveTrain.driveCartesian(x, y, rotation, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	boolean isFinished = Robot.rangeFinder.getRange() <= targetRange;
	if (isFinished)
	{
	    Robot.analogOutput0.setVoltage(0);
	}
	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
