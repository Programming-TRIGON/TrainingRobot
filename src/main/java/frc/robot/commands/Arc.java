/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.TwoEncoderPIDSource;
import frc.robot.subsystems.GyroPIDSource;

public class Arc extends Command {
  double wheelDistance, radius, circumference, innerCircle, outerArc,
  innerArc, innerWheelSpeed, outerWheelSpeed, angle,
  initialDistance,requiredDistance;
  PIDController driveDistanceController;
  PIDOutput driveDistanceOutput;
  
  public Arc(double wheelDistance, double radius, double angle, Double outerWheelSpeed) {
   requires(Robot.drivetrain);
   this.wheelDistance = wheelDistance;
   this.radius = radius;
   this.angle = angle;
   this.outerWheelSpeed = outerWheelSpeed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    circumference = radius * 2 * Math.PI;
    outerArc = circumference / (360 / angle);
    innerCircle = (radius - wheelDistance) * 2 * Math.PI;
    innerArc = innerCircle / (360 / angle);
    innerWheelSpeed = innerArc / outerArc * outerWheelSpeed;
  
    requiredDistance = (outerArc + innerArc)/2;
    initialDistance = Robot.drivetrain.getDistance();
    Robot.drivetrain.tankDrive(outerWheelSpeed, innerWheelSpeed);
    
    this.driveDistanceOutput = new PIDOutput(){
      public void pidWrite(double output) {
        Robot.drivetrain.arcadeDrive(output, 0);
      }
    };
    this.driveDistanceController = new PIDController(0.2, 0, 0,
    new GyroPIDSource() , this.driveDistanceOutput);

    driveDistanceController.setSetpoint(requiredDistance);
    driveDistanceController.setAbsoluteTolerance(0.1);
    driveDistanceController.setOutputRange(-1, 1);
    driveDistanceController.enable();    this.driveDistanceOutput = new PIDOutput(){
      public void pidWrite(double output) {
        Robot.drivetrain.arcadeDrive(output, 0);
      }
    };
    this.driveDistanceController = new PIDController(0.2, 0, 0,
    new TwoEncoderPIDSource() , this.driveDistanceOutput);

    driveDistanceController.setSetpoint(requiredDistance);
    driveDistanceController.setAbsoluteTolerance(0.1);
    driveDistanceController.setOutputRange(-1, 1);
    driveDistanceController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return driveDistanceController.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveDistanceController.disable();
    Robot.drivetrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivetrain.tankDrive(0, 0);
  }
}
