/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  public static Joystick xboxController1;
  public static JoystickButton xboxController1A;
  public static JoystickButton xboxController1B;

  public static CANSparkMax intakeMotor;
  public static CANSparkMax shooterWheel;
  public static CANSparkMax shooterHood;

  public static TalonFX driveMotor1;

  public static Intake intake;
  public static IntakeCommand intakeCommand;

  public static Shooter shooter;
  public static ShootCommand shootCommand;

  
  
  public RobotContainer() {
    xboxController1 = new Joystick(0);
    xboxController1A = new JoystickButton(xboxController1, 1);
    xboxController1B = new JoystickButton(xboxController1, 2);

    intakeMotor = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterWheel = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterHood = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);

    intake = new Intake();
    intakeCommand = new IntakeCommand(0.5);

    shooter = new Shooter();
    shootCommand = new ShootCommand(0.5);

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
    xboxController1A.whileHeld(intakeCommand);
    xboxController1B.whileHeld(shootCommand);
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
