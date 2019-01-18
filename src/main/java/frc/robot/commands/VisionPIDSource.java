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
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class VisionPIDSource implements PIDSource {
    
    VisionTarget target;

    public VisionPIDSource(VisionTarget target){
        this.target=target;
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
		return 0;
	}

    public double runListenerNetworkTable(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
         // TODO: find which table we are using to upload vision target dircetion
        NetworkTable TargetTable = inst.getTable("VisionTable");
        String TargetKey;
        switch (VisionTarget){
            case VisionTarget.kRetroflector: TargetKey = "hatchDirection"; break;
            case VisionTarget.kCargo: TargetKey = "hatchDirection"; break;
            case VisionTarget.kLine: TargetKey = "hatchDirection"; break;
            default: VisionTarget.kHatch TargetKey = "hatchDirection"; break;
        }
        NetworkTableEntry TargetEntry = TargetTable.getEntry(TargetKey);
        return
    }

    public static enum VisionTarget {
        kHatch, kCargo, kRetroflector, kLine;
      }
}
