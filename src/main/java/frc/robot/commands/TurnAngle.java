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
import frc.robot.subsystems.GyroPIDSource;

public class TurnAngle extends Command {
  double angle;
  PIDController turnAngleController;
  PIDOutput turnAngleOutput;
   
  public TurnAngle(double angle) {
    requires(Robot.drivetrain);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.turnAngleOutput = new PIDOutput(){
      public void pidWrite(double output) {
        Robot.drivetrain.arcadeDrive(0, output);
      }
    };
    this.turnAngleController = new PIDController(0.2, 0, 0,
    new GyroPIDSource() , this.turnAngleOutput);

    turnAngleController.setSetpoint(angle);
    turnAngleController.setAbsoluteTolerance(0.1);
    turnAngleController.setOutputRange(-1, 1);
    turnAngleController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return turnAngleController.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    turnAngleController.disable();
    Robot.drivetrain.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
