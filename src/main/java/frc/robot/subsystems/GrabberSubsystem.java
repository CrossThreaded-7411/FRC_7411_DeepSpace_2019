package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class GrabberSubsystem extends Subsystem
{
    public VictorSP grabberMotor = new VictorSP(RobotMap.grabberMotorPort);

    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveManualWithJoystick());    
    }

    public void driveGrabber()
    {
        if (Robot.oi.driverStick2.getRawButton(4) == true)
        {
            grabberMotor.set(-1.0);
        }
        else if (Robot.oi.driverStick2.getRawButton(1) == true)
        {
            grabberMotor.set(1.0);
        }
        else
        {
            grabberMotor.set(0);
        }
    }

    public void stopGrabber()
    {
        grabberMotor.set(0);
    }
}