/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double ROBOT_WIDTH = 19.1; //in inches
    public static final double ROBOT_LENGTH = 19.1; //in inches
    public static final double ROBOT_RADIUS = Math.sqrt(Math.pow(ROBOT_WIDTH / 2, 2) + Math.pow(ROBOT_LENGTH / 2, 2));
    
    public static final double JOYSTICK_DEAD_ZONE = 0.03; //joystick values 0-1

    public static final double ENCODER_OFFSET = 15.0; //in degrees
}
