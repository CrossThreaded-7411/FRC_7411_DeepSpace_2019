/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
   
   /*********************************
    * PWM motor control mapping *
    *********************************/
   public static final int leftFrontDriveMotorPort = 0;
   public static final int leftRearDriveMotorPort =1;
   public static final int rightRearDriveMotorPort = 2;
   public static final int rightFrontDriveMotorPort = 3;
   public static final int leftGrabberMotorPort = 4;
   public static final int rightGrabberMotorPort = 5;
   public static final int vacuumMotorPort = 6;
   public static final int leftGantryMotorPort = 8;
   public static final int rightGantryMotorPort = 9;

   

   //public static final int  = 6;
   //public static final int  = 7;
   //public static final int  = 8;
   //public static final int  = 9;
   //public static final int  = 10;
   //public static final int  = 11;
   //public static final int  = 12;
   //public static final int  = 13;
   //public static final int  = 14;
   //public static final int  = 15;

   
   
   /*********************************
    * Driver 1 joystick mapping *
    *********************************/
   public static final int trigger = 1;
   public static final int topLeftSideButton = 2;
   public static final int topLeftRearButton = 3;
   public static final int topRightRearButton = 4;
   public static final int topLeftFrontButton = 5;
   public static final int topRightFrontButton = 6;
   public static final int bottomOuterFrontButton = 7;
   public static final int bottomInnerFrontButton = 8;
   public static final int bottomOuterMiddleButton = 9;
   public static final int bottomInnerMiddleButton = 10;
   public static final int bottomOuterRearButton = 11;
   public static final int bottomInnerRearButton = 12;
   
   public static final int hatSwitchNorth = 1;
   public static final int hatSwitchNorthEast = 2;
   public static final int hatSwitchEast = 3;
   public static final int hatSwitchSouthEast = 4;
   public static final int hatSwitchSouth = 5;
   public static final int hatSwitchSouthWest = 6;
   public static final int hatSwitchWest = 7;
   public static final int hatSwitchNorthWest = 8;

   
   public static final int leftTrigger = 2;
   public static final int rightTrigger = 3;
}