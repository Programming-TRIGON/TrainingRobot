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
import frc.robot.JoystickPIDSource;
import frc.robot.Robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;



public class TrackVisionTarget extends Command {
  
  VisionPIDSource.VisionTarget target;
  PIDController visionPIDController;

  final Supplier<Double> kP = ConstantHandler.addConstantDouble("kP", 0.7);
  final Supplier<Double> kI = ConstantHandler.addConstantDouble("kI", 0.7);
  final Supplier<Double> kD = ConstantHandler.addConstantDouble("kD", 0.7);
  final Supplier<Double> tolerance = ConstantHandler.addConstantDouble("tolerance", 0.7);

  final int SETPOINT = 0;

  public TrackVisionTarget(VisionPIDSource.VisionTarget target) {
    this.target=target;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    VisionPIDSource visionPIDSource = new VisionPIDSource(this.target); 
    //JoystickPIDSource joystickPIDSource = new JoystickPIDSource(); 
    PIDOutput visionPIDOutput = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        Robot.driveTrain.arcadeDrive(output, 0);
      }
    };

    visionPIDController = new PIDController(this.kP.get(), this.kI.get(), this.kD.get(), 
    visionPIDSource, visionPIDOutput);
    visionPIDController.setAbsoluteTolerance(this.tolerance.get());
    visionPIDController.setSetpoint(this.SETPOINT);
    visionPIDController.setOutputRange(-1, 1);
    visionPIDController.setInputRange(-1, 1);
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
    interrupted();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    visionPIDController.disable();
  }
}
