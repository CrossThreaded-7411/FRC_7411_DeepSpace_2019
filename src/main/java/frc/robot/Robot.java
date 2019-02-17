/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.core.Mat;
import org.opencv.osgi.OpenCVInterface;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Point;
import java.util.List;
import java.util.ArrayList;

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
import frc.robot.subsystems.talonLiftPID;
import frc.robot.subsystems.GantrySubsystem;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
   public static DriveTrainFourMotorSubsystem DriveTrain;
   //public static LiftSubsystem Lift;
   public static talonLiftPID Lift;
   public static GrabberSubsystem Grabber;
   public static GantrySubsystem Gantry;
   public static PneumaticSubsystem Pneumatics;
   public static OI oi;

   //NetworkTableEntry grip;
   UsbCamera camera;
   CvSink cvSink;
   CvSource outputStream;
   Scalar lowerBounds = new Scalar(10,0,252);
   Scalar upperBounds = new Scalar(50,30,255);
   Scalar zero = new Scalar(0,0,0);
   int mode = 0;
   int method = 1;
   int largest = 0;
   

   
   
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
      //Lift = new LiftSubsystem();
      Lift = new talonLiftPID();
      Grabber = new GrabberSubsystem();
      Gantry = new GantrySubsystem();
      Pneumatics = new PneumaticSubsystem();
      oi = new OI();


      
      // Create camera server to stream video to driver station

      Thread cameraThread = new Thread(() ->
      {
         List<MatOfPoint> contours = new ArrayList<>();

         camera = CameraServer.getInstance().startAutomaticCapture();
         camera.setResolution(640, 480);

         CvSink cvSink = CameraServer.getInstance().getVideo();
         CvSource outputStream = CameraServer.getInstance().putVideo("Output", 640, 480);

         Mat pic = new Mat();
         Mat out = new Mat();
         Mat rectPic = new Mat(480, 640, CvType.CV_64F);
         while(true) {
            largest = 0;
            if(cvSink.grabFrame(pic) == 0) {
               outputStream.notifyError(cvSink.getError());
               continue;
            }
            Imgproc.cvtColor(pic, pic, Imgproc.COLOR_RGB2HSV);
            Core.inRange(pic, lowerBounds, upperBounds, pic);
            Imgproc.findContours(pic, contours, out, mode, method);

            if(contours.size() > 0){
               for(int i = 0; i < contours.size() - 1; i++)
               {
                  if(Imgproc.contourArea(contours.get(i)) > Imgproc.contourArea(contours.get(largest))) {
                     largest = i;
                  }
               }

               Rect rect = Imgproc.boundingRect(contours.get(largest));
               Imgproc.rectangle(rectPic, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255,255,255), 3);

               System.out.print(Imgproc.contourArea(contours.get(largest)));
               System.out.print(" ");
               System.out.println(rect.x + rect.width/2);

            }


            outputStream.putFrame(rectPic);

            contours.clear();
            pic.release();
            out.release();
            rectPic.setTo(zero);
         }
      });
      cameraThread.start();


      //NetworkTableInstance inst = NetworkTableInstance.getDefault();
      //NetworkTable table = inst.getTable("datatable");
      
      //grip = table.getEntry("GRIP");
      

      //SmartDashboard.putData("Drive Arcade Normal  ", new DriveManualWithJoystick());
      //SmartDashboard.putData("Auto Mode", chooser);
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
      Lift.initLift();
      Lift.resetLift();
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
      SmartDashboard.putBoolean("Ball Sensed:", Robot.Grabber.ballSensor.get());
   }

   

   /**
    * This function is called periodically during test mode.
    */
   @Override
   public void testPeriodic()
   {
   }
}
