package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveManualWithJoystick extends Command
{
   public DriveManualWithJoystick()
   {
      // Required to declare subsystem dependencies
      // requires(Robot.DriveTrain);                                                                                                                                                                                                                                         
      requires(Robot.Lift);
      // requires(Robot.Gantry);
   }

   // Called just before this Command runs the first time
   protected void initialize()
   {
      //Robot.DriveTrain.driveArcadeStop();
      Robot.Lift.initLift();
      // Robot.Gantry.stopGantry();
      // Robot.Gantry.init();
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute()
   {
      // Robot.DriveTrain.driveArcade();
      //Robot.Lift.driveLift();

      Robot.Lift.PIDLoop();
      Robot.Lift.checkButtons();
      // Robot.Gantry.driveGantry();
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished()
   {
      return false;
   }

   // Called once after isFinished returns true
   protected void end()
   {
      // Robot.DriveTrain.driveArcadeStop();
      Robot.Lift.stopPID();
      // Robot.Gantry.stopGantry();
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   protected void interrupted()
   {
      end();
   }
}