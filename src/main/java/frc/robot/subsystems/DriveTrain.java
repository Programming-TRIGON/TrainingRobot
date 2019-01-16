/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems ;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveJoystick;


public class DriveTrain extends Subsystem {
  public SpeedController backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor;
  Encoder encoderRight, encoderLeft;
 // Gyro gyro;


public DriveTrain(
  SpeedController rightBack, SpeedController rightFront, 
  SpeedController leftBack, SpeedController leftFront, 
  Encoder encoderRight, Encoder encoderLeft) {
  this.backRightMotor = rightBack;
  this.frontRightMotor = rightFront;
  this.backLeftMotor = leftBack;
  this.frontLeftMotor = leftFront;
  this.encoderRight = encoderRight;
  this.encoderLeft  = encoderLeft;
 // this.gyro = gyro;


}

  public void SetPowerRight(double x, double y){
    backRightMotor.set(y - x);
    frontRightMotor.set(y - x);
  }

  public void SetPowerLeft(double x, double y){
    backLeftMotor.set((y + x)/2);
    frontLeftMotor.set((y + x)/2);
  }

  public double encRightTicks(){
    return encoderRight.get();
  }

  public double encLeftTicks(){
    return encoderLeft.get();
  }

 /* public double gyroGet(){
    return gyro.getRate();
  } */ 

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveJoystick());
  }

}
