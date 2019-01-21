/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.VisionPIDSource.VisionDirectionType;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;



public class TrackVisionTarget extends Command {
  
  VisionPIDSource.VisionTarget target;
  PIDController visionXPIDController, visionYPIDController;

  final Supplier<Double> kP = ConstantHandler.addConstantDouble("kP", 0.7);
  final Supplier<Double> kI = ConstantHandler.addConstantDouble("kI", 0.7);
  final Supplier<Double> kD = ConstantHandler.addConstantDouble("kD", 0.7);
  final Supplier<Double> tolerance = ConstantHandler.addConstantDouble("tolerance", 0.7);

  final int SETPOINT = 0;
  XboxController xbox;

  public TrackVisionTarget(VisionPIDSource.VisionTarget target, XboxController xbox) {
    this.target=target;
    this.xbox=xbox;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    VisionPIDSource visionXPIDSource = new VisionPIDSource(this.target, VisionDirectionType.x); 
    //JoystickPIDSource joystickPIDSource = new JoystickPIDSource();
    VisionPIDSource visionYPIDSource = new VisionPIDSource(this.target, VisionDirectionType.y);
    XboxController xbox = this.xbox;
    PIDOutput visionXPIDOutput = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        if(visionXPIDSource.pidGet()!=9999){
          Robot.driveTrain.arcadeDrive(output, -xbox.getY(Hand.kLeft), true);
        } else {
          double y = -xbox.getY(Hand.kLeft);
          Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y, Math.abs(y) <= 0.50);
        }
      }
    };
    /*PIDOutput visionYPIDOutput = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        if(visionXPIDSource.pidGet()!=9999){
          Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), -output, true);
        } else {
          double y = -xbox.getY(Hand.kLeft);
          Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y, Math.abs(y) <= 0.50);
        }
      }
    };*/

    visionXPIDController = new PIDController(this.kP.get(), this.kI.get(), this.kD.get(), 
    visionXPIDSource, visionXPIDOutput);
    visionXPIDController.setAbsoluteTolerance(this.tolerance.get());
    visionXPIDController.setSetpoint(this.SETPOINT);
    visionXPIDController.setOutputRange(-1, 1);
    visionXPIDController.setInputRange(-1, 1);
    visionXPIDController.enable();
    
    /*visionYPIDController = new PIDController(this.kP.get(), this.kI.get(), this.kD.get(), 
    visionXPIDSource, visionYPIDOutput);
    visionYPIDController.setAbsoluteTolerance(this.tolerance.get());
    visionYPIDController.setSetpoint(this.SETPOINT);
    visionYPIDController.setOutputRange(-1, 1);
    visionYPIDController.setInputRange(-1, 1);
    visionYPIDController.enable();*/
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
    visionXPIDController.disable();
  }
}
