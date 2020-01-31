/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class SwerveGroup extends SubsystemBase {
  
  SwerveModule frontLeftModule;
  SwerveModule backRightModule;

  public SwerveGroup() {
    frontLeftModule = RobotContainer.frontLeftModule;
    backRightModule = RobotContainer.backRightModule;
  }

  public void moveCrab(Vector2d translation, double rotation) {
    double joystickAngle = (Math.atan2(translation.y, -translation.x) * (180/Math.PI)) + 180;
    if (Math.abs(translation.magnitude()) > Constants.JOYSTICK_DEAD_ZONE) {
      frontLeftModule.move(translation.magnitude(), joystickAngle);
      backRightModule.move(translation.magnitude(), joystickAngle);
    }
    else if (Math.abs(rotation) > Constants.JOYSTICK_DEAD_ZONE) {
      frontLeftModule.move(rotation, 225);
      backRightModule.move(rotation, 225);
    }
    else {
      frontLeftModule.stop();
      backRightModule.stop();
    }
  }

  public void moveSwerve(Vector2d translation, double rotation) {
    double gyroAngle = (RobotContainer.navX.getAngle()) * (Math.PI / 180); //in radians
    SmartDashboard.putNumber("Gyro Angle: ", gyroAngle);

    double FWD = -translation.y;
    double STR = -translation.x;
    double RCW = -rotation;

    /*
    double temp = (FWD * Math.cos(gyroAngle)) + (STR * Math.sin(gyroAngle));
    STR = (-FWD * Math.sin(gyroAngle)) + (STR * Math.cos(gyroAngle));
    FWD = temp;
    */

    double A = STR - RCW * (Constants.ROBOT_LENGTH / Constants.ROBOT_RADIUS);
    double B = STR + RCW * (Constants.ROBOT_LENGTH / Constants.ROBOT_RADIUS);
    double C = FWD - RCW * (Constants.ROBOT_WIDTH / Constants.ROBOT_RADIUS);
    double D = FWD + RCW * (Constants.ROBOT_WIDTH / Constants.ROBOT_RADIUS);

    double frontRightSpeed = Math.sqrt(Math.pow(B, 2) + Math.pow(C, 2)); 
    double frontRightAngle = Math.atan2(B, C) * (180 / Math.PI) + 180;

    double frontLeftSpeed = Math.sqrt(Math.pow(B, 2) + Math.pow(D, 2)); 
    double frontLeftAngle = Math.atan2(B, D) * (180 / Math.PI) + 180;

    double backLeftSpeed = Math.sqrt(Math.pow(A, 2) + Math.pow(D, 2)); 
    double backLeftAngle = Math.atan2(A, D) * (180 / Math.PI) + 180;

    double backRightSpeed = Math.sqrt(Math.pow(A, 2) + Math.pow(C, 2)); 
    double backRightAngle = Math.atan2(A, C) * (180 / Math.PI) + 180;

    if (Math.abs(translation.magnitude()) > Constants.JOYSTICK_DEAD_ZONE || Math.abs(rotation) > Constants.JOYSTICK_DEAD_ZONE) {
      frontLeftModule.move(frontLeftSpeed, frontLeftAngle);
      SmartDashboard.putNumber("Front Left Desired Angle: ", frontLeftAngle);

      backRightModule.move(backRightSpeed, backRightAngle);
      SmartDashboard.putNumber("Back Right Desired Angle: ", backRightAngle);
    }
    else {
      frontLeftModule.stop();
      backRightModule.stop();
    }
  }

  public void readFiledZeroOffset() {
    frontLeftModule.readFiledZeroOffset("FRONT_LEFT");
    backRightModule.readFiledZeroOffset("BACK_RIGHT");
  }

  public void calibrate() {
    frontLeftModule.calibrate("FRONT_LEFT");
    backRightModule.calibrate("BACK_RIGHT");
  }

  @Override
  public void periodic() {
  }
}
