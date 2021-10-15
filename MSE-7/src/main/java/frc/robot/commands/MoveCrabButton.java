// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/*
    This command enables the crab rotation when a button is pressed, either with a negative value or a positive value
*/

public class MoveCrabButton extends CommandBase {
  /** Creates a new MoveCrabButton. */
double crabDirection;


  public MoveCrabButton(double crabDirection) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.crabDirection = crabDirection;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //RobotContainer.drive.end(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    Drive.rightX = crabDirection;
    //Drive.leftX = 0;
    //Drive.leftY = 0;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Drive.rightX = 0;
    //RobotContainer.drive.schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
