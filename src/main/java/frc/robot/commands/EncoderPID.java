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

public class EncoderPID extends Command {
  PIDController encoderPIDController;

  public EncoderPID() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

}

  @Override
  protected void execute() { 
       PIDOutput encoderPIDOutput = new PIDOutput(){
      public void pidWrite(double output) {
        Robot.driveTrain.arcadeDrive(output, 0);
      
    }
  };
  
    PIDController encoderPIDController = new PIDController(0.2, 0, 0, Robot.encoderPIDSource, encoderPIDOutput);
    encoderPIDController.setSetpoint(1000);
    encoderPIDController.setAbsoluteTolerance(25);
    encoderPIDController.setOutputRange(-1, 1);
    encoderPIDController.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    encoderPIDController.disable();
    Robot.driveTrain.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
