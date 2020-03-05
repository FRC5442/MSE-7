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
public class BackLeftModule extends SwerveModule {

    public BackLeftModule(TalonFX topGear, TalonFX bottomGear, AnalogPotentiometer absEncoder) {
        super(topGear, bottomGear, absEncoder, true, 70);
    }

    @Override
    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Back Left Encoder: ", this.currentAngle);
        SmartDashboard.putNumber("Back Left Encoder Zero Offset: ", this.zeroOffset);
    }
}
