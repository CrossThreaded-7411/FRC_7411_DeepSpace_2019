/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
   
   public Joystick driverStick1 = new Joystick(0);
   public Joystick driverStick2 = new Joystick(1);
   
   // Deadband function
   public double deadband(double input, double band) 
   {
	   if(input < band && input > -band) {
		   return 0;
	   } else {
		   return input;
	   }
   }
   
   /*********************************
    * Driver 1 Methods              *
    *********************************/
   
   // Driver 1 x-axis steers left/right
   public double getDriver1AxisX()
   {
      return deadband(driverStick1.getX(), 0.05);
   }
   
   // Driver 1 y-axis drives forward/reverse
   public double getDriver1AxisY()
   {
	  // Invert axis as forward on joystick is negative value
      return deadband(-driverStick1.getY(), 0.05);
   }
   
   // Driver 1 Z-axis steers left/right
   public double getDriver1AxisZ()
   {
      return deadband(driverStick1.getZ(), 0.07)/1.5;
   }
   
   // Button to switch to arcade style driving with Squared feature off
   public boolean getArcadeNormal()
   {
      return driverStick1.getRawButton(RobotMap.topLeftFrontButton);
   }
   
   // Button to switch to arcade style driving with Squared feature on
   public boolean getArcadeSquared()
   {
      return driverStick1.getRawButton(RobotMap.topRightFrontButton);
   }
   
   // Button to assign steering to x-axis
   public boolean getTurnByAxisX()
   {
      return driverStick1.getRawButton(RobotMap.topLeftRearButton);
   }
   
   // Button to assign steering to z-axis
   public boolean getTurnByAxisZ()
   {
      return driverStick1.getRawButton(RobotMap.topRightRearButton);
   }
      
   
   /*********************************
    * Driver 2 Methods              *
    *********************************/
   
   // Driver 2 x-axis steers left/right
   public double getDriver2AxisX()
   {
      return driverStick2.getX();
   }
   
   // Driver 2 y-axis drives forward/reverse
   public double getDriver2AxisY()
   {
      return -driverStick2.getY();
   }
   
   //Driver 2 triggers control both crate collector motors
   public double getRightTriggerPosition()
   {
      return driverStick2.getRawAxis(3);
   }
   
   public double getLeftTriggerPosition()
   {
      return driverStick2.getRawAxis(2);
   }
   
   //Driver2 Right Bumper ejects
   public boolean getXPressed()
   {
      return driverStick2.getRawButton(3);
   }
   
   // Button to re-center the lift
   public boolean getResetLift() 
   {
	   // 10
	   return driverStick2.getRawButton(8);
   }
   
//   public boolean getXReleased()
//   {
//	   return driverStick2.getRawButtonReleased(3);
//   }
}