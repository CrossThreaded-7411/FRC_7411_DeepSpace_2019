package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainTwoMotorSubsystem extends Subsystem
{

   // Create instances of drivetrain motor PWM controllers and group together as a
   // differential
   private VictorSP leftMotor = new VictorSP(RobotMap.leftFrontDriveMotorPort);
   private VictorSP rightMotor = new VictorSP(RobotMap.rightFrontDriveMotorPort);

   // Use differential drive scheme for on motor on each side, if
   // using multiple motors use a speed controller group
   private DifferentialDrive driveBase = new DifferentialDrive(leftMotor, rightMotor);
   
   private boolean steerByZAxis = false;
   private boolean squaredFlag = false;

   // When not other command is running, let driver operator using joystick
   public void initDefaultCommand()
   {
      setDefaultCommand(new DriveManualWithJoystick());
   }
   
     
   /**
    * This method handles which drive strategy is used and the associated options
    */
   public void driveArcade()
   {
      driveBase.arcadeDrive(Robot.oi.getDriver1AxisY(), getRotation(), getSquaredOption());

      SmartDashboard.putNumber("Left Power  ", leftMotor.getSpeed());
      SmartDashboard.putNumber("Right Power  ", rightMotor.getSpeed());
      SmartDashboard.putBoolean("Squared Flag  ", squaredFlag);
      SmartDashboard.putBoolean("Steer By Z-Axis  ", steerByZAxis);
   }
   
   
   /**
    * This method handles which drive strategy is used and the associated options
    */
   public void driveArcadeAuto(double speed, double rotation)
   {
      driveBase.arcadeDrive(speed, speed, false);
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
