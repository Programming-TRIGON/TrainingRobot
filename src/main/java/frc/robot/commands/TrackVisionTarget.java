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



public class TrackVisionTarget extends Command {
  
  public static enum VisionTarget {
    kHatch, kCargo, kRetroflector, kLine;
  }

  VisionPIDSource.VisionTarget target;
  PIDController visionPIDController;

  public TrackVisionTarget(VisionPIDSource.VisionTarget target) {
    this.target=target;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    VisionPIDSource visionPIDSource = new VisionPIDSource(this.target); 
    PIDOutput visionPIDOutput = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        Robot.driveTrain.arcadeDrive(output, 0);
      }
    };

    visionPIDController = new PIDController(0.4, 0, 0, visionPIDSource, visionPIDOutput);
    visionPIDController.setSetpoint(0);
    visionPIDController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    visionPIDController.disable();
  }
}
