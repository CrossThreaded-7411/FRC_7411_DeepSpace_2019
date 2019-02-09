package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;

public class LiftSubsystem extends Subsystem
{
    public VictorSP leftLiftMotor = new VictorSP(RobotMap.leftLiftMotorPort);
    public VictorSP rightLiftMotor = new VictorSP(RobotMap.rightLiftMotorPort);
    SpeedControllerGroup liftMotors = new SpeedControllerGroup(leftLiftMotor, rightLiftMotor);

    public Encoder liftEncoder = new Encoder(0,1);
    public int intCount = liftEncoder.get();


    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
        initEncoder();
    }

    public void initEncoder()
    {
        liftEncoder.reset();
    }

    //need to get encoder values and in auto set encoder values.
    public void driveLift()
    {
        if (Math.abs(Robot.oi.driverStick2.getRawAxis(5)) <= 0.1)
        {
            liftMotors.set(0);
        }
        else 
        {
            liftMotors.set(Robot.oi.driverStick2.getRawAxis(5) * 0.5);
        }

        SmartDashboard.putNumber("Encoder Counts ", intCount);
    }

    // public void driveLiftAuto()
    // {

    // }

    public void stopLift()
    {
        liftMotors.set(0);
    }
}