/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
//  import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
//  import java.math.*;
import edu.wpi.first.wpilibj.Timer;

public class Turn90DegreesCommand extends Command {
  private boolean turn90Flag = false;
  private double turn90Angle = 0;
  private double vinniesError = 2;//greater than 1 so it doesn't get triggered until the error is correct
  private double startTime90degree = 0;

  public Turn90DegreesCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double turn90X;
    double turn90Y;
    double [] yawPitchRollArray;
    yawPitchRollArray = new double [3];

    Robot.drive.pigeonVinnie.getYawPitchRoll(yawPitchRollArray);
     
      if (!turn90Flag){
        turn90Angle = yawPitchRollArray[0] + 90;
        startTime90degree = Timer.getFPGATimestamp();
        turn90Flag = true;
      }
      vinniesError = turn90Angle - yawPitchRollArray[0];
       turn90X = vinniesError * RobotMap.turnDegreeProportion;
       turn90X = Math.min(1, turn90X);
       turn90X = Math.max(-1, turn90X);
       turn90Y = 0;
       Robot.drive.setArcade(turn90X, turn90Y);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.abs(vinniesError) < 3){
      turn90Flag = false;
      return true;
    }
    if(startTime90degree + RobotMap.turn90DegreeTimeout < Timer.getFPGATimestamp()) {
      turn90Flag = false;
      return true;
    }
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
    turn90Flag = false;
  }
}
