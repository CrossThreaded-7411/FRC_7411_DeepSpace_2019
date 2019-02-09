package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class AutoStop extends Command
{   
   public AutoStop()
   {
      // Required to declare subsystem dependencies
      requires(Robot.DriveTrain);
      requires(Robot.Gantry);
      
      // set 4 second timeout
      setTimeout(5);
   }

   // Called just before this Command runs the first time
   protected void initialize()
   {
      Robot.DriveTrain.driveArcadeStop();
      Robot.Gantry.stopGantry();
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute()
   {        
      Robot.DriveTrain.driveArcadeAuto(0, 0);
      Robot.Gantry.driveGantryAuto(0);
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished()
   {
      return  isTimedOut();
   }

   // Called once after isFinished returns true
   protected void end()
   {
      Robot.DriveTrain.driveArcadeStop();
      Robot.Gantry.stopGantry();
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   protected void interrupted()
   {
      end();
   }
}
