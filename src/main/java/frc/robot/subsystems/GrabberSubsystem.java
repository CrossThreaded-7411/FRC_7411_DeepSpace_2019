package frc.robot.subsystems;

import frc.robot.RobotMap.PWMport;
import frc.robot.commands.StopCargo;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

public class GrabberSubsystem extends Subsystem
{
   public VictorSP leftGrabberMotor = new VictorSP(PWMport.leftGrabberMotorPort.getVal());
   public VictorSP rightGrabberMotor = new VictorSP(PWMport.rightGrabberMotorPort.getVal());
   public enum grabberMode { grab, eject, off; }

   public void initDefaultCommand()
   {
      //setDefaultCommand(new StopCargo());
   }


   public void driveGrabber(grabberMode mode)
   {
      switch(mode)
      {
         case grab:
            leftGrabberMotor.set(-1.0);
            rightGrabberMotor.set(1.0);
            break;

         case eject:
            leftGrabberMotor.set(1.0);
            rightGrabberMotor.set(-1.0);
            break;

         case off:
         default:
            stopGrabber();
            break;
      }
   }


   public void stopGrabber()
   {
      leftGrabberMotor.set(0);
      rightGrabberMotor.set(0);
   }
}