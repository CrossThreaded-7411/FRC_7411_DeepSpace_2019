package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap.PWMport;
import frc.robot.commands.DriveManualWithJoystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainFourMotorSubsystem extends Subsystem
{
   // Create instances of drivetrain motor PWM controllers and group together as a control groups
	private VictorSP leftFrontMotor = new VictorSP(PWMport.leftFrontDriveMotorPort.getVal());
   private VictorSP leftReartMotor = new VictorSP(PWMport.leftRearDriveMotorPort.getVal());
   SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(leftFrontMotor, leftReartMotor);

   private VictorSP rightFrontMotor = new VictorSP(PWMport.rightFrontDriveMotorPort.getVal());
   private VictorSP rightRearMotor = new VictorSP(PWMport.rightRearDriveMotorPort.getVal());
   SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
   

   // Use differential drive scheme for on motor on each side, if using multiple motors use a speed controller group
   private DifferentialDrive driveBase = new DifferentialDrive(leftDriveMotors, rightDriveMotors);
   
   private boolean steerByZAxis = false;
   private boolean squaredFlag = false;

   private double maxHeight = 0;
   private double maxPower = 0;

   // When not other command is running, let driver operator using joystick
   public void initDefaultCommand()
   {
      //setDefaultCommand(new DriveManualWithJoystick());
   }
   
     
   /**
    * This method handles which drive strategy is used and the associated options
    */
   public void driveArcade()
   {
      maxHeight = Robot.Lift.percentMaxHeight();
      maxPower = -0.70 * maxHeight + 1;

      driveBase.arcadeDrive(Robot.oi.getDriver1AxisY() * maxPower, getRotation() * maxPower, getSquaredOption());
      driveBase.setDeadband(0.1);

      SmartDashboard.putNumber("Left Power  ", leftFrontMotor.getSpeed());
      SmartDashboard.putNumber("Right Power  ", rightFrontMotor.getSpeed());
      SmartDashboard.putBoolean("Squared Flag  ", squaredFlag);
      SmartDashboard.putBoolean("Steer By Z-Axis  ", steerByZAxis);
   }
   
   
   /**
    * This method handles which drive strategy is used and the associated options
    */
   public void driveArcadeAuto(double speed, double rotation)
   {
      driveBase.arcadeDrive(speed, rotation, false);
   }
   
      
   /**
    * This method turns off motor drive when subsystem is disabled
    */
   public void driveArcadeStop()
   {
      driveBase.arcadeDrive(0, 0, false);
   }

   
   /**
    * Method set the steering axis based on driver preference through a button selection. 
    */
   private double getRotation()
   {
      double rotationValue = 0.0d;

      if (Robot.oi.getTurnByAxisX())
      {
         steerByZAxis = false;
      }
      else if (Robot.oi.getTurnByAxisZ())
      {
         steerByZAxis = true;
      }
      
      if (steerByZAxis)
      {
         rotationValue = Robot.oi.getDriver1AxisZ();
      }
      else
      {
         rotationValue = Robot.oi.getDriver1AxisX();
      }

      return rotationValue;
   }
   
   
   /**
    * Method determines if driver wishes to use the squared feature in arcade driving mode. 
    */
   private boolean getSquaredOption()
   {

      if (Robot.oi.getArcadeNormal())
      {
         squaredFlag = false;
      }
      else if (Robot.oi.getArcadeSquared())
      {
         squaredFlag = true;
      }
      
      return squaredFlag;
   }
}
