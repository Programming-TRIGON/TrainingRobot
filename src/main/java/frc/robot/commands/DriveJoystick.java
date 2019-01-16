/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveJoystick extends Command {
  XboxController xbox;
  int invert;
  public DriveJoystick(XboxController xbox, int invert) {
    requires(Robot.driveTrain);
    this.xbox = xbox;
    this.invert = invert;
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.driveTrain.arcadeDrive(invert*this.xbox.getY(), this.xbox.getX());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.arcadeDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
