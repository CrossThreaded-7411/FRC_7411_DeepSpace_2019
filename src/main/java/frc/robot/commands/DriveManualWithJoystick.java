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
      requires(Robot.DriveTrain);                                                                                                                                                                                                                                         
      requires(Robot.Lift);
      requires(Robot.Grabber);
      requires(Robot.Gantry);
      requires(Robot.PressureRelease);
   }

   // Called just before this Command runs the first time
   protected void initialize()
   {
      Robot.DriveTrain.driveArcadeStop();
      Robot.Lift.stopLift();
      Robot.Grabber.stopGrabber();
      Robot.Gantry.stopGantry();
      Robot.Gantry.init();
      Robot.PressureRelease.stopPneumatics();
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute()
   {
      Robot.DriveTrain.driveArcade();
      Robot.Lift.driveLift();
      Robot.Grabber.driveGrabber();
      Robot.Gantry.driveGantry();
      Robot.PressureRelease.runPneumatics();
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished()
   {
      return false;
   }

   // Called once after isFinished returns true
   protected void end()
   {
      Robot.DriveTrain.driveArcadeStop();
      Robot.Lift.stopLift();
      Robot.Grabber.stopGrabber();
      Robot.Gantry.stopGantry();
      Robot.PressureRelease.stopPneumatics();
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   protected void interrupted()
   {
      end();
   }
}