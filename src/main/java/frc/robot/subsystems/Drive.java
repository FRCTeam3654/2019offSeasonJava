/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.Robot;
// import edu.wpi.first.wpilibj.
import frc.robot.RobotMap;
import frc.robot.commands.ManualDriveCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType; 
import com.ctre.phoenix.motorcontrol.ControlMode; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands. heehee

  public DifferentialDrive differentialDrive;
  public double leftSpeed; 
  public double rightSpeed;

  public WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(RobotMap.leftTalonMaster);
  public WPI_TalonSRX leftBackTalon = new WPI_TalonSRX(RobotMap.leftTalonSlave);
  public WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(RobotMap.rightTalonMaster);
  public WPI_TalonSRX rightBackTalon = new WPI_TalonSRX(RobotMap.rightTalonSlave);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ManualDriveCommand());
  }
  public Drive() {


    leftFrontTalon.configFactoryDefault();
    leftBackTalon.configFactoryDefault();
    rightFrontTalon.configFactoryDefault();
    rightBackTalon.configFactoryDefault();
    
    leftBackTalon.follow(leftFrontTalon);
    rightBackTalon.follow(rightFrontTalon);

    leftFrontTalon.setInverted(false);
    rightFrontTalon.setInverted(true);
    leftBackTalon.setInverted(InvertType.FollowMaster);
    rightBackTalon.setInverted(InvertType.FollowMaster);

    rightFrontTalon.setSensorPhase(false);
    leftFrontTalon.setSensorPhase(false);
  
    differentialDrive = new DifferentialDrive(leftFrontTalon, rightFrontTalon);
  }
  public void setPower(double leftPower, double rightPower){
    leftFrontTalon.set(ControlMode.PercentOutput, leftPower);
   rightFrontTalon.set(ControlMode.PercentOutput, rightPower);

 
     }
public void setArcade(double velocity, double turn){
  differentialDrive.arcadeDrive(velocity, turn);
}
public void mercyArcadeDrive(double joystickX, double joystickY){
double radiusPower = Math.hypot(joystickX, joystickY);
double initAngle = Math.atan2(joystickX, joystickY);
initAngle = initAngle + Math.PI/4;
rightSpeed = radiusPower*Math.sin(initAngle);
leftSpeed = radiusPower*Math.cos(initAngle);
rightSpeed = rightSpeed*1.414;
leftSpeed = leftSpeed*1.414;
if (rightSpeed > 1) {
  rightSpeed = 1;
}
if (leftSpeed > 1) {
  leftSpeed = 1;
}
if (rightSpeed < -1) {
  rightSpeed = 1;
}
if (leftSpeed < -1) {
  leftSpeed = -1;
}


  
  
  /*Convert the initial (x,y) coordinates to polar coordinates.
  Rotate them by 45 degrees.
Convert the polar coordinates back to cartesian.
Rescale the new coordinates to -1.0/+1.0.
Clamp the new values to -1.0/+1.0.
This assumes the initial (x,y) coordinates are in the -1.0/+1.0 range. The side of the inner square will always be 
equal to l * sqrt(2)/2, so step 4 is just about multiplying the values by sqrt(2). */


  

}
}
// yeet :)
// you could lowkey highkey write a whole butterly essay in java code
//invert values 
//we created teh drive class but we still have to create the instance of that
//have to create a command class that links x and y output to the talon
//go off ig
//go off Mr. Kasznay 