package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GantrySubsystem extends Subsystem
{
    private VictorSP leftGantryMotor = new VictorSP(RobotMap.leftGantryMotorPort);
    private VictorSP rightGantryMotor = new VictorSP(RobotMap.rightGantryMotorPort);
    SpeedControllerGroup gantryMotors = new SpeedControllerGroup(leftGantryMotor, rightGantryMotor);

       //limit switches
   public DigitalInput leftLimitSwitch;
   public DigitalInput rightLimitSwitch;

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
    }

    public void init()
    {
        leftLimitSwitch = new DigitalInput(2);
        rightLimitSwitch = new DigitalInput(3);
    }

    public void driveGantry()
    {
        if (Math.abs(Robot.oi.driverStick2.getRawAxis(1)) <= (0.1))
        {
            gantryMotors.set(0);
        }
        else
        {
            gantryMotors.set(-Robot.oi.driverStick2.getRawAxis(1));
        }

        if (leftLimitSwitch.get() == true)
        {
            if (-Robot.oi.driverStick2.getRawAxis(1) > 0.1)
            {
                leftGantryMotor.set(-Robot.oi.driverStick2.getRawAxis(1));
            }
            else    
            {
                leftGantryMotor.set(0);
            }
        }
        
        if (rightLimitSwitch.get() == true)
        {
            if (-Robot.oi.driverStick2.getRawAxis(1) > 0.1)
            {
                rightGantryMotor.set(-Robot.oi.driverStick2.getRawAxis(1));
            }
            else
            {
                rightGantryMotor.set(0);
            }
        }

        SmartDashboard.putNumber("Left Power  ", leftGantryMotor.getSpeed());
        SmartDashboard.putNumber("Right Power  ", rightGantryMotor.getSpeed());
    }

    //Will need Encoders if used. If not, will need to be run off of time by speed
    public void driveGantryAuto(double speed)
    {
        gantryMotors.set(speed);
    }

    public void stopGantry()
    {
        gantryMotors.set(0);
    }
}