/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
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
  PIDController visionPIDController;
  XboxController xbox;
  String networkTableChange;

  final Supplier<Double> kP = ConstantHandler.addConstantDouble("kP", 0.7);
  final Supplier<Double> kI = ConstantHandler.addConstantDouble("kI", 0.7);
  final Supplier<Double> kD = ConstantHandler.addConstantDouble("kD", 0.7);
  final Supplier<Double> tolerance = ConstantHandler.addConstantDouble("tolerance", 0.7);
  final int SETPOINT = 0;

  public TrackVisionTarget(VisionPIDSource.VisionTarget target, XboxController xbox) {
    this.target=target;
    this.xbox=xbox;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(this.target.toString());

    VisionPIDSource visionXPIDSource = new VisionPIDSource(this.target, VisionDirectionType.x); 
    VisionPIDSource visionYPIDSource = new VisionPIDSource(this.target, VisionDirectionType.y);
    XboxController xbox = this.xbox;
    PIDOutput visionXPIDOutput = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        if(visionXPIDSource.pidGet()!=9999){
          double y = visionYPIDSource.pidGet();
          Robot.driveTrain.arcadeDrive(output, y*0.4, Math.abs(y) <= 0.50);
        } else {
          double y = -xbox.getY(Hand.kLeft);
          Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y, Math.abs(y) <= 0.50);
        }
      }
    };

    visionPIDController = new PIDController(this.kP.get(), this.kI.get(), this.kD.get(), visionXPIDSource, visionXPIDOutput);
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
