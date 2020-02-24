/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class FrontLeftModule extends SwerveModule {

    public FrontLeftModule(TalonFX topGear, TalonFX bottomGear, AnalogPotentiometer absEncoder, boolean inverted) {
        super(topGear, bottomGear, absEncoder, inverted, 0);
    }

    @Override
    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Front Left Encoder: ", this.currentAngle);
        SmartDashboard.putNumber("Front Left Encoder Zero Offset: ", this.currentAngle);
    }
}
