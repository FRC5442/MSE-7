// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class _Intake extends CommandBase {
  /** Creates a new _Intake. */
  double speed;
  double timeout;
  public _Intake(double speed, double timeout) {
    // Use addRequirements() here to declare subsystem dependencies.
    /*addRequirements(RobotContainer.intake);
    this.speed = speed;
    this.timeout = timeout;*/
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*setTimeout(timeout);*/
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.intake.moveIntake(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.intake.moveIntake(speed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return isTimedOut;
    return false;
  }
}
