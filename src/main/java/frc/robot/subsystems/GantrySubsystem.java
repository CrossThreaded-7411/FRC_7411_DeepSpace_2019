package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap.PWMport;
import frc.robot.commands.DriveManualWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GantrySubsystem extends Subsystem
{
    private VictorSP gantryMotor = new VictorSP(PWMport.gantryMotorPort.getVal());

    //limit switches
    public DigitalInput limitSwitch;

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
    }

    public void init()
    {
        limitSwitch = new DigitalInput(2);
    }

    public void driveGantry()
    {
        double d2yAxis = Robot.oi.driverStick2.getRawAxis(5);

        if (limitSwitch.get() == true && d2yAxis > 0)
        {
            gantryMotor.set(0);
        }
        else
        {
            if (Math.abs(d2yAxis) > 0.1)
            {
            gantryMotor.set(-d2yAxis);
            }
            else
            {
                gantryMotor.set(0);
            }
        }
    }

    public void stopGantry()
    {
        gantryMotor.set(0);
    }
}