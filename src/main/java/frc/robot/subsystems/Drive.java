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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;




import com.ctre.phoenix.motorcontrol.InvertType; 
import com.ctre.phoenix.motorcontrol.ControlMode; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands. heehee

  public DifferentialDrive differentialDrive;
  public double leftSpeed; 
  public double rightSpeed;

  public TalonSRX leftFrontTalon = new TalonSRX(RobotMap.leftTalonMaster);
  public TalonSRX leftBackTalon = new TalonSRX(RobotMap.leftTalonSlave);
  public TalonSRX rightFrontTalon = new TalonSRX(RobotMap.rightTalonMaster);
  public TalonSRX rightBackTalon = new TalonSRX(RobotMap.rightTalonSlave);

  public PigeonIMU pigeonVinnie = new PigeonIMU(leftBackTalon);

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
    pigeonVinnie.configFactoryDefault();
    pigeonVinnie.setYaw(0.0);
    pigeonVinnie.setFusedHeading(0.0);
    
    
    leftBackTalon.follow(leftFrontTalon);
    rightBackTalon.follow(rightFrontTalon);

    leftFrontTalon.setInverted(false);
    rightFrontTalon.setInverted(true);
    leftBackTalon.setInverted(InvertType.FollowMaster);
    rightBackTalon.setInverted(InvertType.FollowMaster);

    rightFrontTalon.setSensorPhase(false);
    leftFrontTalon.setSensorPhase(false);
    rightBackTalon.setSensorPhase(false);
    leftBackTalon.setSensorPhase(false);

    rightFrontTalon.setNeutralMode(NeutralMode.Brake);
    leftFrontTalon.setNeutralMode(NeutralMode.Brake);
    rightBackTalon.setNeutralMode(NeutralMode.Brake);
    leftBackTalon.setNeutralMode(NeutralMode.Brake);
    rightFrontTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.pidLoopTimeout);
    leftFrontTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.pidLoopTimeout);

    leftFrontTalon.config_kF(0,RobotMap.driveGainsVelocity.kF);
    leftFrontTalon.config_kP(0,RobotMap.driveGainsVelocity.kP);
    leftFrontTalon.config_kI(0,RobotMap.driveGainsVelocity.kI);
    leftFrontTalon.config_kD(0,RobotMap.driveGainsVelocity.kD);

  
    rightFrontTalon.config_kF(0,RobotMap.driveGainsVelocity.kF);
    rightFrontTalon.config_kP(0,RobotMap.driveGainsVelocity.kP);
    rightFrontTalon.config_kI(0,RobotMap.driveGainsVelocity.kI);
    rightFrontTalon.config_kD(0,RobotMap.driveGainsVelocity.kD);

    zeroSensors();

		
//    differentialDrive = new DifferentialDrive(leftFrontTalon, rightFrontTalon);
  }
  public void setPower(double leftPower, double rightPower){
    leftFrontTalon.set(ControlMode.PercentOutput, leftPower);
   rightFrontTalon.set(ControlMode.PercentOutput, rightPower);

 
     }



         /** Zero Quadrature Encoders on Talons */
		void zeroSensors() {
			leftFrontTalon.getSensorCollection().setQuadraturePosition(0, RobotMap.pidLoopTimeout);
			rightFrontTalon.getSensorCollection().setQuadraturePosition(0, RobotMap.pidLoopTimeout);
			System.out.println("[Quadrature Encoders] All sensors are zeroed.\n");
		}
		
public void setArcade(double velocity, double turn){
  mercyArcadeDrive(velocity, turn);
 // differentialDrive.arcadeDrive(velocity, turn);
}
//Mercy Arcade Drive allows us to smoothly control the robot
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



			double targetVelocity_UnitsPer100ms_left = leftSpeed * 1000;
			double targetVelocity_UnitsPer100ms_right = rightSpeed * 1000;

		
			leftFrontTalon.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms_left);
			rightFrontTalon.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms_right);
			

//leftFrontTalon.set(ControlMode.PercentOutput, leftSpeed);
   //rightFrontTalon.set(ControlMode.PercentOutput, rightSpeed);
  
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