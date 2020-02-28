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
public class BackRightModule extends SwerveModule {
    
    public BackRightModule(TalonFX topGear, TalonFX bottomGear, AnalogPotentiometer absEncoder, boolean inverted) {
        super(topGear, bottomGear, absEncoder, inverted, 110);
    }

    @Override
    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Back Right Encoder: ", this.currentAngle);
        SmartDashboard.putNumber("Back Right Encoder Zero Offset: ", this.zeroOffset);
    }
}
