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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionPIDSource implements PIDSource {

    VisionTarget target;
    VisionDirectionType type;
    NetworkTableEntry visionEntry;
    double imgWidth = 2000; //important to know if the target on the middle of the image 

    public VisionPIDSource(VisionTarget target,VisionDirectionType type) {
        this.target = target;
        this.type = type;
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        // TODO: find which table we are using to upload vision target dircetions
        NetworkTable targeTable = inst.getTable("SmartDashboard");
        this.visionEntry = targeTable.getEntry(target.key);
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
        if(this.visionEntry==null)
            return 0;
        String targetLocation = this.visionEntry.getString("9999 9999");
        String[] locations = targetLocation.split(" ");
        double x = Double.parseDouble(locations[type.key]);
        // double y = Double.parseDouble(locations[1]);
        SmartDashboard.putNumber("target direction "+type.toString(), x);
        // SmartDashboard.putNumber("target direction y", y);
        if(targetLocation.equals("9999 9999"))
            return 0;
        
        return (-x/(this.imgWidth/2))+1; //give the pid controller value between -1 and 1
        //return -this.visionEntry.getDouble(0) - 1000;
    }


    public static enum VisionTarget {
        kHatch("RetroflectorDirection"),
        kCargo("CargoDirection"),
        kRetroflector("LineDirection"),
        kLine("HatchDirection");
        public String key;
        private VisionTarget(String key){
            this.key = key;
        }
    }
    public static enum VisionDirectionType{
        x(0),
        y(1);
        public int key;
        private VisionDirectionType(int key){
            this.key = key;
        }
    }
}