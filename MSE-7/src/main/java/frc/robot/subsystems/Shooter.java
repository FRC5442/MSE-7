/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  CANSparkMax shooterWheel;
  CANSparkMax shooterHood;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shooterWheel = RobotContainer.shooterWheel;
    shooterHood = RobotContainer.shooterHood;
  }

  public void shoot(double speed) {
    shooterWheel.set(speed);
  }

  public void moveHood(double speed) {
    shooterHood.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
