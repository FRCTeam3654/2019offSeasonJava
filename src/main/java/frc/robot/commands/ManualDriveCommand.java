/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
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
    joystickX = Robot.mathFunctions.handleDeadband(joystickX, RobotMap.joystickDeadBand);
    joystickY = Robot.mathFunctions.handleDeadband(joystickY, RobotMap.joystickDeadBand);
    if (Robot.oi.turboButton.get()){
    }
    else{
      joystickX = joystickX * RobotMap.nonTurboMultiplierTurn; 
      joystickY = joystickY * RobotMap.nonTurboMultiplierForward; 
    }

    //Robot.drive.setArcade(joystickX, joystickY);
    Robot.mathFunctions.mercyArcadeDrive(joystickX, joystickY);
   
    SmartDashboard.putNumber("Left Speed",Robot.mathFunctions.LeftSet);
    SmartDashboard.putNumber("Right Speed",Robot.mathFunctions.RightSet);
    SmartDashboard.putNumber("Left Encoder", Robot.drive.leftFrontTalon.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Right Encoder", Robot.drive.rightFrontTalon.getSelectedSensorVelocity());    

    System.out.println("X=" + joystickX + "Y =" + joystickY + "L=" + Robot.mathFunctions.LeftSet + "R =" + Robot.mathFunctions.RightSet);
    Robot.drive.setPower(Robot.mathFunctions.LeftSet, Robot.mathFunctions.RightSet);

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
