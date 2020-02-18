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
import frc.robot.SharedMethods;

public class SwerveGroup extends SubsystemBase {
  
  SwerveModule frontLeftModule;
  SwerveModule backRightModule;

  double convertedGyroAngle = 0;

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
    double gyroRadians = getConvertedGyroAngle() * (Math.PI / 180); //in radians
    SmartDashboard.putNumber("Gyro Angle: ", getConvertedGyroAngle());

    double FWD = -translation.y;
    double STR = -translation.x;
    double RCW = -rotation;

    double temp = (FWD * Math.cos(gyroRadians)) + (STR * Math.sin(gyroRadians));
    STR = (-FWD * Math.sin(gyroRadians)) + (STR * Math.cos(gyroRadians));
    FWD = temp;

    if (Math.abs(translation.magnitude()) <= Constants.JOYSTICK_DEAD_ZONE) {
      FWD = 0;
      STR = 0;
    }
    if (Math.abs(rotation) <= Constants.JOYSTICK_DEAD_ZONE) RCW = 0;

    double A = STR - RCW * (Constants.ROBOT_LENGTH / Constants.ROBOT_RADIUS);
    double B = STR + RCW * (Constants.ROBOT_LENGTH / Constants.ROBOT_RADIUS);
    double C = FWD - RCW * (Constants.ROBOT_WIDTH / Constants.ROBOT_RADIUS);
    double D = FWD + RCW * (Constants.ROBOT_WIDTH / Constants.ROBOT_RADIUS);

    //B and C
    double frontRightSpeed = getMovementAttributes(B, C)[0]; 
    double frontRightAngle = getMovementAttributes(B, C)[1];
    double maxSpeed = frontRightSpeed;

    //B and D
    double frontLeftSpeed = getMovementAttributes(B, D)[0]; 
    double frontLeftAngle = getMovementAttributes(B, D)[1];
    if (frontLeftSpeed > maxSpeed) frontLeftSpeed = maxSpeed;

    //A and D
    double backLeftSpeed = getMovementAttributes(A, D)[0]; 
    double backLeftAngle = getMovementAttributes(A, D)[1];
    if (backLeftSpeed > maxSpeed) backLeftSpeed = maxSpeed;

    //A and C
    double backRightSpeed = getMovementAttributes(A, C)[0];
    double backRightAngle = getMovementAttributes(A, C)[1];
    if (backRightSpeed > maxSpeed) backRightSpeed = maxSpeed;

    if (Math.abs(translation.magnitude()) > Constants.JOYSTICK_DEAD_ZONE || Math.abs(rotation) > Constants.JOYSTICK_DEAD_ZONE) {
      frontLeftModule.move(frontLeftSpeed, frontLeftAngle);
      backRightModule.move(backRightSpeed, backRightAngle);
    }
    else {
      frontLeftModule.stop();
      backRightModule.stop();
    }
  }

  public void calibrate() {
    frontLeftModule.calibrate();
    backRightModule.calibrate();
  }

  public double[] getMovementAttributes(double c1, double c2) {
    double speed = Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
    double angle = Math.atan2(c1, c2) * (180 / Math.PI) + 180;

    return new double[] { speed, angle };
  }

  public void switchDriveState(Constants.DRIVE_STATE driveState) {
    frontLeftModule.switchTranslationMod(driveState.getValue());
    backRightModule.switchTranslationMod(driveState.getValue());
  }

  public double getConvertedGyroAngle() {
    double rawGyroAngle = (RobotContainer.navX.getAngle() + Constants.YAW_OFFSET); //in degrees
    double convertedRawGyroAngle = ((360 - rawGyroAngle + 90) % 360);
    if (convertedRawGyroAngle < 0) {
      return SharedMethods.roundTo(360 + convertedRawGyroAngle, 0);
    }
    else {
      return SharedMethods.roundTo(convertedRawGyroAngle, 0);
    }
  }

  @Override
  public void periodic() {
  }
}
