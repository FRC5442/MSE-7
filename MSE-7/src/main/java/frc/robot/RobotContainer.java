/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateModules;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.BackRightModule;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FrontLeftModule;
import frc.robot.subsystems.SwerveGroup;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static Drive drive;
  public static CalibrateModules calibrateModules;

  public static BackRightModule backRightModule;
  public static FrontLeftModule frontLeftModule;

  public static SwerveGroup swerveGroup;

  //Joysticks and JoystickButtons
  public static Joystick xboxController1;

  public static JoystickButton xboxController1A;

  //Speed Controllers
  public static CANSparkMax driveSpark1, driveSpark2, driveSpark3, driveSpark4;

  //Sensors
  public static AnalogPotentiometer frontLeftAbsEncoder, backRightAbsEncoder;
  public static AHRS navX;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Joysticks and JoystickButtons
    xboxController1 = new Joystick(0);

    xboxController1A = new JoystickButton(xboxController1, 1);

    //Speed Controllers
    driveSpark1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    driveSpark2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    driveSpark3 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    driveSpark4 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

    //Sensors
    frontLeftAbsEncoder = new AnalogPotentiometer(0, 360, 0);
    backRightAbsEncoder = new AnalogPotentiometer(1, 360, 0);

    navX = new AHRS(SerialPort.Port.kMXP);

    //Commands and subsytems
    frontLeftModule = new FrontLeftModule(driveSpark1, driveSpark2, frontLeftAbsEncoder, false);
    backRightModule = new BackRightModule(driveSpark3, driveSpark4, backRightAbsEncoder, false);
    swerveGroup = new SwerveGroup();

    drive = new Drive();
    calibrateModules = new CalibrateModules();

    swerveGroup.setDefaultCommand(drive);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    xboxController1A.whenPressed(calibrateModules);
  }

  public void readFiledZeroOffset() {
    swerveGroup.readFiledZeroOffset();
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return m_autoCommand;
  }
}
