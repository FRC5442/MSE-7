/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class PiVisionTable extends SubsystemBase {

  NetworkTable visionTable;

  /**
   * Creates a new VisionTable.
   */
  public PiVisionTable() {
    visionTable = RobotContainer.visionTable;
  }

  public double getYawOffset() {
    double yawOffset = visionTable.getEntry("Yaw").getDouble(0);
    return yawOffset;
  }

  public double getDistanceM() {
    double distance = visionTable.getEntry("Distance(m)").getDouble(0);
    return distance;
  }

  public double getDistanceIN() {
    double distance = visionTable.getEntry("Distance(in)").getDouble(0);
    return distance;
  }

  public double getDistanceCM() {
    double distance = visionTable.getEntry("Distance(cm)").getDouble(0);
    return distance;
  }

  public boolean isLidarConnected() {
    boolean lidarConnected = visionTable.getEntry("lidarConnected").getBoolean(false);
    return lidarConnected;
  }

  public boolean isTapeDetected() {
    boolean tapeDetected = visionTable.getEntry("tapeDetected").getBoolean(false);
    return tapeDetected;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    boolean tapeDetected = RobotContainer.piVisionTable.isTapeDetected();
    boolean lidarConnected = RobotContainer.piVisionTable.isLidarConnected();
    double yawOffset = RobotContainer.piVisionTable.getYawOffset();
    double distanceM = RobotContainer.piVisionTable.getDistanceM();
    double distanceIN = RobotContainer.piVisionTable.getDistanceIN();
    double distanceCM = RobotContainer.piVisionTable.getDistanceCM();

    SmartDashboard.putBoolean("Tape Detected: ", tapeDetected);
    SmartDashboard.putBoolean("Lidar Connected: ", lidarConnected);
    SmartDashboard.putNumber("Yaw Offset: ", yawOffset);
    SmartDashboard.putNumber("Distance(m): ", distanceM);
    SmartDashboard.putNumber("Distance(in)", distanceIN);
    SmartDashboard.putNumber("Distance(cm)", distanceCM); 
  }
}
