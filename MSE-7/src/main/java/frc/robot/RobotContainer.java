/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateGyro;
import frc.robot.commands.CarouselCommand;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.HighGear;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LowGear;
import frc.robot.commands.MotorTester;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.BackLeftModule;
import frc.robot.subsystems.BackRightModule;
import frc.robot.subsystems.Carousel;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FrontLeftModule;
import frc.robot.subsystems.FrontRightModule;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveGroup;
import frc.robot.subsystems.SwerveModule;

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
  public static JoystickButton xboxController1X;
  public static JoystickButton xboxController1Y;
  public static JoystickButton xboxController1LBumper, xboxController1RBumper;

  public static CANSparkMax intakeMotor;
  public static CANSparkMax shooterWheel1;
  public static CANSparkMax shooterWheel2;
  public static CANSparkMax shooterHood;
  public static CANSparkMax carouselMotor;
  public static CANSparkMax climberMotor;
  
  public static TalonFX driveMotor1, driveMotor2; //front right module
  public static TalonFX driveMotor3, driveMotor4; //front left
  public static TalonFX driveMotor5, driveMotor6; //back left
  public static TalonFX driveMotor7, driveMotor8; //back right

  public static TalonFX testMotor;

  public static Encoder hoodEncoder;
  public static AnalogPotentiometer frontRightAbsEncoder;
  public static AnalogPotentiometer frontLeftAbsEncoder;
  public static AnalogPotentiometer backLeftAbsEncoder;
  public static AnalogPotentiometer backRightAbsEncoder;

  public static AHRS navX;

  public static SwerveGroup swerveGroup;
  public static SwerveModule frontRightModule, frontLeftModule, backLeftModule, backRightModule;
  public static Drive drive;
  public static HighGear highGear;
  public static LowGear lowGear;

  public static Intake intake;
  public static IntakeCommand intakeCommand;

  public static Shooter shooter;
  public static ShootCommand shootCommand;

  public static Carousel carousel;
  public static CarouselCommand carouselCommand;

  public static Climber climber;
  public static ClimberCommand climberCommand;

  public static MotorTester motorTester;
  public static CalibrateGyro calibrateGyro;

  
  public RobotContainer() {
    //xbox controller
    xboxController1 = new Joystick(0);
    xboxController1A = new JoystickButton(xboxController1, 1);
    xboxController1B = new JoystickButton(xboxController1, 2);
    xboxController1X = new JoystickButton(xboxController1, 3);
    xboxController1Y = new JoystickButton(xboxController1, 4);
    xboxController1LBumper = new JoystickButton(xboxController1, 5);
    xboxController1RBumper = new JoystickButton(xboxController1, 6);

    //speed controllers
    intakeMotor = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterWheel1 = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterWheel2 = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterHood = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    carouselMotor = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);
    climberMotor = new CANSparkMax(-1, CANSparkMaxLowLevel.MotorType.kBrushless);

    testMotor = new TalonFX(-1);

    //sensors
    hoodEncoder = new Encoder(-1, -1, true, Encoder.EncodingType.k4X);
    hoodEncoder.setDistancePerPulse(1/360);

    navX = new AHRS(SerialPort.Port.kMXP);

    frontRightAbsEncoder = new AnalogPotentiometer(-1, 360, 0);
    frontLeftAbsEncoder = new AnalogPotentiometer(-1, 360, 0);
    backLeftAbsEncoder = new AnalogPotentiometer(-1, 360, 0);
    backRightAbsEncoder = new AnalogPotentiometer(-1, 360, 0);

    //subsystems and commands

    //drive train
    swerveGroup = new SwerveGroup();
    frontRightModule = new FrontRightModule(driveMotor1, driveMotor2, frontRightAbsEncoder, false);
    frontLeftModule = new FrontLeftModule(driveMotor3, driveMotor4, frontLeftAbsEncoder, false);
    backLeftModule = new BackLeftModule(driveMotor5, driveMotor6, backLeftAbsEncoder, false);
    backRightModule = new BackRightModule(driveMotor7, driveMotor8, backRightAbsEncoder, false);

    drive = new Drive();
    lowGear = new LowGear();
    highGear = new HighGear();

    //intake
    intake = new Intake();
    intakeCommand = new IntakeCommand(0.1);

    //shooter
    shooter = new Shooter();
    shootCommand = new ShootCommand(1000); //rpm

    //carousel
    carousel = new Carousel();
    carouselCommand = new CarouselCommand(0.1);

    //climber
    climber = new Climber();
    climberCommand = new ClimberCommand(0.1);

    //misc commands
    motorTester = new MotorTester();
    calibrateGyro = new CalibrateGyro();

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
    xboxController1A.whileHeld(shootCommand);
    xboxController1B.whenPressed(calibrateGyro);
    xboxController1LBumper.whenPressed(lowGear);
    xboxController1RBumper.whenPressed(highGear);
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
