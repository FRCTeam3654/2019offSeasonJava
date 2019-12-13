/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorCommand;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType; 
import com.ctre.phoenix.motorcontrol.ControlMode; 
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  public TalonSRX elevatorTalon = new TalonSRX(RobotMap.elevatorTalonID);
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Elevator (){
    elevatorTalon.configFactoryDefault();

    elevatorTalon.setNeutralMode(NeutralMode.Brake);
    /*elevatorTalon.setInverted(false); if needed for later
    elevatorTalon.setSensorPhase(false);
    elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.pidLoopTimeout);
    elevatorTalon.config_kF(0,RobotMap.driveGainsVelocity.kF);
    elevatorTalon.config_kP(0,RobotMap.driveGainsVelocity.kP);
    elevatorTalon.config_kI(0,RobotMap.driveGainsVelocity.kI);
    elevatorTalon.config_kD(0,RobotMap.driveGainsVelocity.kD);

    zeroSensors();
    
*/

  }

  void zeroSensors() {
    elevatorTalon.getSensorCollection().setQuadraturePosition(0, RobotMap.pidLoopTimeout);
    System.out.println("[Quadrature Encoders] elevator sensor is zeroed.\n");
  }
}
