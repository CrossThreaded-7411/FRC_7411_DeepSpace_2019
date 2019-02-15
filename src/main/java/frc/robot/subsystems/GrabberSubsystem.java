package frc.robot.subsystems;

import frc.robot.RobotMap.PWMport;
import frc.robot.RobotMap.digitalPorts;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

public class GrabberSubsystem extends Subsystem
{
   public VictorSP leftGrabberMotor = new VictorSP(PWMport.leftGrabberMotorPort.getVal());
   public VictorSP rightGrabberMotor = new VictorSP(PWMport.rightGrabberMotorPort.getVal());
   public enum grabberMode { grab, eject, off; }

   //ballSensor returns true when a ball is sensed
   public DigitalInput ballSensor = new DigitalInput(digitalPorts.ballSensor.getVal());

   public void initDefaultCommand()
   {
      // Not needed as the GrabCargo command is already being initialized
      // by the joystickbutton method in OI.java
   }


   public void driveGrabber(grabberMode mode)
   {
      switch(mode)
      {
         case grab:
            leftGrabberMotor.set(0.75);
            rightGrabberMotor.set(-0.75);
            break;

         case eject:
            leftGrabberMotor.set(-1.0);
            rightGrabberMotor.set(1.0);
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