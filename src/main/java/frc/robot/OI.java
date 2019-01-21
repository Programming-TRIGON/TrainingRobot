/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ToggleInverted;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public XboxController xbox;   
  public JoystickButton buttonY, buttonA;
  public OI(){
    this.xbox = new XboxController(0);
    this.buttonY = new JoystickButton(xbox, 4);
    this.buttonA = new JoystickButton(xbox, 1);
    buttonY.whenPressed(new ToggleInverted());
    buttonA.whenPressed(new DriveDistance(3));
  }
}