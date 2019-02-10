package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

public class GrabberSubsystem extends Subsystem
{
    public VictorSP leftGrabberMotor = new VictorSP(RobotMap.leftGrabberMotorPort);
    public VictorSP rightGrabberMotor = new VictorSP(RobotMap.rightGrabberMotorPort);

    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveManualWithJoystick());    
    }

    public void driveGrabber()
    {
        if (Robot.oi.driverStick2.getRawButton(4) == true)
        {
            leftGrabberMotor.set(-1.0);
            rightGrabberMotor.set(1.0);
        }
        else if (Robot.oi.driverStick2.getRawButton(1) == true)
        {
            leftGrabberMotor.set(1.0);
            rightGrabberMotor.set(-1.0);
        }
        else
        {
            leftGrabberMotor.set(0);
            rightGrabberMotor.set(0);
        }
    }

    public void stopGrabber()
    {
        leftGrabberMotor.set(0);
        rightGrabberMotor.set(0);
    }
}