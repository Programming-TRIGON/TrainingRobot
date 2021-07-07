/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class PathfinderConstants {
    //TODO get actuall values these are placeholders
    public static final int TicksPerRevolution = 360;
    public static final double WheelDiameter = 15.24;
    public static final double MaxVelocity = 4;
    /**Pathweaver(motion profiling) path names*/
    public static enum pathName{
        kPathBottomRight("PathBottomRight"),
        kPathBottomMiddle("PathBottomMiddle"),
        kPathBottomLeft("PathBottomLeft"),
        kPathTopRight("PathTopRight"),
        kPathTopLeft("PathTopLeft");

        public String key;
        pathName(String pathName) {
            this.key = pathName;
        }

    }
    // public static final String PathName = "TheFinderOfAllThings";
}