package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap.PWMport;

public class PneumaticSubsystem extends Subsystem
{
   public Solenoid pressureRelease = new Solenoid(3, 0);
   public VictorSP vacuum = new VictorSP(PWMport.vacuumMotorPort.getVal());
 
   private final double vacuumOff = 0.0;
   private final double vacuumOn = 1.0;
   
   
   public void initDefaultCommand()
   {
      // Not needed as the ToggleHatchGrabDeploy command is already being initialized
      // by the joystickbutton method in OI.java
   }


   public void setPneumaticsForHatchGrab()
   {
      pressureRelease.set(false);
      vacuum.set(vacuumOn);
   }


   public void setPneumaticsForHatchRelease()
   {
      pressureRelease.set(true);
      vacuum.set(vacuumOff);
   }

   
   public void stopPneumatics()
   {
      pressureRelease.set(false);
      vacuum.set(vacuumOff);
   }
}   