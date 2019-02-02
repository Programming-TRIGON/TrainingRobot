/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TrackVisionTarget;
import frc.robot.commands.VisionPIDSource;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public XboxController xbox;
  Button AButton, BButton;
  public OI(){
    // TODO: change to robotMap port
    this.xbox = new XboxController(0);
    this.AButton = new JoystickButton(this.xbox, 1);
    this.BButton = new JoystickButton(this.xbox, 2);
    AButton.whileHeld(new TrackVisionTarget(VisionPIDSource.VisionTarget.kHatch, this.xbox));
    BButton.whileHeld(new TrackVisionTarget(VisionPIDSource.VisionTarget.kCargo, this.xbox));
  }
  public double getX(){
    return xbox.getX(Hand.kLeft);
  }
}