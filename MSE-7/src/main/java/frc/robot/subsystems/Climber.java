/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  TalonFX winchMotor;
  WPI_VictorSPX climberMotor;

  public Climber() {
    climberMotor = RobotContainer.climberMotor;
    winchMotor = RobotContainer.winchMotor;
  }

  public void moveClimber(double speed){
    climberMotor.set(speed);
  }

  public void moveWinch(double speed) {
    winchMotor.set(TalonFXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
