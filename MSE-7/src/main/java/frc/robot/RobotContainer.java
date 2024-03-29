/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CalibrateGyro;
import frc.robot.commands.CalibrateModules;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.HighGear;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakePivot;
import frc.robot.commands.LowGear;
import frc.robot.commands.MoveHood;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.WinchCommand;
import frc.robot.subsystems.BackLeftModule;
import frc.robot.subsystems.BackRightModule;
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
  public static JoystickButton xboxController1LStick, xboxController1RStick;
  public static JoystickButton xboxController1Start, xboxController1Back;

  public static Trigger triggerA;
  public static Trigger triggerB;
  public static Trigger triggerX;
  public static Trigger triggerY;
  public static Trigger triggerBack;
  public static Trigger triggerBackA;
  public static Trigger triggerBackB;
  public static Trigger triggerBackX;
  public static Trigger triggerBackY;

  public static Joystick xboxController2;
  public static JoystickButton xboxController2A;
  public static JoystickButton xboxController2B;
  public static JoystickButton xboxController2X;
  public static JoystickButton xboxController2Y;
  public static JoystickButton xboxController2LBumper, xboxController2RBumper;
  public static JoystickButton xboxController2LStick, xboxController2RStick;
  public static JoystickButton xboxController2Start, xboxController2Back;

  public static JoystickButton logitechTrigger;
  public static JoystickButton logitechThumb;
  public static JoystickButton logitech3;
  public static JoystickButton logitech4;
  public static JoystickButton logitech5;
  public static JoystickButton logitech6;
  public static JoystickButton logitech7;
  public static JoystickButton logitech8;
  public static JoystickButton logitech9;
  public static JoystickButton logitech10;
  public static JoystickButton logitech11;
  public static JoystickButton logitech12;

  public static Trigger triggerLogitech3;
  public static Trigger triggerLogitech4;
  public static Trigger triggerLogitech5;
  public static Trigger triggerLogitech6;
  public static Trigger triggerLogitech8;
  public static Trigger triggerLogitech83;
  public static Trigger triggerLogitech84;
  public static Trigger triggerLogitech85;
  public static Trigger triggerLogitech86;
 

  public static WPI_VictorSPX intakePivotMotor;
  public static WPI_VictorSPX intakeMotor;
  public static CANSparkMax shooterWheel1;
  public static CANSparkMax shooterWheel2;
  public static WPI_VictorSPX shooterHood;
  public static WPI_VictorSPX climberMotor;
  public static TalonFX winchMotor;
  
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
  public static PowerDistributionPanel pdp;

  public static SwerveGroup swerveGroup;
  public static SwerveModule frontRightModule, frontLeftModule, backLeftModule, backRightModule;
  public static Drive drive;
  public static HighGear highGear;
  public static LowGear lowGear;

  public static Intake intake;
  public static IntakeCommand intakeCommand;
  public static IntakePivot intakePivot;
  public static IntakePivot reverseIntakePivot;

  public static Shooter shooter;
  public static ShootCommand shootCommand;
  public static ShootCommand reverseShooter;
  public static MoveHood lowerHood;
  public static MoveHood raiseHood;
  
  public static Climber climber;
  public static ClimberCommand climberCommand;
  public static ClimberCommand reverseClimber;
  public static WinchCommand winchCommand;
  public static WinchCommand reverseWinch;

  public static CalibrateGyro calibrateGyro;
  public static CalibrateModules calibrateModules;
  
  public RobotContainer() {
    //xbox controllers
    xboxController1 = new Joystick(0);
    xboxController1A = new JoystickButton(xboxController1, 1);
    xboxController1B = new JoystickButton(xboxController1, 2);
    xboxController1X = new JoystickButton(xboxController1, 3);
    xboxController1Y = new JoystickButton(xboxController1, 4);
    xboxController1LBumper = new JoystickButton(xboxController1, 5);
    xboxController1RBumper = new JoystickButton(xboxController1, 6);
    xboxController1Back = new JoystickButton(xboxController1, 7);
    xboxController1Start = new JoystickButton(xboxController1, 8);
    xboxController1LStick = new JoystickButton(xboxController1, 9);
    xboxController1RStick = new JoystickButton(xboxController1, 10);

    triggerA = new JoystickButton(xboxController1, 1);
    triggerB = new JoystickButton(xboxController1, 2);
    triggerX = new JoystickButton(xboxController1, 3);
    triggerY = new JoystickButton(xboxController1, 4);
    triggerBack = new JoystickButton(xboxController1, 7);

    triggerBackA = triggerA.and(triggerBack);
    triggerBackB = triggerB.and(triggerBack);
    triggerBackX = triggerX.and(triggerBack);
    triggerBackY = triggerY.and(triggerBack);

    xboxController2 = new Joystick(1);
    xboxController2A = new JoystickButton(xboxController2, 1);
    xboxController2B = new JoystickButton(xboxController2, 2);
    xboxController2X = new JoystickButton(xboxController2, 3);
    xboxController2Y = new JoystickButton(xboxController2, 4);
    xboxController2LBumper = new JoystickButton(xboxController2, 5);
    xboxController2RBumper = new JoystickButton(xboxController2, 6);
    xboxController2Back = new JoystickButton(xboxController2, 7);
    xboxController2Start = new JoystickButton(xboxController2, 8);
    xboxController2LStick = new JoystickButton(xboxController2, 9);
    xboxController2RStick = new JoystickButton(xboxController2, 10);

    //Button mappings for logitech controller

    logitechTrigger = new JoystickButton(xboxController1, 1);
    logitechThumb = new Joystickbutton(xboxController1, 2);
    logitech3 = new JoystickButton(xboxController1, 3);
    logitech4 = new JoystickButton(xboxController1, 4);
    logitech5 = new JoystickButton(xboxController1, 5);
    logitech6 = new JoystickButton(xboxController1, 6);
    logitech7 = new JoystickButton(xboxController1, 7);
    logitech8 = new JoystickButton(xboxController1, 8);
    logitech9 = new JoystickButton(xboxController1, 9);
    logitech10 = new JoystickButton(xboxController1, 10);
    logitech11 = new JoystickButton(xboxController1, 11);
    logitech12 = new JoystickButton(xboxController1, 12);

    triggerLogitech3 = new JoystickButton(xboxController1, 3);
    triggerLogitech4 = new JoystickButton(xboxController1, 4);
    triggerLogitech5 = new JoystickButton(xboxController1, 5);
    triggerLogitech6 = new JoystickButton(xboxController1, 6);
    triggerLogitech8 = new JoystickButton(xboxController1, 8);

    triggerLogitech83 = triggerLogitech8.and(triggerLogitech3);
    triggerLogitech84 = triggerLogitech8.and(triggerLogitech4);
    triggerLogitech85 = triggerLogitech8.and(triggerLogitech5);
    triggerLogitech86 = triggerLogitech8.and(triggerLogitech6);


    //speed controllers
    intakePivotMotor = new WPI_VictorSPX(14);
    intakeMotor = new WPI_VictorSPX(11);
    shooterWheel1 = new CANSparkMax(20, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterWheel2 = new CANSparkMax(21, CANSparkMaxLowLevel.MotorType.kBrushless);
    shooterHood = new WPI_VictorSPX(9);
    winchMotor = new TalonFX(12);
    climberMotor = new WPI_VictorSPX(15);

    driveMotor1 = new TalonFX(1);
    driveMotor2 = new TalonFX(2);
    driveMotor3 = new TalonFX(3);
    driveMotor4 = new TalonFX(4);
    driveMotor5 = new TalonFX(5);
    driveMotor6 = new TalonFX(6);
    driveMotor7 = new TalonFX(7);
    driveMotor8 = new TalonFX(8);
    //testMotor = new TalonFX(-1);

    //sensors
    hoodEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
    hoodEncoder.setDistancePerPulse(1/360);

    navX = new AHRS(SerialPort.Port.kMXP);
    

    frontRightAbsEncoder = new AnalogPotentiometer(0, 360, 0);
    frontLeftAbsEncoder = new AnalogPotentiometer(1, 360, 0);
    backLeftAbsEncoder = new AnalogPotentiometer(3, 360, 0);
    backRightAbsEncoder = new AnalogPotentiometer(2, 360, 0);

    //subsystems and commands

    //drive train
    frontRightModule = new FrontRightModule(driveMotor1, driveMotor2, frontRightAbsEncoder);
    frontLeftModule = new FrontLeftModule(driveMotor3, driveMotor4, frontLeftAbsEncoder);
    backLeftModule = new BackLeftModule(driveMotor5, driveMotor6, backLeftAbsEncoder);
    backRightModule = new BackRightModule(driveMotor7, driveMotor8, backRightAbsEncoder);
    swerveGroup = new SwerveGroup();

    drive = new Drive();
    lowGear = new LowGear();
    highGear = new HighGear();

    //intake
    intake = new Intake();
    intakeCommand = new IntakeCommand(1);   //changed
    reverseIntake = new IntakeCommand(-0.5);
    intakePivot = new IntakePivot(0.4);
    reverseIntakePivot = new IntakePivot(-0.4);

    //shooter
    shooter = new Shooter();
    shootCommand = new ShootCommand(5000); //rpm
    reverseShooter = new ShootCommand(-500); //rpm
    lowerHood = new MoveHood(0.2);
    raiseHood = new MoveHood(-0.2);

    //climber
    climber = new Climber();
    climberCommand = new ClimberCommand(0.75);
    reverseClimber = new ClimberCommand(-0.75);
    winchCommand = new WinchCommand(0.5);
    reverseWinch = new WinchCommand(-0.5);

    //misc commands
    calibrateGyro = new CalibrateGyro();
    calibrateModules = new CalibrateModules();
    // Configure the button bindings
    configureLogitechBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  
  public static void configureButtonBindings() {
    
      xboxController1A.whileHeld(shootCommand);
      //xboxController1Start.whenPressed(calibrateGyro);
      //xboxController1Back.whenPressed(calibrateModules); only enable when testing
      xboxController1B.whileHeld(reverseShooter);    
      xboxController1Y.whileHeld(intakeCommand);
      //xboxController1X.whileHeld(intakePivot);
      xboxController1LBumper.whileHeld(lowerHood);
      xboxController1RBumper.whileHeld(raiseHood);


      triggerBackA.whileActiveContinuous(climberCommand);
      triggerBackB.whileActiveContinuous(reverseClimber);
      triggerBackX.whileActiveContinuous(winchCommand);
      triggerBackY.whileActiveContinuous(reverseWinch);
  }

  public static void configureLogitechBindings() {
      logitechTrigger.whileHeld(shootCommand);
      logitechThumb.whileHeld(intakeCommand);
      logitech12.whileHeld(raiseHood);
      logitech11.whileHeld(lowerHood);
      logitech5.whileHeld(reverseShooter);
      logitech6.whileHeld(reverseIntake);
      logitech9.whileHeld(intakePivot);
      logitech10.whileHeld(reverseIntakePivot);

      //endgame commands
      triggerLogitech85.whileHeld(climberCommand);
      triggerLogitech83.whileHeld(reverseClimber);
      triggerLogitech86.whileHeld(winchCommand);
      triggerLogitech84.whileHeld(reverseWinch);

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
