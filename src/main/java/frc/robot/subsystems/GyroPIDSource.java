/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class GyroPIDSource implements PIDSource {

    public void setPIDSourceType(PIDSourceType pidSource){
    }

    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kRate;
       }
     
    public double pidGet(){
        return Robot.drivetrain.getAngle();
    }
}
