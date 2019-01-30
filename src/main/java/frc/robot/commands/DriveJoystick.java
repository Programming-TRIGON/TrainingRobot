/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveJoystick extends Command {
  XboxController xbox;
  public DriveJoystick(XboxController xbox) {
    requires(Robot.drivetrain);
    this.xbox = xbox;
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDrive(
      Robot.drivetrain.isInverted() ? -1 * this.xbox.getY(Hand.kLeft) :1 * this.xbox.getY(Hand.kLeft), this.xbox.getX(Hand.kLeft));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.arcadeDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
