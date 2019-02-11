/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.DriveManualWithJoystick;
import frc.robot.subsystems.DriveTrainFourMotorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.GantrySubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
   //public static DriveTrainTwoMotorSubsystem DriveTrain;
   public static DriveTrainFourMotorSubsystem DriveTrain;
   public static LiftSubsystem Lift;
   public static GrabberSubsystem Grabber;
   public static GantrySubsystem Gantry;
   public static OI oi;
   public static PneumaticSubsystem Pneumatics;

   Command autonomousCommand;
   SendableChooser<Command> chooser = new SendableChooser<>();
   

   /**
    * This function is run when the robot is first started up and should be used
    * for any initialization code.
    */
   @Override
   public void robotInit()
   {
      DriveTrain = new DriveTrainFourMotorSubsystem();
      Lift = new LiftSubsystem();
      Grabber = new GrabberSubsystem();
      Gantry = new GantrySubsystem();
      Pneumatics = new PneumaticSubsystem();
      oi = new OI();
      
      // Create camera server to stream video to driver station
      //CameraServer.getInstance().startAutomaticCapture();

      SmartDashboard.putData("Drive Arcade Normal  ", new DriveManualWithJoystick());
      SmartDashboard.putData("Auto Mode", chooser);
   }


   /**
    * This autonomous (along with the chooser code above) shows how to select
    * between different autonomous modes using the dashboard. The sendable chooser
    * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
    * remove all of the chooser code and uncomment the getString code to get the
    * auto name from the text box below the Gyro
    *
    * <p>
    * You can add additional auto modes by adding additional commands to the
    * chooser code above (like the commented example) or additional comparisons to
    * the switch structure below with additional strings & commands.
    */
   @Override
   public void autonomousInit()
   {
      autonomousCommand = chooser.getSelected();

      /*
       * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
       * switch(autoSelected) { case "My Auto": autonomousCommand = new
       * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
       * ExampleCommand(); break; }
       */

      // schedule the autonomous command (example)
      if (autonomousCommand != null)
      {
         autonomousCommand.start();
      }
   }

   /**
    * This function is called periodically during autonomous.
    */
   @Override
   public void autonomousPeriodic()
   {
      Scheduler.getInstance().run();
   }

   @Override
   public void teleopInit()
   {
      // This makes sure that the autonomous stops running when teleop starts running
      // If you want the autonomous to continue until interrupted by another command, remove
      // this line or comment it out.
      if (autonomousCommand != null)
      {
         autonomousCommand.cancel();
         
      }
   }

   /**
    * This function is called periodically during operator control.
    */
   @Override
   public void teleopPeriodic()
   {
      Scheduler.getInstance().run();
      //LiveWindow.run();
      
      SmartDashboard.putNumber("Left Trigger Position ", Robot.oi.getLeftTriggerPosition());
      SmartDashboard.putNumber("Right Trigger Position ", Robot.oi.getRightTriggerPosition());
      SmartDashboard.putNumber("Gantry Power", Robot.oi.getDriver2AxisY());
   }

   /**
    * This function is called periodically during test mode.
    */
   @Override
   public void testPeriodic()
   {
   }
}
