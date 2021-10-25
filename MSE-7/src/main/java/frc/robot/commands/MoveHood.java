/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveHood extends CommandBase {
  /**
   * Creates a new MoveHood.
   */

  double distance;
  double speed;

  public MoveHood(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.shooter);
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //double speed = (RobotContainer.hoodEncoder.getDistance() - distance) / 100;

    if (RobotContainer.hoodEncoder.getDistance() < 40 && speed > 0){
      RobotContainer.shooter.moveHood(0);
    }else if (RobotContainer.hoodEncoder.getDistance() > 340 && speed < 0){
      RobotContainer.shooter.moveHood(0);
    } else {
      RobotContainer.shooter.moveHood(speed);
    }
    
    int hoodEncoderValue = (int) Math.round(RobotContainer.hoodEncoder.getDistance() / 20);
    
    SmartDashboard.putNumber("THE REAL HOOD ENCODER", hoodEncoderValue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.moveHood(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return Math.abs(RobotContainer.hoodEncoder.getDistance() - distance) <= 5;
    return false;
  }
}
