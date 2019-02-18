/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ToggleHatchGrabDeploy extends Command
{
   private toggleState toggle = toggleState.hatchHold;

   public ToggleHatchGrabDeploy()
   {
      // Use requires() here to declare subsystem dependencies
      requires(Robot.Grabber);
   }
   
   /**
    * Enumeration used to toggle the pneumatic operating state.
    * Enum class includes means to return enumerated value as a number
    * as it is desired to output the state to the SmartDashboard.
    * At this time, not sure if there is a better way to push
    * enumerated values to display. If so, this is overly complicated
    * and could be simplified.
    */
   public enum toggleState
   {
      hatchHold(1),
      hatchDeploy(2);

      private int value;
      private toggleState(int value) { this.value = value; }
      public int getVal() { return value; }
   }

   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      Robot.Pneumatics.stopPneumatics();
   }

   /**
    * This execute switches pneumatics between holding a hatch (vacuum on, bleed valve off)
    * and deploying a hatch (vacuum off, bleed valve on).

    * IMPORTANT:  This execute method is designed as a run ONCE AND ONLY ONCE. The command
    * is kicked off via a joystickbutton instance that is tied to this command. Desired
    * functionality is to toggle between pneumatic operating states each time the button is
    * pressed. Therefore, this method will toggle the pneumatic operating state and turn
    * on or off actuators as desired and then exit the command. The next time the button is
    * pressed, the execute method will again toggle the pneumatic operating state and exit.
    */
   @Override
   protected void execute()
   {
      togglePneumaticState();

      switch(toggle)
      {
         case hatchHold:
            Robot.Pneumatics.setPneumaticsForHatchGrab();
            break;
         
         case hatchDeploy:
            Robot.Pneumatics.setPneumaticsForHatchRelease();
            break;
      }

      SmartDashboard.putNumber("Pneumatic State", toggle.getVal());
   }

   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return true;
   }

   // Called once after isFinished returns true
   @Override
   protected void end()
   {
      // Do nothing, need to leave pneumatics operating in exited state
      //Robot.Pneumatics.stopPneumatics();
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   @Override
   protected void interrupted()
   {
      // Do nothing, need to leave pneumatics operating in exited state
      Robot.Pneumatics.stopPneumatics();
   }

   // Toggles the pneumatic state between holding a hatch (vacuum on, bleed valve off)
   // and deploying a hatch (vacuum off, bleed valve on)
   private void togglePneumaticState()
   {
      if (toggle == toggleState.hatchDeploy)
      {
         toggle = toggleState.hatchHold;
      }
      else
      {
         toggle = toggleState.hatchDeploy;
      }
   }
}
