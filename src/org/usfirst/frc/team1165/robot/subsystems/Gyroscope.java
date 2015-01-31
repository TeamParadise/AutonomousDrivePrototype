package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportGyroscope;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gyroscope extends Subsystem
{
	private Gyro gyro;

	final double twistPower = 0.275;
	final double smallTwistPower = 0.1;
	final double smallAngle = 1;
	final double bigAngle = 2;
	private double angle;

	public Gyroscope()
	{
		gyro = new Gyro(RobotMap.gyroscopeChannel);
		gyro.setSensitivity(7.0 / 1000); // 7 millivolts per degree per second
		reset();
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportGyroscope());
	}

	public double getHeading()
	{
		return gyro.getAngle();
	}

	public double getTwistCorrection()
	{

		angle = getHeading();

		if (angle > bigAngle)
		{
			return angle > 0 ? twistPower : -twistPower;
		}
		if (angle < smallAngle)
		{
			return 0;
		}
		return angle > 0 ? smallTwistPower : -smallTwistPower;
	}

	public void reset()
	{
		gyro.reset();
	}
}
