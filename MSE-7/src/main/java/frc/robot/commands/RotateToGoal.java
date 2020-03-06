/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.commands.Drive;

public class RotateToGoal extends CommandBase {
  /**
   * Creates a new RotateToGoal.
   */

  double speed = 0;
  double convertedSpeed = 0;
  double yawOffset = 0;
  double rightX = 0.25;
  boolean tapeDetected = false;
  

  public RotateToGoal(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveGroup, RobotContainer.piVisionTable);
    
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Rotating to tape...");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tapeDetected = RobotContainer.piVisionTable.isTapeDetected();

    yawOffset = RobotContainer.piVisionTable.getYawOffset();

    if (tapeDetected) {

      if (Math.abs(yawOffset) > 3) {
        convertedSpeed = speed * MathUtil.clamp((yawOffset / 12) * Math.abs(yawOffset / 15), -1, 1);

        if (convertedSpeed < speed && yawOffset < 0) {
          convertedSpeed = -speed;
        } else if (convertedSpeed < speed && yawOffset > 0) {
          convertedSpeed = speed;
        }
        RobotContainer.swerveGroup.moveSwerve(new Vector2d(0, 0), convertedSpeed);
        System.out.println("Rotating...(Tape Detected): " + yawOffset + " " + convertedSpeed);
      }

    } else {
      Robot.scheduleCommand(new ScanForGoal(0.25));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.swerveGroup.moveSwerve(new Vector2d(0,0), 0);
    System.out.println("RotateToGoal Stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (tapeDetected) {
      if (Math.abs(yawOffset) <= 4) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
