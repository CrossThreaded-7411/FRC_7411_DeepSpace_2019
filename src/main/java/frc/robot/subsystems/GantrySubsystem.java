package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GantrySubsystem extends Subsystem
{
    private VictorSP leftGantryMotor = new VictorSP(RobotMap.leftGantryMotorPort);
    private VictorSP rightGantryMotor = new VictorSP(RobotMap.rightGantryMotorPort);

    //limit switches
    public DigitalInput leftLimitSwitch;
    public DigitalInput rightLimitSwitch;

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
    }

    public void init()
    {
        leftLimitSwitch = new DigitalInput(3);
        rightLimitSwitch = new DigitalInput(2);
    }

    public void driveGantry()
    {
        double d2yAxis = Robot.oi.driverStick2.getRawAxis(1);

        if (leftLimitSwitch.get() == true && d2yAxis > 0)
        {
            leftGantryMotor.set(0);
        }
        else
        {
            if (Math.abs(d2yAxis) > 0.1)
            {
                leftGantryMotor.set(-d2yAxis);
            }
            else
            {
                leftGantryMotor.set(0);
            }
        }
        
        if (rightLimitSwitch.get() == true && d2yAxis > 0)
        {
            rightGantryMotor.set(0);
        }
        else
        {
            if (Math.abs(d2yAxis) > 0.1)
            {
                rightGantryMotor.set(-d2yAxis);
            }
            else
            {
                rightGantryMotor.set(0);
            }
        }

        SmartDashboard.putNumber("Left Power  ", leftGantryMotor.getSpeed());
        SmartDashboard.putNumber("Right Power  ", rightGantryMotor.getSpeed());

    }

    public void stopGantry()
    {
        leftGantryMotor.set(0);
        rightGantryMotor.set(0);
    }
}