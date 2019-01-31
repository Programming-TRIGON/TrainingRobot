/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.PathfinderConstants;
import frc.robot.Robot;
import frc.robot.commands.DriveJoystick;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DriveTrain extends Subsystem {
  SpeedController backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor;
  Encoder encoderRight, encoderLeft;
  AnalogGyro gyro;
  EncoderFollower encoderFollowerLeft, encoderFollowerRight;
  Notifier encoderNotifier;
  DifferentialDrive differentialDrive;

  boolean inverted = false;

  public DriveTrain(SpeedController rightBack, SpeedController rightFront, SpeedController leftBack,
      SpeedController leftFront, Encoder encoderRight, Encoder encoderLeft, AnalogGyro gyro) {
    this.backRightMotor = rightBack;
    this.frontRightMotor = rightFront;
    this.backLeftMotor = leftBack;
    this.frontLeftMotor = leftFront;
    this.encoderRight = encoderRight;
    this.encoderLeft = encoderLeft;
    this.gyro = gyro;

    SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(backRightMotor, frontRightMotor);
    SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(backLeftMotor, frontLeftMotor);
    this.differentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  }

  public void arcadeDrive(double x, double y) {
    this.differentialDrive.arcadeDrive(x, y);
  }

  public void tankDrive(double left, double right){
    this.differentialDrive.tankDrive(left, right);
  }

  public int encRightTicks() {
    return encoderRight.get();
  }

  public int encLeftTicks() {
    return encoderLeft.get();
  }
  public double gyroAngle(){
    return gyro.getAngle();
  }

  /*
   * public double gyroGet(){ return gyro.getRate(); }
   */
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveJoystick(Robot.m_oi.xbox));
  }

  public void toggleInverted() {
    this.inverted = !this.inverted;
  }

  public boolean isInverted() {
    return this.inverted;
  }

}
