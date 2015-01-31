package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToObject extends Command
{
	private String forwardMagnitudeKey;
	private String brakeRangeKey;
	private String targetRangeKey;
	private String creepMagnitudeKey;

	private double forwardMagnitude;
	private double brakeRange;
	private double targetRange;
	private double creepMagnitude;

	private double currentRange;
	private double previousRange;
	private double twistCorrection;
	
	private boolean isCreeping;
	
	/**
	 * Uses values from the smart dashboard to drive the robot
	 * 
	 * @param forwardMagnitudeKey
	 * @param directionKey
	 * @param timeoutKey
	 * @param rotationKey
	 */
	public DriveToObject(String forwardMagnitudeKey, String brakeRangeKey, String targetRangeKey, String creepMagnitudeKey)
	{
		requires(Robot.driveTrain);
		this.forwardMagnitudeKey = forwardMagnitudeKey;
		this.brakeRangeKey = brakeRangeKey;
		this.targetRangeKey = targetRangeKey;
		this.creepMagnitudeKey = creepMagnitudeKey;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// See if we are using values from the smart dashboard:
		if (null != forwardMagnitudeKey)
		{
			forwardMagnitude = SmartDashboard.getNumber(forwardMagnitudeKey);
			brakeRange = SmartDashboard.getNumber(brakeRangeKey);
			targetRange = SmartDashboard.getNumber(targetRangeKey);
			creepMagnitude = SmartDashboard.getNumber(creepMagnitudeKey);
		}
		
		Robot.gyroscope.reset();
		isCreeping = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		SmartDashboard.putBoolean("Is creeping", isCreeping);
		
		// We drive forward until we reach brakeRange.
		// We then reverse the motors until we come to a stop.
		// We then creep forwards until we reach range.
		currentRange = Robot.rangeFinder.getRange();
		twistCorrection = Robot.gyroscope.getTwistCorrection();
		
		if (currentRange > brakeRange)
		{
			Robot.driveTrain.driveCartesian(forwardMagnitude, 0, twistCorrection, 0);
		}
		else if (!isCreeping && previousRange > currentRange)
		{
			Robot.driveTrain.driveCartesian(-forwardMagnitude, 0, twistCorrection, 0);
		}
		else
		{ 
			isCreeping = true;
			Robot.driveTrain.driveCartesian(creepMagnitude, 0, twistCorrection, 0);
		}

		previousRange = currentRange;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.rangeFinder.getRange() <= targetRange;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.driveTrain.driveCartesian(0, 0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
