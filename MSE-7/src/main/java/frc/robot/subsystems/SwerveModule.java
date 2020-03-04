/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SharedMethods;

public class SwerveModule extends SubsystemBase {

  TalonFX topGear, bottomGear;
  AnalogPotentiometer absEncoder;
  CANEncoder topEncoder, bottomEncoder;
  double currentAngle = 0.0;
  double rawAngle = 0.0;
  double startTime = 0.0;
  double elapsedTime = 500;
  double zeroOffset = 0;

  double TRANSLATE_MOD = 0.4;
  double ROTATE_MOD = 0.3;
  double ERROR_BOUND = 1;

  double topGearSpeed = 0;
  double bottomGearSpeed = 0;

  String moduleID = "";

  public SwerveModule(TalonFX topGear, TalonFX bottomGear, AnalogPotentiometer absEncoder, boolean invertedTranslate, double zeroOffset) {

    this.moduleID = moduleID.toUpperCase();

    this.zeroOffset = zeroOffset;

    this.topGear = topGear;
    this.bottomGear = bottomGear;
    this.absEncoder = absEncoder;

    if (invertedTranslate) {
      TRANSLATE_MOD *= -1;
    }
  }
  
  public void move(double speed, double angle) {
    topGearSpeed = 0;
    bottomGearSpeed = 0;

    topGearSpeed += (-speed * TRANSLATE_MOD);
    bottomGearSpeed += (speed * TRANSLATE_MOD);

    if (Math.abs(currentAngle - angle) >= ERROR_BOUND && Math.abs(currentAngle - angle) <= 360 - ERROR_BOUND) {
      ROTATE_MOD = 0.3 - (((Math.abs(topGearSpeed) + Math.abs(bottomGearSpeed)) / 2) * 0.15);
      turnToAngle(angle);
    }
  }

  public void turnToAngle(double desiredAngle) {
    //get the error
    double error = 0;

    if (desiredAngle > currentAngle) {
      error = desiredAngle - currentAngle;
      if (error < 180) {
        //move D by increasing C
        topGearSpeed += Math.abs(error) / 150 * ROTATE_MOD;
        bottomGearSpeed += Math.abs(error) / 150 * ROTATE_MOD;
      }
      else if (error >= 180) {
        //move towards D by decreasing C
        topGearSpeed += -Math.abs(360 - error) / 150 * ROTATE_MOD;
        bottomGearSpeed += -Math.abs(360 - error) / 150 * ROTATE_MOD;
      }
    }
    else if (desiredAngle < currentAngle) {
      error = currentAngle - desiredAngle;
      if (error < 180) {
        //move towards D decreasing C
        topGearSpeed += -Math.abs(error) / 150 * ROTATE_MOD;
        bottomGearSpeed += -Math.abs(error) / 150 * ROTATE_MOD;
      }
      else if (error >= 180) {
        //move towards D by increasing C
        topGearSpeed += Math.abs(360 - error) / 150 * ROTATE_MOD;
        bottomGearSpeed += Math.abs(360 - error) / 150 * ROTATE_MOD;
      }
    }
  }

  public void calibrate() {
    zeroOffset = rawAngle;
  }

  public void switchTranslationMod(double value) {
    TRANSLATE_MOD = value;
  }

  public void stop() {
    topGearSpeed = 0;
    bottomGearSpeed = 0;
  }

  @Override
  public void periodic() {
    updateGearSpeeds();
    updateCurrentAngle();
    updateSmartDashboard();
  }

  public void updateCurrentAngle() {
    elapsedTime = (System.nanoTime() / 1000000) - startTime;
    if (elapsedTime >= 10) {
      startTime = System.nanoTime() / 1000000;

      //convert absolute encoder voltage to degrees and post to smartdashboard for testing
      rawAngle = (SharedMethods.roundTo(((absEncoder.get() - Constants.ENCODER_OFFSET) / 335) * 360, 0));

      //do if statement with 360 minus for negative numbers
      double newAngle = rawAngle - zeroOffset;

      if (newAngle < 0) {
        currentAngle = 360 + newAngle; //new angle is always negative so current angle = 360 - (a negative number)
      }
      else {
        currentAngle = newAngle;
      }
    }
  }

  public void updateSmartDashboard() {
    //override in module specific class
  }

  public void updateGearSpeeds() {
    topGear.set(TalonFXControlMode.PercentOutput, topGearSpeed);
    bottomGear.set(TalonFXControlMode.PercentOutput, bottomGearSpeed);
  }
}
