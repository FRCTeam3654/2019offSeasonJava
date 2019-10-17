/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.Drive;
import frc.robot.RobotMap;

public class ManualDriveCommand extends Command {
  public ManualDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

//this is melanie making changes to your code, mwahahahha
// ANdy is awesome
//Karena 
//test
// test change

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double joystickX;
    double joystickY;
    joystickX = Robot.oi.driverStick.getX();
    joystickY = Robot.oi.driverStick.getY();
    joystickX = handleDeadband(joystickX, RobotMap.joystickDeadBand);
    joystickY = handleDeadband(joystickY, RobotMap.joystickDeadBand);
    if (Robot.oi.turboButton.get()){
    }
    else{
      joystickX = joystickX * RobotMap.nonTurboMultiplierTurn; 
      joystickY = joystickY * RobotMap.nonTurboMultiplierForward; 
    }
    System.out.println("X=" + joystickX + "Y =" + joystickY);
    Robot.drive.setArcade(joystickX, joystickY);


  }
  public double handleDeadband(double val, double deadband){
    return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
