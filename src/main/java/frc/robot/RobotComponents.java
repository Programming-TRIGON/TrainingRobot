package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotComponents {
    public static final VictorSP rightFrontTankDriveMotor = new VictorSP(RobotMap.FRONT_RIGHT_MOTOR);
    public static final VictorSP rightBackTankDriveMotor = new VictorSP(RobotMap.BACK_RIGHT_MOTOR);
    public static final VictorSP leftFrontTankDriveMotor = new VictorSP(RobotMap.FRONT_LEFT_MOTOR);
    public static final VictorSP leftBackTankDriveMotor = new VictorSP(RobotMap.BACK_LEFT_MOTOR);
    public static final SpeedControllerGroup TANK_DRIVE_RIGHT_MOTORS = new SpeedControllerGroup(
            RobotComponents.rightFrontTankDriveMotor, RobotComponents.rightBackTankDriveMotor);
    public static final SpeedControllerGroup TANK_DRIVE_LEFT_MOTORS = new SpeedControllerGroup(
            RobotComponents.leftFrontTankDriveMotor, RobotComponents.leftBackTankDriveMotor);
}
