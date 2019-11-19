/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualDriveCommand extends Command {
  private boolean driveStraightFlag = false;
  private double driveStraightAngle = 0;
  public ManualDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

//this is melanie making changes to your code, mwahahahha
// ANdy is awesome yes he is
//Karena 
//test
// test change

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double joystickX;
    double joystickY;
    double [] yawPitchRollArray;
    yawPitchRollArray = new double [3];
    joystickX = (Robot.oi.driverStick.getX() * -1);
    joystickY = (Robot.oi.driverStick.getY() * -1);
    joystickX = handleDeadband(joystickX, RobotMap.joystickDeadBand);
    joystickY = handleDeadband(joystickY, RobotMap.joystickDeadBand);
    //This is to activate turbo mode. If the button is pressed, turbo mode is on
    
    if (Robot.oi.turboButton.get()){
    }
    else{
      joystickX = joystickX * RobotMap.nonTurboMultiplierTurn; 
      joystickY = joystickY * RobotMap.nonTurboMultiplierForward;
    }
    Robot.drive.pigeonVinnie.getYawPitchRoll(yawPitchRollArray);
    if (Robot.oi.driveStraightButton.get()){
      //joystickX = 0;
      if (!driveStraightFlag){
        driveStraightAngle = yawPitchRollArray[0];
        driveStraightFlag = true;
      }
      double vinniesError = driveStraightAngle - yawPitchRollArray[0];
      joystickX = vinniesError * RobotMap.driveStraightProportion;
    }

    else {
      driveStraightFlag = false;
    }


    System.out.println("X=" + joystickX + "Y=" + joystickY);
    Robot.drive.setArcade(joystickX, joystickY);
    

    //Dashboard features for Joystick x and y values and right and left encoders
    SmartDashboard.putNumber("Joystick X: ", joystickX);
    SmartDashboard.putNumber("Joystick Y: ", joystickY);
    SmartDashboard.putNumber("Left Encoder", Robot.drive.leftFrontTalon.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Right Encoder", Robot.drive.rightFrontTalon.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Yaw: ", yawPitchRollArray[0]);
  }


  //Deadband makes the center of the joystick have leeway on absolute 0
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