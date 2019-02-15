/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap.F310button;
import frc.robot.commands.DriveManualWithJoystick;
import frc.robot.LiftPosition;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * TODO: Add limit switch stop
 *       Undo Jeff-isms and make is your own
 *       Add upper limit
 *       Add manual control w/ability to switch modes
 *       Tune PID controller
 *       Tune ramp rate
 *       fix kTimeOutMs not in scope issue
 *       Debug pulsing when using other subsystems/commands
 *       Reenable all other subsystems/commands
 */

/**
 * Add your docs here.
 */
public class talonLiftPID extends Subsystem
{
   WPI_TalonSRX liftMotor1 = new WPI_TalonSRX(30);
   WPI_TalonSRX liftMotor2 = new WPI_TalonSRX(31);

   public enum liftMode 
   { 
      down, low, mid, high; 
   }
   liftMode currMode = liftMode.down;

   private boolean lastDown = false;
   private boolean lastLow = false;
   private boolean lastMid = false;
   private boolean lastHigh = false;

   private boolean down = false;
   private boolean low = false;
   private boolean mid = false;
   private boolean high = false;

   @Override
   public void initDefaultCommand()
   {
      setDefaultCommand(new DriveManualWithJoystick());
   }

   public void initLift()
   {
      final int kPIDLoopIdx = 0;                   //
      final int kTimeoutMs = 30;                   // Time to wait for Talon to finish config updates
      final boolean kSensorPhase = false;          // Used to set correct sign of sensor measurement
      final boolean kMotorInvert = true;           // Used to invert motor direction compared to command
      final double kP = 0.25;                       // Proportional gain
      final double kI = 0.00002;                   // Integral gain
      final double kD = 10;                        // Differential gain
      final double kF = 0.0;                       // Feedforward gain
      // int kIzone = 0;
      final double peakPowerRaise = 0.8;           // Control loop power command saturates at this limit on raise
      final double peakPowerLower = -0.6;          // Control loop power command saturates at this limit on lower
      final double neutralToFullRampRate = 2.0;    // Time in seconds control will ramp from neutral to full power

      /* Config the sensor used for Primary PID and sensor direction */
      liftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

      /* Ensure sensor is positive when output is positive */
      liftMotor1.setSensorPhase(kSensorPhase);

      // Set based on what direction you want forward/positive to be. This does not affect sensor phase.
      liftMotor1.setInverted(kMotorInvert);
      liftMotor2.setInverted(kMotorInvert);

      /* Config the peak and nominal outputs, 12V means full */
      liftMotor1.configNominalOutputForward(0, kTimeoutMs);
      liftMotor1.configNominalOutputReverse(0, kTimeoutMs);
      liftMotor1.configPeakOutputForward(peakPowerRaise, kTimeoutMs);
      liftMotor1.configPeakOutputReverse(peakPowerLower, kTimeoutMs);

      /**
       * Config the allowable closed-loop error, Closed-Loop output will be neutral
       * within this range. See Table in Section 17.2.1 for native units per rotation.
       */
      liftMotor1.configAllowableClosedloopError(10, kPIDLoopIdx, kTimeoutMs);

      /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		liftMotor1.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
		liftMotor1.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		liftMotor1.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
		liftMotor1.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

      /**
       * Grab the 360 degree position of the MagEncoder's absolute position, and
       * intitally set the relative sensor to match.
       */
      // int absolutePosition = liftMotor1.getSensorCollection().getQuadraturePosition();

      /* Mask out overflows, keep bottom 12 bits */
      // absolutePosition &= 0xFFF;
      // if (kSensorPhase)
      // {
      //    absolutePosition *= -1;
      // }
      // if (kMotorInvert)
      // {
      //    absolutePosition *= -1;
      // }

      /* Set the quadrature (relative) sensor to match absolute */
      //liftMotor1.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
      liftMotor1.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);

      liftMotor2.follow(liftMotor1);

      /**
       * Zero out the reported quadrature position (this only works for sensors set up as
       * quadrature encoders (i.e. not absolute position)).
       */
      //liftMotor1.getSensorCollection().setQuadraturePosition(0, kTimeoutMs);

      /**
       * Configure the closed-loop ramp rate. This is teh minimum desired time to go from
       * zero to full throttle in seconds
       */
       liftMotor1.configClosedloopRamp(neutralToFullRampRate, kTimeoutMs);
   }


   /**
    * General periodic task for updating PID control information on the Talon and
    * whatever else needs to be done while running the lift
    */
   public void PIDLoop()
   {
      boolean bButton = Robot.oi.driverStick2.getRawButton(F310button.B_Button.getVal());

      int offset;
      switch(currMode) 
      {
         case down:
            offset = LiftPosition.Misc.home.getVal(); // This is the lowest position
            break;

         case low:
            offset = LiftPosition.Rocket.cargoLevel1.getVal(); // This is the first ball position
            break;

         case mid:
            offset = LiftPosition.Rocket.cargoLevel2.getVal(); // This is the middle ball position
            break;

         case high:
            offset = LiftPosition.Rocket.cargoLevel3.getVal(); // This is the high ball position
            break;

         default:
            offset = LiftPosition.Misc.home.getVal();
            break;
      }

      SmartDashboard.putNumber("Offset", offset);
      // if (bButton == true)
      // {
         liftMotor1.set(ControlMode.Position, offset);
         liftMotor2.set(ControlMode.Follower, 30);
      // }
      // else
      // {
      //    stopPID();
      // }

      SmartDashboard.putNumber("Motor Pos", liftMotor1.getSensorCollection().getPulseWidthPosition());
      SmartDashboard.putNumber("Motor Output", liftMotor1.getMotorOutputPercent());
   }

   public void checkLiftButtons() 
   {
      down = Robot.oi.driverStick2.getRawButton(F310button.A_Button.getVal());
      low = Robot.oi.driverStick2.getRawButton(F310button.X_Button.getVal());
      mid = Robot.oi.driverStick2.getRawButton(F310button.Y_Button.getVal());
      high = Robot.oi.driverStick2.getRawButton(F310button.B_Button.getVal());
      
      if(lastDown == true && down == false) {
          currMode = liftMode.down;
      } else if(lastLow == true && low == false) {
         currMode = liftMode.low;
      } else if(lastMid == true && mid == false) {
         currMode = liftMode.mid;
      } else if(lastHigh == true && high == false) {
         currMode = liftMode.high;
      }

      lastDown = down;
      lastLow = low;
      lastMid = mid;
      lastHigh = high;
   }

   public void stopPID()
   {
      liftMotor1.stopMotor();
   }
}
