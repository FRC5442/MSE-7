/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  CANSparkMax shooterWheel1, shooterWheel2;
  WPI_VictorSPX shooterHood;

  CANEncoder wheel1Encoder, wheel2Encoder;

  CANPIDController wheel1PIDController, wheel2PIDController;
  
  public Shooter() {
    shooterWheel1 = RobotContainer.shooterWheel1;
    wheel1Encoder = shooterWheel1.getEncoder();
    wheel1PIDController = shooterWheel1.getPIDController();

    wheel1PIDController.setP(6e-5);
    wheel1PIDController.setI(0);
    wheel1PIDController.setD(0);
    wheel1PIDController.setIZone(0);
    wheel1PIDController.setFF(0.000015);
    wheel1PIDController.setOutputRange(-1, 1);

    shooterWheel2 = RobotContainer.shooterWheel2;
    wheel2Encoder = shooterWheel2.getEncoder();
    wheel2PIDController = shooterWheel2.getPIDController();

    wheel2PIDController.setP(6e-5);
    wheel2PIDController.setI(0);
    wheel2PIDController.setD(0);
    wheel2PIDController.setIZone(0);
    wheel2PIDController.setFF(0.000015);
    wheel2PIDController.setOutputRange(-1, 1);

    shooterHood = RobotContainer.shooterHood;
  }

  public void shoot(double rpm) {
    wheel1PIDController.setReference(-rpm, ControlType.kVelocity);
    //wheel2PIDController.setReference(rpm, ControlType.kVelocity);
    //add the other wheel once we know the PID works
  }

  public void moveHood(double speed) {
    shooterHood.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
