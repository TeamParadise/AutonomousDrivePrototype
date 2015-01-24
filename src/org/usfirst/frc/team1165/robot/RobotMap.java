package org.usfirst.frc.team1165.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
    // Wheel motors: Digital I/O PWM
    public static final int frontLeftMotorChannel	= 0;
    public static final int frontRightMotorChannel	= 1;
    public static final int rearLeftMotorChannel	= 2;
    public static final int rearRightMotorChannel	= 3;
    
    // Ultrasonic sensor
    public static final int ultrasonicPingChannel	= 0;
    public static final int ultrasonicEchoChannel	= 1;
    
    // Gyroscope
    public static final int gyroscopeChannel		= 0;

}
