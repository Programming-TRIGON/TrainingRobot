/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class TwoEncoderPIDSource implements PIDSource {

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) { 
  }

  @Override
  public PIDSourceType getPIDSourceType() {
   return PIDSourceType.kDisplacement;
  }

  @Override
  public double pidGet() {
    return Robot.drivetrain.getDistance();
  } 

}