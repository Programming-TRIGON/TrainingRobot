/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems ;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  SpeedController backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor;
  Encoder encoderRight, encoderLeft;
 // Gyro gyro;


public DriveTrain(
   SpeedController backLeftMotor, SpeedController frontLeftMotor,
     SpeedController backRightMotor, SpeedController frontRightMotor,
       Encoder encoderRight, Encoder encoderLeft) {
  this.backLeftMotor = backLeftMotor;
  this.backRightMotor = backRightMotor;
  this.frontLeftMotor = frontLeftMotor;
  this.frontRightMotor = frontRightMotor;
  this.encoderRight = encoderRight;
  this.encoderLeft = encoderLeft;
 // this.gyro = gyro;


}

  public void SetPowerRight(double power){
    backRightMotor.set(power);
    frontRightMotor.set(power);
  }

  public void SetPowerLeft(double power){
    backLeftMotor.set(power);
    frontLeftMotor.set(power);
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

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
