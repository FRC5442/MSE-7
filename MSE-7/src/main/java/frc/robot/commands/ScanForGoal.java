/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.hal.sim.mockdata.PCMDataJNI;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ScanForGoal extends CommandBase {
  /**
   * Creates a new ScanForGoal.
   */

  double speed = 0;
  boolean tapeDetected = false;

  public ScanForGoal(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveGroup, RobotContainer.piVisionTable);

    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Starting ScanForGoal...");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentAngle = RobotContainer.swerveGroup.getConvertedGyroAngle();

    tapeDetected = RobotContainer.piVisionTable.isTapeDetected();

    if (!tapeDetected) {
      if (currentAngle >= 180 && currentAngle <= 270) {
        System.out.println("Rotating...(180-270)");
        RobotContainer.swerveGroup.moveSwerve(new Vector2d(0,0), speed);
      } else if (currentAngle > 270 && currentAngle <= 360) {
        System.out.println("Rotating...(270-360)");
        RobotContainer.swerveGroup.moveSwerve(new Vector2d(0,0), -speed);
      } else {
        System.out.println("Rotating...(else)");
        RobotContainer.swerveGroup.moveSwerve(new Vector2d(0,0), speed);
      }
        
      System.out.println("Scanning...: " + currentAngle);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Ending ScanForGoal...");
    Robot.scheduleCommand(new RotateToGoal(0.15));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (tapeDetected) {
      return true;
    } else {
      return false;
    }
  }
}
