/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TranslateDistance extends CommandBase {
  
  double speed, angle, distance;
  
  /**
   * Creates a new TranslateDistance.
   */
  public TranslateDistance(double speed, double angle, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveGroup);
    
    this.speed = speed;
    this.angle = angle;
    this.distance = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Translating...");
    RobotContainer.navX.resetDisplacement();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = speed * Math.cos(angle * (Math.PI / 180));
    double y = -speed * Math.sin(angle * (Math.PI / 180));
    RobotContainer.swerveGroup.moveSwerve(new Vector2d(x, y), 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.swerveGroup.moveSwerve(new Vector2d(0, 0), 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double xDisplacement = RobotContainer.navX.getDisplacementX();
    double yDisplacement = RobotContainer.navX.getDisplacementY();

    return Math.sqrt(Math.pow(xDisplacement, 2) + Math.pow(yDisplacement, 2)) >= distance;
  }
}
