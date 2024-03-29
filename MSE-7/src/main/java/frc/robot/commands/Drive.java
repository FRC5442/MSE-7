/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Drive extends CommandBase {

  public Drive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.swerveGroup);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Joystick driveStick = RobotContainer.xboxController1;

    double leftX = driveStick.getRawAxis(0);
    double leftY = driveStick.getRawAxis(1);

    double rightX = driveStick.getRawAxis(2);   //.getRawAxis(4) for xboxController     //.getRawAxis(2) for logitech

    Vector2d translation = new Vector2d(leftX * Math.pow(Math.abs(leftX), 1), leftY * Math.pow(Math.abs(leftY), 1));
    //RobotContainer.swerveGroup.moveSwerve(translation, rightX * Math.pow(Math.abs(rightX), 1));
    RobotContainer.swerveGroup.moveSwerve(translation, rightX * Math.pow(Math.abs(rightX), 1));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.swerveGroup.moveCrab(new Vector2d(0, 0), 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
