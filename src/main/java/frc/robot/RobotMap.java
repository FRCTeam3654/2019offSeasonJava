/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Preferences;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  // test
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  //:) 
  // Experimentally derived output value (percent output)

static Preferences prefs; 

public static double leftPercentOutput = 1.0;
public static double rightPercentOutput = 1.0;

public static int driverJoystickPort = 0;

public static int leftTalonMaster = 1;
public static int rightTalonMaster = 2;
public static int leftTalonSlave = 3;
public static int rightTalonSlave = 4;

public static double joystickDeadBand = 0.05;

//Must be experimentally derived
public static double driveStraightProportion = 0.02;
public static double turnDegreeProportion = 0.007;
public static double turn90DegreeTimeout = 1.5;

public static double leftOverRightCompensation = .98;

public static double talonDriveAccelerationRate = 0.3654;

public static double nonTurboMultiplierTurn = 0.35;
public static double nonTurboMultiplierForward = 0.5; 

/*these are the pid gains responsiveness to the control loop
*kF: 1023 represents toutput value to Talon at 100%, 7200 represents velocity units at 100% output
*
*                                                           kP,  kI,  kD,    kF,  Iz,  PeakOutput*/
public final static Gains driveGainsVelocity = new Gains( 0.25, 0.0, 0.0, 1.015, 400, 1);
public static int pidLoopTimeout = 30;


public static boolean driveClosedLoopMode = true;


public static int turboButtonNumber = 1; 
public static int driveStraightButtonNumber = 2;
public static int turn90ButtonNumber = 4;

public static void getPreference(){
prefs = Preferences.getInstance();
driveClosedLoopMode = prefs.getBoolean("DriveClosedLoopMode", true);
}

}
