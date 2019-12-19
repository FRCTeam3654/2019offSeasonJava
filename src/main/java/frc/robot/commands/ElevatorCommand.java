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
import frc.robot.subsystems.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class ElevatorCommand extends Command {
  private boolean elevatorFlag = true; //true means that the elevator is moving
  
  public ElevatorCommand() {
    requires(Robot.elevator); 
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    elevatorFlag = true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
//On robot, the wheel turning backwards makes the elevator go up and the wheel turning forwards makes it go down
   
//need to make elevatorPower object
    if (elevatorFlag){ 
      if (!Robot.oi.elevatorDownButton.get() && !Robot.oi.elevatorUpButton.get()){
        Robot.elevator.setPower(0);  
        elevatorFlag = false;
      }
      if (Robot.oi.elevatorDownButton.get()){
        Robot.elevator.setPower(RobotMap.elevatorDownSpeed);
         
      }
      if (Robot.oi.elevatorUpButton.get()){ 
        Robot.elevator.setPower(RobotMap.elevatorUpSpeed);
      }
   }
 }

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (!Robot.oi.elevatorDownButton.get() && !Robot.oi.elevatorUpButton.get()){
      elevatorFlag = false;
      return true;
    }

    else if (!elevatorFlag){
      
      return true;
    }
    

    return false;
    }
  

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.setPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
