// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
//import edu.wpi.first.wpilibj.command.TimedCommand;


public class _Shoot extends CommandBase {

  //double rpm;
  //double time;
  //double timeout;
  /** Creates a new AutoShoot. */
  public _Shoot(double rpm, double timeout) {
    // Use addRequirements() here to declare subsystem dependencies.
    //this.rpm = rpm;
    //this.timeout = timeout;
    //addRequirements(RobotContainer.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //setTimeout(timeout);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.shooter.shoot(rpm);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.shooter.shoot(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return isTimedOut(timeout);
    return false;
  }
}
