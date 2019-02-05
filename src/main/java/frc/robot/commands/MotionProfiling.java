package frc.robot.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PathfinderConstants;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class MotionProfiling extends Command {
  EncoderFollower encoderFollowerLeft, encoderFollowerRight;
  Notifier encoderNotifier;
  String pathName;

  public MotionProfiling(String pathName) {
    requires(Robot.driveTrain);
    this.pathName = pathName;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Trajectory left_trajectory = PathfinderFRC.getTrajectory(pathName + ".right");
    Trajectory right_trajectory = PathfinderFRC.getTrajectory(pathName + ".left");

    encoderFollowerLeft = new EncoderFollower(left_trajectory);
    encoderFollowerRight = new EncoderFollower(right_trajectory);

    encoderFollowerLeft.configureEncoder(Robot.driveTrain.encLeftTicks(), PathfinderConstants.TicksPerRevolution,
        PathfinderConstants.WheelDiameter);
    // You must tune the PID values on the following line!
    encoderFollowerLeft.configurePIDVA(1.0, 0.0, 0.0, 1 / PathfinderConstants.MaxVelocity, 0);

    encoderFollowerRight.configureEncoder(Robot.driveTrain.encRightTicks(), PathfinderConstants.TicksPerRevolution,
        PathfinderConstants.WheelDiameter);
    // You must tune the PID values on the following line!
    encoderFollowerRight.configurePIDVA(1.0, 0.0, 0.0, 1 / PathfinderConstants.MaxVelocity, 0);

    encoderNotifier = new Notifier(this::followPath);
    encoderNotifier.startPeriodic(left_trajectory.get(0).dt);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    encoderNotifier.stop();
    Robot.driveTrain.arcadeDrive(0, 0);
  }



  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private void followPath() {
    if (encoderFollowerLeft.isFinished() || encoderFollowerRight.isFinished()) {
      encoderNotifier.stop();
    } else {
      double left_speed = encoderFollowerLeft.calculate(Robot.driveTrain.encLeftTicks());
      double right_speed = encoderFollowerRight.calculate(Robot.driveTrain.encRightTicks());
      double heading = Robot.driveTrain.gyroAngle();
      double desired_heading = Pathfinder.r2d(encoderFollowerLeft.getHeading());
      double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
      double turn = 0.8 * (-1.0 / 80.0) * heading_difference;
      Robot.driveTrain.tankDrive((left_speed + turn), (right_speed - turn));
    }
  }
}
