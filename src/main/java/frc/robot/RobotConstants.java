package frc.robot;

public class RobotConstants {
    public class RobotDimensions {
        public static final double DRIVETRAIN_WHEEL_DIAMETER = 15.24;
        public static final double DRIVETRAIN_GEAR_RATIO = 12.75;
        public static final int DRIVETRAIN_ENCODER_CPR = 360;
        public static final double DRIVETRAIN_CYCLES_PER_METER = Math.PI * RobotDimensions.DRIVETRAIN_WHEEL_DIAMETER
                * RobotConstants.RobotDimensions.DRIVETRAIN_GEAR_RATIO
                * RobotConstants.RobotDimensions.DRIVETRAIN_ENCODER_CPR / 100;
        public static final double CYCLE_TO_PULSE = 0.25;
    }
}