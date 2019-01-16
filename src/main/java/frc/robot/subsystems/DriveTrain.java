/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems ;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveJoystick;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  public static SpeedController backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor;
  Encoder encoderRight, encoderLeft;
 // Gyro gyro;


public DriveTrain() {
  new VictorSP(RobotMap.BACK_LEFT_MOTOR);
  new VictorSP(RobotMap.FRONT_LEFT_MOTOR);
  new VictorSP(RobotMap.BACK_RIGHT_MOTOR);
  new VictorSP(RobotMap.FRONT_RIGHT_MOTOR);
  new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);
  new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B);
 // this.gyro = gyro;


}

  public static void SetPowerRight(double x, double y){
    backRightMotor.set(y - x);
    frontRightMotor.set(y - x);
  }

  public static void SetPowerLeft(double x, double y){
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

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveJoystick());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
