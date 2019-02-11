/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
   public enum PWMport
   {
      leftFrontDriveMotorPort(0),
      leftRearDriveMotorPort(1),
      rightRearDriveMotorPort(2),
      rightFrontDriveMotorPort(3),
      leftGrabberMotorPort(4),
      rightGrabberMotorPort(5),
      vacuumMotorPort(6),
      rightGantryMotorPort(8),
      leftGantryMotorPort(9);

      private int value;

      private PWMport(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }
   
   
   /********************************************
    * Logitech Extreme 3D Pro joystick mapping *
    ********************************************/
   // Logitech Joystick POV hat
   public enum Extreme3DButton
   {
      trigger(1),                         // Trigger
      topLeftSideButton(2),               // Top left side button
      topLeftRearButton(3),               // Top leftrear button
      topRightRearButton(4),              // Top right rear button
      topLeftFrontButton(5),              // Top left front button
      topRightFrontButton(6),             // Top right front button
      bottomOuterFrontButton(7),          // Bottom outer front button
      bottomInnerFrontButton(8),          // Bottom inner front button
      bottomOuterMiddleButton(9),         // Bottom outer middle button
      bottomInnerMiddleButton(10),         // Bottom inner middle button
      bottomOuterRearButton(11),           // Bottom outer rear button
      bottomInnerRearButton(12);           // Bottom inner rear button

      private int value;

      private Extreme3DButton(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }


   // Logitech Joystick POV hat
   public enum Extreme3DPOV
   {
      POV1(1),            // hat switch North
      POV2(2),            // hat switch Northeast
      POV3(3),            // hat switch East
      POV4(4),            // hat switch Southeast
      POV5(5),            // hat switch South
      POV6(6),            // hat switch Southwest
      POV7(7),            // hat switch West
      POV8(8);            // hat switch Northwest

      private int value;

      private Extreme3DPOV(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }


   /********************************************
    * Logitech F310 joystick mapping *
    ********************************************/
   public enum F310button
   {
      A_Button(1),           // A button
      B_Button(2),           // B button
      X_Button(3),           // X button
      Y_Button(4),           // Y button
      leftTrigger(5),           // Left digital button
      rightTrigger(6),           // Right digital button
      button7(7),           // 
      button8(8),           // 
      button9(9),           // Left analog push
      button10(10);          // Right analog push

      private int value;

      private F310button(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }

   public enum F310axis
   {
      leftXAxis(1),        // Right (negative), left (positive)
      LeftYAxis(2),        // Rearward (negative), forward (positive)
      leftTrigger(3),      // Fully pressed (negative), released (positive)
      rightXAxis(4),       // Right (negative), left (positive)
      rightYAxis(5),       // Rearward (negative), forward (positive)
      rightTrigger(6);     // Fully pressed (negative), released (positive)

      private int value;

      private F310axis(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }        
}