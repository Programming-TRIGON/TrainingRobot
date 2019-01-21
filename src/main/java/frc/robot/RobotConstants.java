package frc.robot;

public class RobotConstants {
    public class RobotDimentions {
        public static final double DRIVETRAIN_WHEEL_DIAMETER = 15.24;
        public static final double DRIVETRAIN_GEAR_RATIO = 12.75;
        public static final int DRIVETRAIN_ENCODER_CPR = 360;
        public static final double DRIVETRAIN_CYCLES_PER_METER = Math.PI * RobotDimentions.DRIVETRAIN_WHEEL_DIAMETER
                * RobotConstants.RobotDimentions.DRIVETRAIN_GEAR_RATIO
                * RobotConstants.RobotDimentions.DRIVETRAIN_ENCODER_CPR / 100;
        public static final double CYCLE_TO_PULSE = 0.25;
    }
}