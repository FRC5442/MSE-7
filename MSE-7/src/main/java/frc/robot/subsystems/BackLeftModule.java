/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class BackLeftModule extends SwerveModule {

    public BackLeftModule(CANSparkMax topGear, CANSparkMax bottomGear, AnalogPotentiometer absEncoder, boolean inverted) {
        super("BACK_LEFT", topGear, bottomGear, absEncoder, inverted, 0);
    }

    @Override
    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Front Left Encoder: ", this.currentAngle);
    }
}
