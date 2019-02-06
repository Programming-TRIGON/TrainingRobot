/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems ;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.commands.DriveJoystick;


public class DriveTrain extends Subsystem {
  public SpeedController backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor;
  Encoder encoderRight, encoderLeft;
  DifferentialDrive differentialDrive;
  DigitalInput Switch;
 private ADXRS450_Gyro gyro;

public DriveTrain(
  SpeedController rightBack, SpeedController rightFront, 
  SpeedController leftBack, SpeedController leftFront, 
  Encoder encoderRight, Encoder encoderLeft,ADXRS450_Gyro /*AnalogGyro*/ gyro) {
  this.backRightMotor = rightBack;
  this.frontRightMotor = rightFront;
  this.backLeftMotor = leftBack;
  this.frontLeftMotor = leftFront;
  this.encoderRight = encoderRight;
  this.encoderLeft  = encoderLeft;
  this.gyro = gyro;
  gyro.calibrate();
  this.Switch = new DigitalInput(4);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(backRightMotor, frontRightMotor);
  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(backLeftMotor, frontLeftMotor);
  this.differentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);


}
  public void arcadeDrive(double x, double y){
    this.differentialDrive.arcadeDrive(y, x);
  }

  public void arcadeDrive(double x, double y, boolean cheesyDrive){
    this.differentialDrive.curvatureDrive(y, x, cheesyDrive);
  }

  public double encRightTicks(){
    return encoderRight.getDistance();
  }

  public double encLeftTicks(){
    return encoderLeft.getRate();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveJoystick(Robot.m_oi.xbox));
  }

  public double getAngle(){
    return this.gyro.getAngle();
  }
  public boolean isAlive(){
    return this.gyro.isConnected();
  }

  public boolean getSwitch(){
    return this.Switch.get();
  }
}
