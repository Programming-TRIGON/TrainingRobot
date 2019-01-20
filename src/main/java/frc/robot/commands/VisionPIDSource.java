/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class VisionPIDSource implements PIDSource {

    VisionTarget target;
    NetworkTableEntry visionEntry;

    public VisionPIDSource(VisionTarget target) {
        this.target = target;
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        // TODO: find which table we are using to upload vision target dircetions
        NetworkTable targeTable = inst.getTable("ImageProcessing");
        String targetKey;
        switch (target) {
        case kRetroflector:
            targetKey = "retroflectorDirection";
            break;
        case kCargo:
            targetKey = "cargoDirection";
            break;
        case kLine:
            targetKey = "lineDirection";
            break;
        default:
            targetKey = "hatchDirection";
            break;
        }
        this.visionEntry = targeTable.getEntry(targetKey);
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        double imgWidth = 2000; //important to know if the target on the middle of the image 
        if(this.visionEntry==null)
            return 0;
        return (this.visionEntry.getDouble(0)/(imgWidth/2))-1; //give the pid controller value between -1 and 1
    }


    public static enum VisionTarget {
        kHatch, kCargo, kRetroflector, kLine;
    }
}