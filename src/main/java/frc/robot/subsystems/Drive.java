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

  

}
// yeet :)
// you could lowkey highkey write a whole butterly essay in java code
//invert values 
//we created teh drive class but we still have to create the instance of that
//have to create a command class that links x and y output to the talon
//go off ig