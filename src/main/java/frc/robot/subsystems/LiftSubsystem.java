package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Robot;
import frc.robot.commands.DriveManualWithJoystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LiftSubsystem extends Subsystem
{
   public WPI_TalonSRX liftMotor1 = new WPI_TalonSRX(30);
   public WPI_TalonSRX liftMotor2 = new WPI_TalonSRX(31);

   SpeedControllerGroup liftMotors = new SpeedControllerGroup(liftMotor1, liftMotor2);

   // Relative encoder position
   double encoderZeroPosition = 0.0;
   double liftPosition = 0.0;

   public void initDefaultCommand()
   {
      //setDefaultCommand(new DriveManualWithJoystick());
   }

   public void initLift()
   {
      stopLift();
      setEncoderZero();
   }

   // need to get encoder values and in auto set encoder values.
   public void driveLift()
   {
      double cmdPID = 0.0;
      int absolutePosition = liftMotor1.getSensorCollection().getPulseWidthPosition();
      
      calculateRelativePosition();
      cmdPID = liftPIDcontrol();

      if (Math.abs(Robot.oi.driverStick2.getRawAxis(5)) <= 0.1)
      {
         liftMotors.set(0);
      }
      else
      {
         if (liftPosition < 18000)
         {
            //liftMotors.set(Robot.oi.driverStick2.getRawAxis(5) * 0.5);
            liftMotors.set(cmdPID);
         }
         else
         {
            liftMotors.set(0);
         }
      }

      SmartDashboard.putNumber("Encoder Counts ", absolutePosition);
      SmartDashboard.putNumber("Relative Position", liftPosition);
      SmartDashboard.putNumber("encoderZeroPosition", encoderZeroPosition);
      SmartDashboard.putNumber("cmdPID", cmdPID);
   }

   public void stopLift()
   {
      liftMotors.set(0);
   }

   /**
    * To simplify debugging and make numbers act consistant instead of crossing zero, code will
    * use a relative position using the initial encoder position as the point of reference. This
    * method captures the initial encoder counts used for calculating relative position.
    */
   public void setEncoderZero()
   {
      encoderZeroPosition = liftMotor1.getSensorCollection().getPulseWidthPosition();
   }

   /**
    * Calculate the relative position in encoder counts which is the difference between the initial
    * encoder reading and actual encoder position
    */
    private void calculateRelativePosition()
    {
       double actualPosition = liftMotor1.getSensorCollection().getPulseWidthPosition();
       liftPosition = encoderZeroPosition - actualPosition;
    }


    /**
     * testPID
     */
    private double liftPIDcontrol()
    {
       double cmdMax = 0.3;
       double cmd = 0.0;
       double setPoint = 3000;
       double Kp = 0.002;

       double error = setPoint - liftPosition;
       cmd = -1 * (Kp * error);

       if (Math.abs(cmd) > cmdMax)
       {
          cmd = Math.signum(cmd) * cmdMax;
       }

       SmartDashboard.putNumber("error", error);
       SmartDashboard.putNumber("cmd", cmd);

       return cmd;
    }
}